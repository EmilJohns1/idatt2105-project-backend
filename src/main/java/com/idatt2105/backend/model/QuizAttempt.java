package com.idatt2105.backend.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

/** Represents a quiz attempt made by a user. */
@Entity
@Data
public class QuizAttempt {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(name = "attempt_time")
  private LocalDateTime attemptTime;

  @Column(name = "score")
  private int score;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @JsonIgnore
  private User user;

  private Long quizId;

  @OneToMany(mappedBy = "quizAttempt", cascade = CascadeType.ALL)
  private Set<QuestionAttempt> questionAttempts = new HashSet<>();
}
