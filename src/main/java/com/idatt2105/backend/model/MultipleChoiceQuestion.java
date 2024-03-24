package com.idatt2105.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Data;

/**
 * Entity representing a Multiple Choice question, with a number of alternatives.
 */
@Entity
@Data
@Table(name = "multiple_choice_questions")
public class MultipleChoiceQuestion extends Question {
  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Alternative> alternatives = new HashSet<>();

  /**
   * Adds an alternative to the Multiple Choice question.
   *
   * @param alternative (AlternativeDTO) The alternative to add.
   */
  public Alternative addAlternative(AlternativeDTO alternative) {
    Alternative newAlternative = new Alternative();
    newAlternative.setAlternativeText(alternative.getAlternativeText());
    newAlternative.setCorrect(alternative.isCorrect());
    newAlternative.setQuestion(this);
    alternatives.add(newAlternative);
    return newAlternative;
  }
}