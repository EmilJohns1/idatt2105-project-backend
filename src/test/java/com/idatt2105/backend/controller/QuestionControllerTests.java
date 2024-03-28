package com.idatt2105.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idatt2105.backend.dto.AlternativeDTO;
import com.idatt2105.backend.dto.QuestionDTO;
import com.idatt2105.backend.enumerator.QuestionType;
import com.idatt2105.backend.model.Alternative;
import com.idatt2105.backend.model.MultipleChoiceQuestion;
import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.model.TrueOrFalseQuestion;
import com.idatt2105.backend.service.QuestionService;
import com.idatt2105.backend.util.InvalidIdException;
import com.idatt2105.backend.util.InvalidQuestionTypeException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTests {
  @Autowired MockMvc mockMvc;
  @MockBean private QuestionService questionService;

  @Nested
  class BasicFunctionalityTests {
    QuestionDTO mCQuestionDTO;

    @BeforeEach
    void setUp() {
      mCQuestionDTO = new QuestionDTO();
      mCQuestionDTO.setType(QuestionType.MULTIPLE_CHOICE);
      mCQuestionDTO.setQuizId(1L);
      mCQuestionDTO.setQuestionText("What is the capital of Norway?");
      List<Question> questions = new ArrayList<>();
      Question question1 = new MultipleChoiceQuestion();
      question1.setId(1L);
      questions.add(question1);
      TrueOrFalseQuestion question2 = new TrueOrFalseQuestion();
      question2.setId(2L);
      questions.add(question2);
      AlternativeDTO alternativeDTO = new AlternativeDTO();
      alternativeDTO.setQuestionId(1L);
      alternativeDTO.setAlternativeText("Oslo");
      alternativeDTO.setCorrect(true);
      Alternative alternative = new Alternative();
      alternative.setId(1L);

      when(questionService.addQuestion(any(QuestionDTO.class))).thenReturn(question1);
      when(questionService.getQuestionById(1L)).thenReturn(question1);
      when(questionService.updateQuestion(any(QuestionDTO.class))).thenReturn(question1);
      when(questionService.getQuestionsByQuizId(1L)).thenReturn(questions);
      when(questionService.addAlternative(any(AlternativeDTO.class))).thenReturn(alternative);
      when(questionService.updateTrueOrFalseQuestion(any(QuestionDTO.class))).thenReturn(question2);
      doNothing().when(questionService).deleteAlternative(1L);
    }

    @Test
    void addQuestionReturnsCreated() throws Exception {
      mockMvc
          .perform(
              post("/api/question/add")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(asJsonString(mCQuestionDTO))
                  .secure(true))
          .andExpect(status().isCreated());
    }

    @Test
    void getQuestionByIdReturnsOkAndQuestion() throws Exception {
      mockMvc
          .perform(get("/api/question/get/1").secure(true))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deleteQuestionByIdReturnsOk() throws Exception {
      mockMvc.perform(delete("/api/question/delete/1").secure(true)).andExpect(status().isOk());
    }

    @Test
    void updateQuestionReturnsOk() throws Exception {
      mockMvc
          .perform(
              post("/api/question/update")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(asJsonString(mCQuestionDTO))
                  .secure(true))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getQuestionsByQuizIdReturnsOkAndQuestions() throws Exception {
      mockMvc
          .perform(get("/api/question/get/all/1").secure(true))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].id").value(1))
          .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void addAlternativeReturnsCreatedAndAlternative() throws Exception {
      mockMvc
          .perform(
              post("/api/question/add/alternative")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(asJsonString(new AlternativeDTO()))
                  .secure(true))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deleteAlternativeReturnsOk() throws Exception {
      mockMvc
          .perform(delete("/api/question/delete/alternative/1").secure(true))
          .andExpect(status().isOk());
    }

    @Test
    void updateTrueOrFalseQuestionReturnsOkAndQuestion() throws Exception {
      mockMvc
          .perform(
              patch("/api/question/update/true-or-false/1&&true")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(asJsonString(new QuestionDTO()))
                  .secure(true))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void getQuestionByIdWithNonExistentIdReturns404() throws Exception {
      when(questionService.getQuestionById(any())).thenThrow(InvalidIdException.class);
      mockMvc
          .perform(get("/api/question/get/5").contentType(MediaType.APPLICATION_JSON).secure(true))
          .andExpect(status().isNotFound());
    }

    @Test
    void addQuestionWithInvalidTypeReturns400() throws Exception {
      when(questionService.addQuestion(any(QuestionDTO.class)))
          .thenThrow(InvalidQuestionTypeException.class);
      mCQuestionDTO.setType(null);
      mockMvc
          .perform(
              post("/api/question/add")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(asJsonString(mCQuestionDTO))
                  .secure(true))
          .andExpect(status().isBadRequest());
    }
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
