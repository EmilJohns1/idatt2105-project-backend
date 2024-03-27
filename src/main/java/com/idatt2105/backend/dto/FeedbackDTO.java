package com.idatt2105.backend.dto;

import com.idatt2105.backend.enumerator.FeedbackType;

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
}
