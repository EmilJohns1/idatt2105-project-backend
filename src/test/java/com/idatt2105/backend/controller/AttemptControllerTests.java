package com.idatt2105.backend.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idatt2105.backend.dto.QuizAttemptDTO;
import com.idatt2105.backend.model.QuizAttempt;
import com.idatt2105.backend.service.AttemptService;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AttemptController.class)
class AttemptControllerTests {
  @Autowired MockMvc mockMvc;

  @MockBean private AttemptService attemptService;

  @Test
  void addQuizAttempt() throws Exception {
    QuizAttemptDTO quizAttemptDTO = new QuizAttemptDTO();
    QuizAttempt expected = new QuizAttempt();
    expected.setId(1L);
    when(attemptService.addQuizAttempt(quizAttemptDTO)).thenReturn(expected);

    mockMvc
        .perform(
            post("/api/attempts/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(quizAttemptDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1));
  }

  @Test
  void addQuizAttemptWithEmptyBodyIsBadRequest() throws Exception {
    mockMvc
        .perform(post("/api/attempts/add").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void getAllAttemptsForUser() throws Exception {
    QuizAttempt quizAttempt = new QuizAttempt();
    quizAttempt.setId(1L);
    when(attemptService.getAllAttemptsForUser(1L)).thenReturn(List.of(quizAttempt));

    mockMvc
        .perform(get("/api/attempts/all/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1));
  }

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
