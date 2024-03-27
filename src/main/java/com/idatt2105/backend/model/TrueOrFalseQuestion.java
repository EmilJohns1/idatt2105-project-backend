package com.idatt2105.backend.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity representing a True or False question. This class extends the Question class and adds a
 * field for the correct answer.
 */
@Entity
@Data
@Table(name = "true_or_false_questions")
public class TrueOrFalseQuestion extends Question {
  @Column(name = "correct_answer")
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
    TrueOrFalseQuestion that = (TrueOrFalseQuestion) o;
    return Objects.equals(correctAnswer, that.correctAnswer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), correctAnswer);
  }
}
