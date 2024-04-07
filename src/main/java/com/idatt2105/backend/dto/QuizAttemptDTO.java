package com.idatt2105.backend.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.idatt2105.backend.util.NoNullElements;

import lombok.Data;

/**
 * A Data Transfer Object (DTO) class representing a quiz attempt. This class is used to transfer
 * quiz attempt data between different layers of the application.
 */
@Data
public class QuizAttemptDTO {
  private String title;
  private LocalDateTime attemptTime;
  private int score;
  private Long userId;
  private Long quizId;
  @NoNullElements private Set<QuestionAttemptDTO> questionAttempts = new HashSet<>();
}
