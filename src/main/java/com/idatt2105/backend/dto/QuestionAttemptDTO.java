package com.idatt2105.backend.dto;

import java.util.HashSet;
import java.util.Set;

import com.idatt2105.backend.enumerator.QuestionType;
import com.idatt2105.backend.model.AlternativeRecord;
import com.idatt2105.backend.model.MultipleChoiceQuestionAttempt;
import com.idatt2105.backend.model.QuestionAttempt;
import com.idatt2105.backend.model.TrueOrFalseQuestionAttempt;

import lombok.Data;

@Data
public class QuestionAttemptDTO {
  private QuestionType type;
  private String questionText;
  private String mediaUrl;
  private String category;
  private int points;
  private Set<AlternativeRecord> alternatives = new HashSet<>();
  private Boolean userAnswer;
  private Boolean correctAnswer;

  public QuestionAttempt instantiateQuestionAttempt() {
    return switch (type) {
      case TRUE_OR_FALSE -> new TrueOrFalseQuestionAttempt();
      case MULTIPLE_CHOICE -> new MultipleChoiceQuestionAttempt();
    };
  }
}
