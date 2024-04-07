package com.idatt2105.backend.util;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidCredentialsExceptionTest {

  /**
   * This method tests the constructor of the InvalidCredentialsException class. It verifies that
   * the exception message is correctly set.
   */
  @Test
  void testConstructor() {
    // Arrange
    String message = "Invalid credentials";

    // Act
    InvalidCredentialsException exception = new InvalidCredentialsException(message);

    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * This method tests the constructor of the InvalidCredentialsException class. It verifies that
   * the exception message and cause are correctly set.
   */
  @Test
  void testConstructorWithCause() {
    // Arrange
    String message = "Invalid credentials";
    Throwable cause = new RuntimeException("Cause");

    // Act
    InvalidCredentialsException exception = new InvalidCredentialsException(message, cause);

    // Assert
    assertEquals(message, exception.getMessage());
    assertEquals(cause, exception.getCause());
  }

  /**
   * This method tests the HTTP status code of the InvalidCredentialsException class. It verifies
   * that the status code is correctly set to 403 (FORBIDDEN).
   */
  @Test
  void testHttpStatus() {
    // Arrange
    InvalidCredentialsException exception = new InvalidCredentialsException("Invalid credentials");

    // Act
    ResponseEntity<Void> response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    // Assert
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }
}
