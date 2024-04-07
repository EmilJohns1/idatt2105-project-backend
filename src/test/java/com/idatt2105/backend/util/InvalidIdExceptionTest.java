package com.idatt2105.backend.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The InvalidIdExceptionTest class is a test class that tests the InvalidIdException class. */
class InvalidIdExceptionTest {

  /**
   * This method tests the constructor of the InvalidIdException class. It verifies that the
   * exception message is correctly set.
   */
  @Test
  void testConstructor() {
    // Arrange
    String message = "Invalid ID";

    // Act
    InvalidIdException exception = new InvalidIdException(message);

    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * This method tests the constructor of the InvalidIdException class with a cause. It verifies
   * that the exception message and cause are correctly set.
   */
  @Test
  void testConstructorWithCause() {
    // Arrange
    String message = "Invalid ID";
    Throwable cause = new RuntimeException("Cause");

    // Act
    InvalidIdException exception = new InvalidIdException(message, cause);

    // Assert
    assertEquals(message, exception.getMessage());
    assertEquals(cause, exception.getCause());
  }
}
