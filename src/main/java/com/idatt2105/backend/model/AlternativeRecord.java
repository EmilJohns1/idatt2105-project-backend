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
public class AlternativeRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "text", nullable = false)
  private String alternativeText;

  @Column(name = "was_correct", nullable = false)
  @Accessors(fluent = true)
  private boolean wasCorrect;

  @Column(name = "was_selected", nullable = false)
  @Accessors(fluent = true)
  private boolean wasSelected;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "attempt_id", nullable = false)
  private MultipleChoiceQuestionAttempt questionAttempt;

  public boolean getWasCorrect() { // Must also have regular setter/getter to make jackson work properly
    return wasCorrect;
  }

  public void setWasCorrect(boolean wasCorrect) {
    this.wasCorrect = wasCorrect;
  }

  public boolean getWasSelected() { // Must also have regular setter/getter to make jackson work properly
    return wasSelected;
  }

  public void setWasSelected(boolean wasSelected) {
    this.wasSelected = wasSelected;
  }

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

  @Override
  public String toString() {
    return "AlternativeRecord{"
        + "id="
        + id
        + ", alternativeText='"
        + alternativeText
        + '\''
        + ", wasCorrect="
        + wasCorrect
        + ", wasSelected="
        + wasSelected
        + '}';
  }
}
