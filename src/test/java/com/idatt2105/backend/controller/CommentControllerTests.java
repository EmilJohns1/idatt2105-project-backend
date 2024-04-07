package com.idatt2105.backend.controller;

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

import com.idatt2105.backend.dto.CommentDTO;
import com.idatt2105.backend.service.CommentService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** The CommentControllerTests class is a test class that tests the CommentController class. */
@WebMvcTest(CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
class CommentControllerTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private CommentService commentService;

  /**
   * The BasicFunctionalityTests class is a test class that tests the basic functionality of the
   * CommentController class.
   */
  @Nested
  class BasicFunctionalityTests {
    CommentDTO commentDTO;

    @BeforeEach
    void setUp() {
      commentDTO = new CommentDTO();
      commentDTO.setContent("Test comment");

      when(commentService.getAllComments()).thenReturn(List.of(commentDTO));
      when(commentService.getCommentById(1L)).thenReturn(commentDTO);
      when(commentService.saveComment(any(CommentDTO.class))).thenReturn(commentDTO);
    }

    /**
     * This method tests the behavior of the getAllComments endpoint.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the comments are
     * successfully retrieved.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getAllCommentsReturnsOkAndComments() throws Exception {
      mockMvc.perform(get("/api/comments").secure(true)).andExpect(status().isOk());
    }

    /**
     * This method tests the behavior of the getCommentById endpoint with a valid comment ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when a comment with
     * the specified ID exists.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getCommentByIdReturnsOk() throws Exception {
      mockMvc.perform(get("/api/comments/1").secure(true)).andExpect(status().isOk());
    }

    /**
     * This method tests the behavior of the createComment endpoint with a valid comment.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 201 Created when the comment
     * is successfully created.
     *
     * @throws Exception if the test fails
     */
    @Test
    void createCommentReturnsCreated() throws Exception {
      mockMvc
          .perform(
              post("/api/comments")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content("{\"id\":1,\"content\":\"Test comment\"}")
                  .secure(true))
          .andExpect(status().isCreated());
    }

    /**
     * This method tests the behavior of the updateComment endpoint with a valid comment ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the comment is
     * successfully updated.
     *
     * @throws Exception if the test fails
     */
    @Test
    void updateCommentReturnsOk() throws Exception {
      mockMvc
          .perform(
              put("/api/comments/1")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content("\"Test comment updated\"")
                  .secure(true))
          .andExpect(status().isOk());
    }

    /**
     * This method tests the behavior of the delete endpoint with a valid comment ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 204 No Content when the
     * comment is successfully deleted.
     *
     * @throws Exception if the test fails
     */
    @Test
    void deleteCommentReturnsNoContent() throws Exception {
      mockMvc.perform(delete("/api/comments/1").secure(true)).andExpect(status().isNoContent());
    }

    /**
     * This method tests the behavior of the getCommentsByQuizId endpoint with a valid quiz ID that
     * has comments.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the comment
     * exists for the specified quiz ID.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getCommentsByQuizIdReturnsOkAndComments() throws Exception {
      when(commentService.getCommentsByQuizId(1L)).thenReturn(List.of(commentDTO));
      mockMvc.perform(get("/api/comments/quiz/1").secure(true)).andExpect(status().isOk());
    }

    /**
     * This method tests the getCommentsByUserId endpoint with a valid user ID that has commented.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the comment
     * exists for the specified user ID.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getCommentsByUserIdReturnsOkAndComments() throws Exception {
      when(commentService.getCommentsByUserId(1L)).thenReturn(List.of(commentDTO));
      mockMvc.perform(get("/api/comments/user/1").secure(true)).andExpect(status().isOk());
    }
  }
}
