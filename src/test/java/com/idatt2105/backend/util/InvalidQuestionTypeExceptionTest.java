package com.idatt2105.backend.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * The InvalidQuestionTypeExceptionTest class is a test class that tests the
 * InvalidQuestionTypeException class.
 */
class InvalidQuestionTypeExceptionTest {

  /**
   * This method tests the constructor of the InvalidQuestionTypeException class. It verifies that
   * the constructor sets the correct message and that the cause is null.
   */
  @Test
  void testConstructorWithMessage() {
    // Arrange
    String message = "Invalid question type";

    // Act
    InvalidQuestionTypeException exception = new InvalidQuestionTypeException(message);

    // Assert
    assertEquals(message, exception.getMessage());
    assertNull(exception.getCause());
  }

  /**
   * This method tests the constructor of the InvalidQuestionTypeException class. It verifies that
   * the constructor sets the correct message and cause.
   */
  @Test
  void testConstructorWithMessageAndCause() {
    // Arrange
    String message = "Invalid question type";
    Throwable cause = new IllegalArgumentException("Invalid argument");

    // Act
    InvalidQuestionTypeException exception = new InvalidQuestionTypeException(message, cause);

    // Assert
    assertEquals(message, exception.getMessage());
    assertEquals(cause, exception.getCause());
  }
}
