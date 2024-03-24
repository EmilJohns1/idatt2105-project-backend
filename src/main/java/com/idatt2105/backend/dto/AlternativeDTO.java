package com.idatt2105.backend.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for Alternative.
 */
@Data
public class AlternativeDTO {
  private Long id;
  private String alternativeText;
  private boolean isCorrect;
  private Long questionId;
}