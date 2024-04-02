package com.idatt2105.backend.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/** Entity representing an alternative to a Multiple Choice question. */
@Entity
@Data
@Table(name = "alternatives")
public class Alternative {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "alternative_text")
  @NotEmpty
  private String alternativeText;

  @Column(name = "is_correct", nullable = false)
  private boolean isCorrect;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "question_id", nullable = false)
  private MultipleChoiceQuestion question;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Alternative that = (Alternative) o;
    if (id == null || that.id == null) {
      return false;
    }
    return isCorrect == that.isCorrect &&
      id.equals(that.id) &&
      alternativeText.equals(that.alternativeText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, alternativeText, isCorrect);
  }
}
