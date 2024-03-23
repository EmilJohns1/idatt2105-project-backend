package com.idatt2105.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.idatt2105.backend.util.InvalidQuestionTypeException;
import java.util.Set;
import lombok.Data;

/**
 * Data transfer object for questions.
 */
@Data
public class QuestionDTO {
  /**
   * Enum for question types.
   */
  private enum Type {
    TRUE_OR_FALSE("true_or_false"),
    MULTIPLE_CHOICE("multiple_choice");

    private final String value;
    Type(String value) {
      this.value = value;
    }

    @JsonCreator
    public static Type fromValue(String value) {
      return switch (value) {
        case "true_or_false" -> TRUE_OR_FALSE;
        case "multiple_choice" -> MULTIPLE_CHOICE;
        default -> throw new InvalidQuestionTypeException(value);
      };
    }

    @Override
    public String toString() {
      return value;
    }
  }
  private Long quizId;
  private String questionText;
  private Type type;
  private Long questionId;
  private String mediaUrl;
  private String category;
  private Set<Tag> tags;

  /**
   * Gets a new instance of the class specified by the type field.
   *
   * @return A new instance of the class specified by the type field.
   */
  public Question instantiateQuestion() {
    return switch (type) {
      case TRUE_OR_FALSE -> new TrueOrFalseQuestion();
      case MULTIPLE_CHOICE -> new MultipleChoiceQuestion();
    };
  }
}
