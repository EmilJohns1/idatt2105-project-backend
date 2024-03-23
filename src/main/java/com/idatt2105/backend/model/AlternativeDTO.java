package com.idatt2105.backend.model;

import lombok.Data;

@Data
public class AlternativeDTO {
  private Long id;
  private String alternativeText;
  private boolean isCorrect;
  private Long questionId;
}