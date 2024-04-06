package com.idatt2105.backend.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

/** The AttemptControllerTests class is a test class that tests the AttemptController class. */
@WebMvcTest(AttemptController.class)
@AutoConfigureMockMvc(addFilters = false)
class AttemptControllerTests {
  @Autowired MockMvc mockMvc;
  @MockBean private AttemptService attemptService;

  /**
   * This method tests the addQuizAttempt endpoint with a valid body.
   *
   * <p>It verifies that the endpoint returns an HTTP status code of 201 Created when the attempt is
   * successfully added.
   *
   * @throws Exception if the test fails
   */
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
                .content(asJsonString(quizAttemptDTO))
                .secure(true))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1));
  }

  /**
   * This method tests the addQuizAttempt endpoint in the AttemptController class with an empty
   * body.
   *
   * <p>It verifies that the endpoint returns an HTTP status code of 400 Bad Request when the body
   * is empty.
   *
   * @throws Exception if the test fails
   */
  @Test
  void addQuizAttemptWithEmptyBodyIsBadRequest() throws Exception {
    mockMvc
        .perform(post("/api/attempts/add").contentType(MediaType.APPLICATION_JSON).secure(true))
        .andExpect(status().isBadRequest());
  }

  /**
   * This method tests the getAllAttemptsForUser endpoint with a valid user ID.
   *
   * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the attempts are
   * successfully retrieved. It also verifies that the endpoint returns the correct attempts.
   *
   * @throws Exception if the test fails
   */
  @Test
  void getAllAttemptsForUser() throws Exception {
    QuizAttempt quizAttempt = new QuizAttempt();
    quizAttempt.setId(1L);
    when(attemptService.getAllAttemptsForUser(1L)).thenReturn(List.of(quizAttempt));

    mockMvc
        .perform(get("/api/attempts/all/1").secure(true))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1));
  }

  /**
   * method to convert object to json string
   *
   * @param obj object to convert
   * @return json string
   */
  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
