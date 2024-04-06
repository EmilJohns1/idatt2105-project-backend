package com.idatt2105.backend.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** ErrorResponse class for handling error responses. */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
  private String title;
  private int status;
  private String timestamp;
}
