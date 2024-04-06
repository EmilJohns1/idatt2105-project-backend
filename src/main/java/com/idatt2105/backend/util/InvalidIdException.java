package com.idatt2105.backend.util;

/** Exception for when an invalid ID is provided. */
public class InvalidIdException extends RuntimeException {
  public InvalidIdException(String message) {
    super(message);
  }

  public InvalidIdException(String message, Throwable cause) {
    super(message, cause);
  }
}
