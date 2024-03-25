package com.idatt2105.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity representing a True or False question.
 * This class extends the Question class and adds a field for the correct answer.
 */
@Entity
@Data
@Table(name = "true_or_false_questions")
public class TrueOrFalseQuestion extends Question {
  @Column(name = "correct_answer")
  private Boolean correctAnswer;
}