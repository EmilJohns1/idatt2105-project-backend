package com.idatt2105.backend.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.Tag;
import com.idatt2105.backend.service.QuizService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** The QuizControllerTests class is a test class that tests the QuizController class. */
@WebMvcTest(QuizController.class)
@AutoConfigureMockMvc(addFilters = false)
public class QuizControllerTests {
  @Autowired MockMvc mockMvc;

  @MockBean private QuizService quizService;

  /**
   * The BasicFunctionalityTests class is a test class that tests the basic functionality of the
   * QuizController class.
   */
  @Nested
  class BasicFunctionalityTests {
    QuizDTO quizDTO;
    UserDTO userDTO;
    Set<UserDTO> users;
    Set<Tag> tags;

    @BeforeEach
    void setUp() {
      quizDTO = new QuizDTO();
      quizDTO.setId(1L);
      quizDTO.setTitle("Quiz Title");
      quizDTO.setDescription("Quiz Description");

      userDTO = new UserDTO();
      userDTO.setId(1L);
      userDTO.setUsername("TestUser");

      users = new HashSet<>();
      users.add(userDTO);

      Tag tag = new Tag();
      tag.setId(1L);
      tag.setTagName("TestTag");

      tags = new HashSet<>();
      tags.add(tag);

      when(quizService.save(any(QuizDTO.class))).thenReturn(quizDTO);
      when(quizService.getQuizById(1L)).thenReturn(quizDTO);
      when(quizService.getUsersByQuizId(1L)).thenReturn(users);
      when(quizService.addTags(any(QuizDTO.class))).thenReturn(quizDTO);
      when(quizService.deleteTags(any(QuizDTO.class))).thenReturn(quizDTO);

      List<QuizDTO> quizzes = new ArrayList<>();
      quizzes.add(quizDTO);
      Page<QuizDTO> quizPage = new PageImpl<>(quizzes);
      when(quizService.getAllQuizzes(any())).thenReturn(quizPage);
    }

    /**
     * This method tests the behavior of the getAllQuizzes endpoint.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the quizzes are
     * successfully retrieved.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getAllQuizzesReturnsOkAndQuizzes() throws Exception {
      mockMvc.perform(get("/api/quizzes").secure(true)).andExpect(status().isOk());
    }

    /**
     * This method tests the behavior of the getQuizById endpoint with a valid quiz ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when a quiz with the
     * specified ID exists.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getQuizByIdReturnsOkAndQuiz() throws Exception {
      mockMvc.perform(get("/api/quizzes/1").secure(true)).andExpect(status().isOk());
    }

    /**
     * This method tests the behavior of the createQuiz endpoint with a valid body.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 201 Created when the quiz is
     * successfully created.
     *
     * @throws Exception if the test fails
     */
    @Test
    void createQuizReturnsCreatedAndQuiz() throws Exception {
      mockMvc
          .perform(
              post("/api/quizzes")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(asJsonString(quizDTO))
                  .secure(true))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id").value(1));
    }

    /**
     * This method tests the updateQuiz endpoint with a valid quiz ID and body.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the quiz is
     * successfully updated.
     *
     * @throws Exception if the test fails
     */
    @Test
    void updateQuizReturnsOk() throws Exception {
      mockMvc
          .perform(
              put("/api/quizzes/1")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(asJsonString(quizDTO))
                  .secure(true))
          .andExpect(status().isOk());
    }

    /**
     * This method tests the addUserToQuiz endpoint with a valid quiz ID and user ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 201 Created when the user is
     * successfully added to the quiz.
     *
     * @throws Exception if the test fails
     */
    @Test
    void addUserToQuizReturnsCreated() throws Exception {
      mockMvc.perform(post("/api/quizzes/1/users/1").secure(true)).andExpect(status().isCreated());
    }

    /**
     * This method tests the removeUserFromQuiz endpoint with a valid quiz ID and user ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 204 No Content when the user
     * is successfully removed from the quiz.
     *
     * @throws Exception if the test fails
     */
    @Test
    void removeUserFromQuizReturnsNoContent() throws Exception {
      mockMvc
          .perform(delete("/api/quizzes/1/users/1").secure(true))
          .andExpect(status().isNoContent());
    }

    /**
     * This method tests the getUsersByQuizId endpoint with a valid quiz ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the users are
     * successfully retrieved.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getUsersByQuizIdReturnsOkAndUsers() throws Exception {
      mockMvc
          .perform(get("/api/quizzes/users/1").secure(true))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].id").value(1));
    }

    /**
     * This method tests the addTags endpoint with a valid quiz ID and body.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the tags are
     * successfully added to the quiz.
     *
     * @throws Exception if the test fails
     */
    @Test
    void addTagsReturnsOkAndQuiz() throws Exception {
      mockMvc
          .perform(
              patch("/api/quizzes/add/tags/1")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(asJsonString(tags))
                  .secure(true))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(1));
    }

    /**
     * This method tests the deleteTags endpoint with a valid quiz ID and body.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the tags are
     * successfully deleted from the quiz.
     *
     * @throws Exception if the test fails
     */
    @Test
    void deleteTagsReturnsOkAndQuiz() throws Exception {
      mockMvc
          .perform(
              delete("/api/quizzes/delete/tags/1")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(asJsonString(tags))
                  .secure(true))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(1));
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
