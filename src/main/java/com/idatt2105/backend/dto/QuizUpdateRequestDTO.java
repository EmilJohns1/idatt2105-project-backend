package com.idatt2105.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Represents a quiz update request data transfer object. */
@NoArgsConstructor
@Getter
@Setter
@Data
public class QuizUpdateRequestDTO {
  private String title;
  private String description;
  private String quizPictureUrl;

  /**
   * Constructs a new QuizUpdateRequestDTO with the specified title and description.
   *
   * @param title the title
   * @param description the description
   * @param quizPictureUrl the quiz picture URL
   */
  public QuizUpdateRequestDTO(String title, String description, String quizPictureUrl) {
    this.title = title;
    this.description = description;
    this.quizPictureUrl = quizPictureUrl;
  }

  /**
   * Constructs a new QuizUpdateRequestDTO with the specified title and description.
   *
   * @param title the title
   * @param description the description
   */
  public QuizUpdateRequestDTO(String title, String description) {
    this.title = title;
    this.description = description;
  }
}
