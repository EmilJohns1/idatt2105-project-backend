package com.idatt2105.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TrueOrFalseQuestionAttempt extends QuestionAttempt {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_answer", nullable = false)
  private Boolean userAnswer;

  @Column(name = "correct_answer", nullable = false)
  private Boolean correctAnswer;
}
