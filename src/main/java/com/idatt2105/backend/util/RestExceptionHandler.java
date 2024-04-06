package com.idatt2105.backend.util;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** Exception handler for the REST API. */
@ControllerAdvice
public class RestExceptionHandler {
  /**
   * Handles UserNotFoundException.
   *
   * @param e The UserNotFoundException.
   * @return The response entity with the error response.
   */
  @ExceptionHandler(UserNotFoundException.class)
  ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTitle(e.getMessage());
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    errorResponse.setTimestamp(LocalDate.now().toString());
    return ResponseEntity.status(404).body(errorResponse);
  }

  /**
   * Handles ExistingUserException.
   *
   * @param e The ExistingUserException.
   * @return The response entity with the error response.
   */
  @ExceptionHandler(ExistingUserException.class)
  ResponseEntity<ErrorResponse> handleExistingUserException(ExistingUserException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTitle(e.getMessage());
    errorResponse.setStatus(HttpStatus.CONFLICT.value());
    errorResponse.setTimestamp(LocalDate.now().toString());
    return ResponseEntity.status(409).body(errorResponse);
  }

  /**
   * Handles InvalidCredentialsException.
   *
   * @param e The InvalidCredentialsException.
   * @return The response entity with the error response.
   */
  @ExceptionHandler(InvalidCredentialsException.class)
  ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTitle(e.getMessage());
    errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    errorResponse.setTimestamp(LocalDate.now().toString());
    return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
  }

  /**
   * Handles InvalidIdException.
   *
   * @param e The InvalidIdException.
   * @return The response entity with the error response.
   */
  @ExceptionHandler(InvalidIdException.class)
  ResponseEntity<ErrorResponse> handleQuizNotFoundException(InvalidIdException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTitle(e.getMessage());
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    errorResponse.setTimestamp(LocalDate.now().toString());
    return ResponseEntity.status(404).body(errorResponse);
  }

  /**
   * Handles InvalidQuestionTypeException.
   *
   * @param e The InvalidQuestionTypeException.
   * @return The response entity with the error response.
   */
  @ExceptionHandler(InvalidQuestionTypeException.class)
  ResponseEntity<ErrorResponse> handleInvalidQuestionTypeException(InvalidQuestionTypeException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTitle(e.getMessage());
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setTimestamp(LocalDate.now().toString());
    return ResponseEntity.status(400).body(errorResponse);
  }
}
