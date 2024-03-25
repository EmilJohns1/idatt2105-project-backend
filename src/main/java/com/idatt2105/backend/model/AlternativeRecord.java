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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@NoArgsConstructor
@Accessors(fluent = true)
public class AlternativeRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "text", nullable = false)
  private String alternativeText;

  @Column(name = "was_correct", nullable = false)
  private boolean wasCorrect;

  @Column(name = "was_selected", nullable = false)
  private boolean wasSelected;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "attempt_id", nullable = false)
  private MultipleChoiceQuestionAttempt questionAttempt;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlternativeRecord that = (AlternativeRecord) o;
    return wasCorrect == that.wasCorrect
        && wasSelected == that.wasSelected
        && Objects.equals(id, that.id)
        && Objects.equals(alternativeText, that.alternativeText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, alternativeText, wasCorrect, wasSelected);
  }
}
