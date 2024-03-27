package com.idatt2105.backend.dto;

import java.time.LocalDateTime;

import com.idatt2105.backend.model.Comment;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A Data Transfer Object (DTO) class representing a comment. This class is used to transfer comment
 * data between different layers of the application.
 */
@NoArgsConstructor
@Getter
@Setter
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
   *
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
