package com.idatt2105.backend.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idatt2105.backend.dto.QuestionAttemptDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/** Log entity for a question attempt. */
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class QuestionAttempt {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "question_text")
  private String questionText;

  private String mediaUrl;
  private int points;

  @ManyToOne
  @JoinColumn(name = "quiz_attempt_id", nullable = false)
  @JsonIgnore
  private QuizAttempt quizAttempt;

  public void extractFromDTO(QuestionAttemptDTO dto) {
    this.questionText = dto.getQuestionText();
    this.mediaUrl = dto.getMediaUrl();
    this.points = dto.getPoints();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QuestionAttempt that = (QuestionAttempt) o;
    return points == that.points
        && Objects.equals(id, that.id)
        && Objects.equals(questionText, that.questionText)
        && Objects.equals(mediaUrl, that.mediaUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionText, mediaUrl, points);
  }
}
