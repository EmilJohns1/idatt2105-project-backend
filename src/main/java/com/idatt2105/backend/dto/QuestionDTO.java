package com.idatt2105.backend.dto;

import com.idatt2105.backend.enumerator.QuestionType;
import com.idatt2105.backend.model.MultipleChoiceQuestion;
import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.model.TrueOrFalseQuestion;

import lombok.Data;
import lombok.experimental.Accessors;

/** Data Transfer Object (DTO) for questions. Contains an enum for question types. */
@Data
public class QuestionDTO {
  private Long questionId;
  private String questionText;
  private String mediaUrl;
  private int points;
  private Long quizId;
  private QuestionType type;

  @Accessors(fluent = true)
  private Boolean isCorrect;

  /**
   * Instantiates a Question object based on the type.
   *
   * @return (Question) A new instance of the class specified by the type field.
   * @see Question
   */
  public Question instantiateQuestion() {
    return switch (type) {
      case TRUE_OR_FALSE -> new TrueOrFalseQuestion();
      case MULTIPLE_CHOICE -> new MultipleChoiceQuestion();
    };
  }

  public Boolean
      getIsCorrect() { // Must also have regular setter/getter to make jackson work properly
    return isCorrect;
  }

  public void setIsCorrect(Boolean isCorrect) {
    this.isCorrect = isCorrect;
  }
}
