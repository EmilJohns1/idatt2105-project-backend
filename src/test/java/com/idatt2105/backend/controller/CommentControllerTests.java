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

@WebMvcTest(CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
class CommentControllerTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private CommentService commentService;

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

    @Test
    void getAllCommentsReturnsOkAndComments() throws Exception {
      mockMvc.perform(get("/api/comments").secure(true)).andExpect(status().isOk());
    }

    @Test
    void getCommentByIdReturnsOk() throws Exception {
      mockMvc.perform(get("/api/comments/1").secure(true)).andExpect(status().isOk());
    }

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

    @Test
    void deleteCommentReturnsNoContent() throws Exception {
      mockMvc.perform(delete("/api/comments/1").secure(true)).andExpect(status().isNoContent());
    }

    @Test
    void getCommentsByQuizIdReturnsOkAndComments() throws Exception {
      when(commentService.getCommentsByQuizId(1L)).thenReturn(List.of(commentDTO));
      mockMvc.perform(get("/api/comments/quiz/1").secure(true)).andExpect(status().isOk());
    }

    @Test
    void getCommentsByUserIdReturnsOkAndComments() throws Exception {
      when(commentService.getCommentsByUserId(1L)).thenReturn(List.of(commentDTO));
      mockMvc.perform(get("/api/comments/user/1").secure(true)).andExpect(status().isOk());
    }
  }
}
