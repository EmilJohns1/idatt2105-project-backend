package com.idatt2105.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

/**
 * Represents a comment made by a user on a quiz.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Column(name = "content", nullable = false)
  private String content;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "quiz_id", nullable = false)
  private Quiz quiz;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @Column(name = "last_modified_date")
  private LocalDateTime lastModifiedDate;

  /**
   * Sets the ID of the user who made the comment.
   * 
   * @param userId The ID of the user
   */
  public void setUserId(Long userId) {
    this.user = new User();
    this.user.setId(userId);
  }

  /**
   * Sets the ID of the quiz that the comment is made on.
   * 
   * @param quizId The ID of the quiz
   */
  public void setQuizId(Long quizId) {
    this.quiz = new Quiz();
    this.quiz.setId(quizId);
  }
}
