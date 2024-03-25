package com.idatt2105.backend.dto;

import com.idatt2105.backend.util.NoNullElements;
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
  @NoNullElements
  private Set<QuestionAttemptDTO> questionAttempts = new HashSet<>();
}
