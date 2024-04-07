package com.idatt2105.backend.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExistingUserExceptionTest {

  /**
   * This method tests the constructor of the ExistingUserException class. It verifies that the
   * exception message is correctly set.
   */
  @Test
  void testConstructor() {
    // Arrange
    String message = "User already exists";

    // Act
    ExistingUserException exception = new ExistingUserException(message);

    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * This method tests the constructor of the ExistingUserException class with a cause. It verifies
   * that the exception message and cause are correctly set.
   */
  @Test
  void testConstructorWithCause() {
    // Arrange
    String message = "User already exists";
    Throwable cause = new RuntimeException("Some cause");

    // Act
    ExistingUserException exception = new ExistingUserException(message, cause);

    // Assert
    assertEquals(message, exception.getMessage());
    assertEquals(cause, exception.getCause());
  }
}
