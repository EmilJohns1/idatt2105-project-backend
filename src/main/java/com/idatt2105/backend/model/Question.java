package com.idatt2105.backend.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idatt2105.backend.dto.QuestionDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/** Entity representing a question for a quiz. */
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "questions")
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "question_text")
  @NotEmpty
  private String questionText;

  @Column(name = "media_url")
  private String mediaUrl;

  @Column(name = "points")
  private int points;

  @ManyToOne
  @JoinColumn(name = "quiz_id", nullable = false)
  @JsonIgnore
  private Quiz quiz;

  public void extractFromDTO(QuestionDTO dto) {
    this.questionText = dto.getQuestionText();
    this.mediaUrl = dto.getMediaUrl();
    this.points = dto.getPoints();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Question question = (Question) o;
    Long thisQuizId;
    Long thatQuizId;

    thisQuizId = quiz == null ? null : quiz.getId();
    thatQuizId = question.quiz == null ? null : question.quiz.getId();

    return points == question.points
        && Objects.equals(id, question.id)
        && Objects.equals(questionText, question.questionText)
        && Objects.equals(mediaUrl, question.mediaUrl)
        && Objects.equals(thisQuizId, thatQuizId);
  }

  @Override
  public int hashCode() {
    Long quizId = quiz == null ? null : quiz.getId();
    return Objects.hash(id, questionText, mediaUrl, points, quizId);
  }
}
