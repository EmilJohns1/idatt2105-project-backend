package com.idatt2105.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.idatt2105.backend.dto.FeedbackDTO;
import com.idatt2105.backend.service.FeedbackService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/** The FeedbackController class handles HTTP requests related to feedback operations. */
@RestController
@RequestMapping("/api/feedback")
@Tag(name = "Feedback", description = "Operations related to feedback")
public class FeedbackController {
  private final FeedbackService feedbackService;

  @Autowired
  public FeedbackController(FeedbackService feedbackService) {
    this.feedbackService = feedbackService;
  }

  /**
   * Creates a new feedback.
   *
   * @param feedbackDTO The FeedbackDTO object containing the feedback data.
   * @return The ResponseEntity containing the created FeedbackDTO and the HTTP status code.
   */
  @PostMapping("/create")
  @Operation(summary = "Create a new feedback")
  public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
    FeedbackDTO createdFeedback = feedbackService.createFeedback(feedbackDTO);
    return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
  }

  /**
   * Updates an existing feedback.
   *
   * @param id The ID of the feedback to be updated.
   * @param feedbackDTO The FeedbackDTO object containing the updated feedback data.
   * @return The ResponseEntity with the HTTP status code indicating the success of the update
   *     operation.
   */
  @PutMapping("/update/{id}")
  @Operation(summary = "Update an existing feedback")
  public ResponseEntity<Void> updateFeedback(
      @PathVariable Long id, @RequestBody FeedbackDTO feedbackDTO) {
    feedbackService.updateFeedback(id, feedbackDTO);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Deletes a feedback.
   *
   * @param id The ID of the feedback to be deleted.
   * @return The ResponseEntity with the HTTP status code indicating the success of the delete
   *     operation.
   */
  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Delete a feedback")
  public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
    feedbackService.deleteFeedback(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Retrieves all feedback.
   *
   * @return The ResponseEntity containing the list of all FeedbackDTO objects and the HTTP status
   *     code.
   */
  @GetMapping("/all")
  @Operation(summary = "Get all feedback")
  public ResponseEntity<List<FeedbackDTO>> getAllFeedback() {
    List<FeedbackDTO> feedbackList = feedbackService.getAllFeedback();
    return new ResponseEntity<>(feedbackList, HttpStatus.OK);
  }

  /**
   * Retrieves feedback by user ID.
   *
   * @param userId The ID of the user to retrieve feedback for.
   * @return The ResponseEntity containing the list of FeedbackDTO objects for the specified user
   *     and the HTTP status code.
   */
  @GetMapping("/user/{userId}")
  @Operation(summary = "Get feedback by user ID")
  public ResponseEntity<List<FeedbackDTO>> getFeedbackByUserId(@PathVariable Long userId) {
    List<FeedbackDTO> feedbackList = feedbackService.getFeedbackByUserId(userId);
    return new ResponseEntity<>(feedbackList, HttpStatus.OK);
  }
}
