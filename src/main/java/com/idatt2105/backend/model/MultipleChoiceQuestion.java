package com.idatt2105.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "multiple_choice_questions")
public class MultipleChoiceQuestion extends Question {
  @OneToMany(mappedBy = "question")
  private List<Alternative> alternatives;
}
