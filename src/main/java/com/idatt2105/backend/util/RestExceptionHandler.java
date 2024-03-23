package com.idatt2105.backend.util;

import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler(UserNotFoundException.class)
  ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTitle("User not found");
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    errorResponse.setTimestamp(LocalDate.now().toString());
    return ResponseEntity.status(404).body(errorResponse);
  }

  @ExceptionHandler(ExistingUserException.class)
  ResponseEntity<ErrorResponse> handleExistingUserException(ExistingUserException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTitle("User already exists");
    errorResponse.setStatus(HttpStatus.CONFLICT.value());
    errorResponse.setTimestamp(LocalDate.now().toString());
    return ResponseEntity.status(409).body(errorResponse);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTitle("Unauthorized");
    errorResponse.setStatus(HttpStatus.FORBIDDEN.value());
    errorResponse.setTimestamp(LocalDate.now().toString());
    return ResponseEntity.status(403).body(errorResponse);
  }

  @ExceptionHandler(QuizNotFoundException.class)
  ResponseEntity<ErrorResponse> handleQuizNotFoundException(QuizNotFoundException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTitle(e.getMessage());
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    errorResponse.setTimestamp(LocalDate.now().toString());
    return ResponseEntity.status(404).body(errorResponse);
  }

  @ExceptionHandler(InvalidQuestionTypeException.class)
  ResponseEntity<ErrorResponse> handleInvalidQuestionTypeException(InvalidQuestionTypeException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTitle("Invalid question type");
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setTimestamp(LocalDate.now().toString());
    return ResponseEntity.status(400).body(errorResponse);
  }
}
