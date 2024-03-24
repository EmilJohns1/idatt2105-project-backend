package com.idatt2105.backend.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class QuizAttemptDTO {
  private LocalDateTime attemptTime;
  private int score;
  private Long userId;
  private Long quizId;
  private Set<QuestionAttemptDTO> questionAttempts = new HashSet<>();
}
