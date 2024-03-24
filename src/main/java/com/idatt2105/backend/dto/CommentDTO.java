package com.idatt2105.backend.dto;

import com.idatt2105.backend.model.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * A Data Transfer Object (DTO) class representing a comment.
 * This class is used to transfer comment data between different layers of the application.
 */
@NoArgsConstructor
@Data
public class CommentDTO {
  private String content;
  private Long userId;
  private Long quizId;
  private LocalDateTime creationDate;
  private LocalDateTime lastModifiedDate;

  /**
   * Constructs a CommentDTO object based on a Comment entity.
   *
   * @param comment The Comment entity to create the DTO from.
   */
  public CommentDTO(Comment comment) {
    this.content = comment.getContent();
    this.userId = comment.getUser().getId();
    this.quizId = comment.getQuiz().getId();
    this.creationDate = comment.getCreationDate();
    this.lastModifiedDate = comment.getLastModifiedDate();
  }

  /**
   * Constructs a CommentDTO object with the specified attributes.
   * @param content The content of the comment.
   * @param userId The ID of the user who made the comment.
   * @param quizId The ID of the quiz the comment is made on.
   */
  public CommentDTO(String content, Long userId, Long quizId) {
    this.content = content;
    this.userId = userId;
    this.quizId = quizId;
  }

  /**
   * Constructs a CommentDTO object with the specified attributes.
   * @param content The content of the comment.
   */
  public CommentDTO(String content) {
    this.content = content;
  }

  /*
   * Converts the DTO to a Comment entity.
   * @return The Comment entity created from the DTO.
   */
  public Comment toEntity() {
    Comment comment = new Comment();
    comment.setContent(this.content);
    comment.setCreationDate(this.creationDate);
    comment.setLastModifiedDate(this.lastModifiedDate);
    return comment;
  } 
}
