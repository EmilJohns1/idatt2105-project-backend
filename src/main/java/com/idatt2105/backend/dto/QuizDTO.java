package com.idatt2105.backend.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.idatt2105.backend.model.Quiz;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Data Transfer Object (DTO) for a Quiz. This class is used to transfer Quiz data
 * between different layers of the application.
 */
@NoArgsConstructor
@Getter
@Setter
@Data
public class QuizDTO {
  private Long id;
  private String title;
  private String description;
  private LocalDateTime creationDate;
  private LocalDateTime lastModifiedDate;
  private Set<UserDTO> userDTOs;

  /**
   * Constructs a QuizDTO object from a Quiz entity.
   *
   * @param quiz The Quiz entity to construct the DTO from.
   */
  public QuizDTO(Quiz quiz) {
    this.id = quiz.getId();
    this.title = quiz.getTitle();
    this.description = quiz.getDescription();
    this.creationDate = quiz.getCreationDate();
    this.lastModifiedDate = quiz.getLastModifiedDate();
  }

  /**
   * Constructs a QuizDTO object with the specified attributes.
   *
   * @param id The ID of the quiz.
   * @param title The title of the quiz.
   * @param description The description of the quiz.
   * @param creationDate The creation date of the quiz.
   * @param lastModifiedDate The last modified date of the quiz.
   */
  public QuizDTO(
      Long id,
      String title,
      String description,
      LocalDateTime creationDate,
      LocalDateTime lastModifiedDate) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.creationDate = creationDate;
    this.lastModifiedDate = lastModifiedDate;
  }

  /**
   * Constructs a QuizDTO object with the specified attributes.
   *
   * @param id The ID of the quiz.
   * @param title The title of the quiz.
   * @param description The description of the quiz.
   * @param creationDate The creation date of the quiz.
   * @param lastModifiedDate The last modified date of the quiz.
   * @param userDTOs The set of UserDTOs associated with the quiz.
   */
  public QuizDTO(
      Long id,
      String title,
      String description,
      LocalDateTime creationDate,
      LocalDateTime lastModifiedDate,
      Set<UserDTO> userDTOs) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.creationDate = creationDate;
    this.lastModifiedDate = lastModifiedDate;
    this.userDTOs = userDTOs;
  }

  /**
   * Constructs a QuizDTO object with the specified attributes.
   *
   * @param id The ID of the quiz.
   * @param title The title of the quiz.
   * @param description The description of the quiz.
   * @param userDTOs The set of UserDTOs associated with the quiz.
   */
  public QuizDTO(Long id, String title, String description, Set<UserDTO> userDTOs) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  /*
   * Constructs a QuizDTO object with the specified attributes.
   * @param title The title of the quiz.
   * @param description The description of the quiz.
   */
  public QuizDTO(String title, String description) {
    this.title = title;
    this.description = description;
  }

  /**
   * Converts the QuizDTO object to a Quiz entity.
   *
   * @return The Quiz entity.
   */
  public Quiz toEntity() {
    Quiz quiz = new Quiz();
    quiz.setId(this.id);
    quiz.setTitle(this.title);
    quiz.setDescription(this.description);
    return quiz;
  }
}
