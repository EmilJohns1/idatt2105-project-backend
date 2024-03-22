package com.idatt2105.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

/**
 * Represents a quiz.
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "quizzes")
public class Quiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @Column(name = "last_modified_date")
  private LocalDateTime lastModifiedDate;

  @ManyToMany(mappedBy = "quizzes")
  private Set<User> users = new HashSet<>();
}