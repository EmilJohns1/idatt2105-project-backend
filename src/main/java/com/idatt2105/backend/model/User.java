package com.idatt2105.backend.model;

import io.swagger.v3.oas.annotations.Hidden;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(exclude = "quizzes")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password")
  private String password;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinTable(name = "user_quiz",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "quiz_id", referencedColumnName = "id"))
  @JsonManagedReference
  private Set<Quiz> quizzes = new HashSet<>();

  public User (String username, String password) {
      this.username = username;
      this.password = password;
  }

  public User(Long id, String username) {
    this.id = id;
    this.username = username;
  }

  public void addQuiz(Quiz quiz) {
      this.quizzes.add(quiz);
      quiz.getUsers().add(this);
  }
}