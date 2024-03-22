package com.idatt2105.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "alternatives")
public class Alternative {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "alternative_text")
  private String alternativeText;

  @Column(name = "is_correct")
  private boolean isCorrect;

  @ManyToOne
  private MultipleChoiceQuestion question;
}
