package com.idatt2105.backend.model;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    TrueOrFalseQuestionAttempt that = (TrueOrFalseQuestionAttempt) o;
    return Objects.equals(id, that.id)
        && Objects.equals(userAnswer, that.userAnswer)
        && Objects.equals(correctAnswer, that.correctAnswer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, userAnswer, correctAnswer);
  }
}
