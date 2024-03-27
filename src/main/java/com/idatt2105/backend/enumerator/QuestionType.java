package com.idatt2105.backend.enumerator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.idatt2105.backend.util.InvalidQuestionTypeException;

/** Enum for question types. */
public enum QuestionType {
  TRUE_OR_FALSE("true_or_false"),
  MULTIPLE_CHOICE("multiple_choice");

  private final String value;

  QuestionType(String value) {
    this.value = value;
  }

  /**
   * Gets the enum from a string value.
   *
   * @param value (String) The string value to get the enum from.
   * @return (Type) The enum corresponding to the value.
   * @throws InvalidQuestionTypeException if the value is not a valid question type.
   */
  @JsonCreator
  public static QuestionType fromValue(String value) {
    return switch (value) {
      case "true_or_false" -> TRUE_OR_FALSE;
      case "multiple_choice" -> MULTIPLE_CHOICE;
      default -> throw new InvalidQuestionTypeException(value);
    };
  }

  @Override
  @JsonValue
  public String toString() {
    return value;
  }
}
