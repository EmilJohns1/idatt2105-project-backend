package com.idatt2105.backend.controller;

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

import com.idatt2105.backend.dto.FeedbackDTO;
import com.idatt2105.backend.service.FeedbackService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FeedbackControllerTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private FeedbackService feedbackService;

  @Nested
  class BasicFunctionalityTests {
    FeedbackDTO feedbackDTO;

    @BeforeEach
    void setUp() {
      feedbackDTO = new FeedbackDTO();
      feedbackDTO.setContent("Test feedback");

      when(feedbackService.getAllFeedback()).thenReturn(List.of(feedbackDTO));
      when(feedbackService.getFeedbackByUserId(1L)).thenReturn(List.of(feedbackDTO));
      when(feedbackService.createFeedback(any(FeedbackDTO.class))).thenReturn(feedbackDTO);
    }

    @Test
    void getAllFeedbackReturnsOkAndFeedback() throws Exception {
      mockMvc.perform(get("/api/feedback/all").secure(true)).andExpect(status().isOk());
    }

    @Test
    void getFeedbackByUserIdReturnsOkAndFeedback() throws Exception {
      mockMvc.perform(get("/api/feedback/user/1").secure(true)).andExpect(status().isOk());
    }

    @Test
    void createFeedbackReturnsCreated() throws Exception {
      mockMvc
          .perform(
              post("/api/feedback/create")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content("{\"id\":1,\"content\":\"Test feedback\"}")
                  .secure(true))
          .andExpect(status().isCreated());
    }

    @Test
    void updateFeedbackReturnsNoContent() throws Exception {
      mockMvc
          .perform(
              put("/api/feedback/update/1")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content("{\"content\":\"Test feedback updated\"}")
                  .secure(true))
          .andExpect(status().isNoContent());
    }

    @Test
    void deleteFeedbackReturnsNoContent() throws Exception {
      mockMvc
          .perform(delete("/api/feedback/delete/1").secure(true))
          .andExpect(status().isNoContent());
    }
  }
}
