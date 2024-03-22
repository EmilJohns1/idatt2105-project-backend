package com.idatt2105.backend.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ExistingUserException extends RuntimeException {
  public ExistingUserException(String message) {
    super(message);
  }

  public ExistingUserException(String message, Throwable cause) {
    super(message, cause);
  }
}
