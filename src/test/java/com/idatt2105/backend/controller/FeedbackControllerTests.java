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

import com.idatt2105.backend.dto.FeedbackDTO;
import com.idatt2105.backend.service.FeedbackService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** The FeedbackControllerTests class is a test class that tests the FeedbackController class. */
@WebMvcTest(FeedbackController.class)
@AutoConfigureMockMvc(addFilters = false)
class FeedbackControllerTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private FeedbackService feedbackService;

  /**
   * The BasicFunctionalityTests class is a test class that tests the basic functionality of the
   * FeedbackController class.
   */
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

    /**
     * This method tests the behavior of the getAllFeedback endpoint.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the feedback are
     * successfully retrieved.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getAllFeedbackReturnsOkAndFeedback() throws Exception {
      mockMvc.perform(get("/api/feedback/all").secure(true)).andExpect(status().isOk());
    }

    /**
     * This method tests the behavior of the getFeedbackByUserId endpoint with a valid user ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 200 OK when the feedback are
     * successfully retrieved.
     *
     * @throws Exception if the test fails
     */
    @Test
    void getFeedbackByUserIdReturnsOkAndFeedback() throws Exception {
      mockMvc.perform(get("/api/feedback/user/1").secure(true)).andExpect(status().isOk());
    }

    /**
     * This method tests the behavior of the createFeedback endpoint with a valid body.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 201 Created when the feedback
     * is successfully created.
     *
     * @throws Exception if the test fails
     */
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

    /**
     * This method tests the behavior of the updateFeedback endpoint with a valid feedback ID.
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 204 No Content when the
     * feedback is successfully updated.
     *
     * @throws Exception if the test fails
     */
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

    /**
     * This method tests the behavior of the deleteFeedback endpoint with a valid feedback ID. W
     *
     * <p>It verifies that the endpoint returns an HTTP status code of 204 No Content when the
     * feedback is successfully deleted.
     *
     * @throws Exception if the test fails
     */
    @Test
    void deleteFeedbackReturnsNoContent() throws Exception {
      mockMvc
          .perform(delete("/api/feedback/delete/1").secure(true))
          .andExpect(status().isNoContent());
    }
  }
}
