package com.idatt2105.backend.dto;

import com.idatt2105.backend.enumerator.FeedbackType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Represents a feedback DTO used for transferring feedback data. */
@NoArgsConstructor
@Setter
@Getter
@Data
public class FeedbackDTO {
  private String firstName;
  private String lastName;
  private String email;

  @Schema(description = "The type of feedback (ASSISTANCE, FEEDBACK, REPORT_ISSUE)")
  private FeedbackType feedbackType;

  private String content;
  private Long userId;

  /**
   * Constructs a new FeedbackDTO with the specified parameters.
   *
   * @param firstName the first name of the user giving the feedback
   * @param lastName the last name of the user giving the feedback
   * @param email the email of the user giving the feedback
   * @param feedbackType the type of feedback
   * @param content the content of the feedback
   * @param userId the ID of the user giving the feedback
   */
  public FeedbackDTO(
      String firstName,
      String lastName,
      String email,
      FeedbackType feedbackType,
      String content,
      Long userId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.feedbackType = feedbackType;
    this.content = content;
    this.userId = userId;
  }
}
