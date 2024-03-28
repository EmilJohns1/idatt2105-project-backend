package com.idatt2105.backend.dto;

import com.idatt2105.backend.enumerator.FeedbackType;
import com.idatt2105.backend.model.Feedback;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Represents a feedback DTO used for transferring feedback data. */
@NoArgsConstructor
@AllArgsConstructor
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

  public FeedbackDTO(Feedback feedback) {
    if (feedback == null) {
      throw new IllegalArgumentException("Feedback cannot be null");
    }
    this.firstName = feedback.getFirstName();
    this.lastName = feedback.getLastName();
    this.email = feedback.getEmail();
    this.feedbackType = feedback.getFeedbackType();
    this.content = feedback.getContent();
    this.userId = feedback.getUser() == null ? null : feedback.getUser().getId();
  }

  public Feedback convertToEntity() {
    Feedback feedback = new Feedback();
    feedback.setFirstName(getFirstName());
    feedback.setLastName(getLastName());
    feedback.setEmail(getEmail());
    feedback.setFeedbackType(getFeedbackType());
    feedback.setContent(getContent());
    return feedback;
  }
}
