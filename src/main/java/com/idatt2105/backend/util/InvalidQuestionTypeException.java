package com.idatt2105.backend.util;

/** Exception for when an invalid question type is provided. */
public class InvalidQuestionTypeException extends RuntimeException {
  public InvalidQuestionTypeException(String message) {
    super(message);
  }

  public InvalidQuestionTypeException(String message, Throwable cause) {
    super(message, cause);
  }
}
