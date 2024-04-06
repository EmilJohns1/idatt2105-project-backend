package com.idatt2105.backend.util;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The RestExceptionHandlerTest class is a test class that tests the RestExceptionHandler class. */
class RestExceptionHandlerTest {

  /**
   * This method tests the handleUserNotFoundException method of the RestExceptionHandler class. It
   * verifies that the method returns the correct response entity.
   */
  @Test
  void testHandleUserNotFoundException() {
    // Arrange
    UserNotFoundException exception = new UserNotFoundException("User not found");
    RestExceptionHandler handler = new RestExceptionHandler();

    // Act
    ResponseEntity<ErrorResponse> response = handler.handleUserNotFoundException(exception);

    // Assert
    ErrorResponse errorResponse = response.getBody();
    assertEquals("User not found", errorResponse.getTitle());
    assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getStatus());
    assertEquals(LocalDate.now().toString(), errorResponse.getTimestamp());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  /**
   * This method tests the handleExistingUserException method of the RestExceptionHandler class. It
   * verifies that the method returns the correct response entity.
   */
  @Test
  void testHandleExistingUserException() {
    // Arrange
    ExistingUserException exception = new ExistingUserException("User already exists");
    RestExceptionHandler handler = new RestExceptionHandler();

    // Act
    ResponseEntity<ErrorResponse> response = handler.handleExistingUserException(exception);

    // Assert
    ErrorResponse errorResponse = response.getBody();
    assertEquals("User already exists", errorResponse.getTitle());
    assertEquals(HttpStatus.CONFLICT.value(), errorResponse.getStatus());
    assertEquals(LocalDate.now().toString(), errorResponse.getTimestamp());
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
  }

  /**
   * This method tests the handleInvalidCredentialsException method of the RestExceptionHandler
   * class. It verifies that the method returns the correct response entity.
   */
  @Test
  void testHandleInvalidCredentialsException() {
    // Arrange
    InvalidCredentialsException exception = new InvalidCredentialsException("Invalid credentials");
    RestExceptionHandler handler = new RestExceptionHandler();

    // Act
    ResponseEntity<ErrorResponse> response = handler.handleInvalidCredentialsException(exception);

    // Assert
    ErrorResponse errorResponse = response.getBody();
    assertEquals("Invalid credentials", errorResponse.getTitle());
    assertEquals(HttpStatus.UNAUTHORIZED.value(), errorResponse.getStatus());
    assertEquals(LocalDate.now().toString(), errorResponse.getTimestamp());
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
  }

  /**
   * This method tests the handleInvalidIdException method of the RestExceptionHandler class. It
   * verifies that the method returns the correct response entity.
   */
  @Test
  void testHandleQuizNotFoundException() {
    // Arrange
    InvalidIdException exception = new InvalidIdException("Invalid quiz ID");
    RestExceptionHandler handler = new RestExceptionHandler();

    // Act
    ResponseEntity<ErrorResponse> response = handler.handleQuizNotFoundException(exception);

    // Assert
    ErrorResponse errorResponse = response.getBody();
    assertEquals("Invalid quiz ID", errorResponse.getTitle());
    assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getStatus());
    assertEquals(LocalDate.now().toString(), errorResponse.getTimestamp());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  /**
   * This method tests the handleInvalidQuestionTypeException method of the RestExceptionHandler
   * class. It verifies that the method returns the correct response entity.
   */
  @Test
  void testHandleInvalidQuestionTypeException() {
    // Arrange
    InvalidQuestionTypeException exception =
        new InvalidQuestionTypeException("Invalid question type");
    RestExceptionHandler handler = new RestExceptionHandler();

    // Act
    ResponseEntity<ErrorResponse> response = handler.handleInvalidQuestionTypeException(exception);

    // Assert
    ErrorResponse errorResponse = response.getBody();
    assertEquals("Invalid question type", errorResponse.getTitle());
    assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatus());
    assertEquals(LocalDate.now().toString(), errorResponse.getTimestamp());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }
}
