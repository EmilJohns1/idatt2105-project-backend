package com.idatt2105.backend.model;

import com.idatt2105.backend.util.NoNullElements;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Data;

@Entity
@Table(name = "multiple_choice_question_attempts")
@Data
public class MultipleChoiceQuestionAttempt extends QuestionAttempt {
  @OneToMany(mappedBy = "questionAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
  @NoNullElements
  private Set<AlternativeRecord> alternatives = new HashSet<>();

  public void addAlternative(@NotNull AlternativeRecord alternative) {
    this.alternatives.add(alternative);
  }

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
    MultipleChoiceQuestionAttempt that = (MultipleChoiceQuestionAttempt) o;
    return Objects.equals(alternatives, that.alternatives);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), alternatives);
  }
}
