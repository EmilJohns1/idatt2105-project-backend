package com.idatt2105.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

/** The QuestionControllerTests class is a test class that tests the QuestionController class. */
@WebMvcTest(QuestionController.class)
@AutoConfigureMockMvc(addFilters = false)
class QuestionControllerTests {
  @Autowired MockMvc mockMvc;
  @MockBean private QuestionService questionService;

  /**
   * The BasicFunctionalityTests class is a test class that tests the basic functionality of the
   * QuestionController class.
   */
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

    /**
     * This method tests the behavior of the addQuestion endpoint with a valid body.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 201 Created when the question
     * is successfully created.
     *
     * @throws Exception if the test fails
     */
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

    /**
     * This method tests the behavior of the getQuestionById endpoint with a valid question ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when a question with
     * the specified ID exists.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getQuestionByIdReturnsOkAndQuestion() throws Exception {
      mockMvc
          .perform(get("/api/question/get/1").secure(true))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(1));
    }

    /**
     * This method tests the behavior of the deleteQuestionById endpoint with a valid question ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the question is
     * successfully deleted.
     *
     * @throws Exception if the test fails
     */
    @Test
    void deleteQuestionByIdReturnsOk() throws Exception {
      mockMvc.perform(delete("/api/question/delete/1").secure(true)).andExpect(status().isOk());
    }

    /**
     * This method tests the behavior of the updateQuestion endpoint with a valid body.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the question is
     * successfully updated.
     *
     * @throws Exception if the test fails
     */
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

    /**
     * This method tests the behavior of the getQuestionsByQuizId endpoint with a valid quiz ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the questions are
     * successfully retrieved.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getQuestionsByQuizIdReturnsOkAndQuestions() throws Exception {
      mockMvc
          .perform(get("/api/question/get/all/1").secure(true))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].id").value(1))
          .andExpect(jsonPath("$[1].id").value(2));
    }

    /**
     * This method tests the behavior of the addAlternative endpoint with a valid body.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 201 Created when the
     * alternative is successfully added.
     *
     * @throws Exception if the test fails
     */
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

    /**
     * This method tests the behavior of the deleteAlternative endpoint with a valid alternative ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the alternative
     * is successfully deleted.
     *
     * @throws Exception if the test fails
     */
    @Test
    void deleteAlternativeReturnsOk() throws Exception {
      mockMvc
          .perform(delete("/api/question/delete/alternative/1").secure(true))
          .andExpect(status().isOk());
    }

    /**
     * This method tests the behavior of the updateTrueOrFalseQuestion endpoint with a valid body.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the question is
     * successfully updated.
     *
     * @throws Exception if the test fails
     */
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

    /**
     * Test the getQuestionById endpoint with a non-existent ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 404 Not Found when a question
     * with the specified ID does not exist.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getQuestionByIdWithNonExistentIdReturns404() throws Exception {
      when(questionService.getQuestionById(any())).thenThrow(InvalidIdException.class);
      mockMvc
          .perform(get("/api/question/get/5").contentType(MediaType.APPLICATION_JSON).secure(true))
          .andExpect(status().isNotFound());
    }

    /**
     * Test the addQuestion endpoint with an invalid type.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 400 Bad Request when the type
     * is invalid.
     *
     * @throws Exception if the test fails
     */
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

  /**
   * This method converts an object to a JSON string.
   *
   * @param obj the object to convert
   * @return the JSON string
   */
  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
