package com.idatt2105.backend.util;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserNotFoundExceptionTest {

  @Test
  void testConstructorWithMessage() {
    // Arrange
    String message = "User not found";

    // Act
    UserNotFoundException exception = new UserNotFoundException(message);

    // Assert
    assertEquals(message, exception.getMessage());
  }

  @Test
  void testConstructorWithMessageAndCause() {
    // Arrange
    String message = "User not found";
    Throwable cause = new Throwable("Some cause");

    // Act
    UserNotFoundException exception = new UserNotFoundException(message, cause);

    // Assert
    assertEquals(message, exception.getMessage());
    assertEquals(cause, exception.getCause());
  }

  @Test
  void testErrorResponse() {
    // Arrange
    String message = "User not found";
    UserNotFoundException exception = new UserNotFoundException(message);
    RestExceptionHandler handler = new RestExceptionHandler();

    // Act
    ResponseEntity<ErrorResponse> response = handler.handleUserNotFoundException(exception);

    // Assert
    ErrorResponse errorResponse = response.getBody();
    assertEquals(message, errorResponse.getTitle());
    assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getStatus());
    assertEquals(LocalDate.now().toString(), errorResponse.getTimestamp());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }
}
