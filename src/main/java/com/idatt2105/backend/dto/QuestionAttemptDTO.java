package com.idatt2105.backend.dto;

import com.idatt2105.backend.model.AlternativeRecord;
import com.idatt2105.backend.model.QuestionType;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class QuestionAttemptDTO {
  private QuestionType type;
  private String questionText;
  private String mediaUrl;
  private String category;
  private Set<AlternativeRecord> alternatives = new HashSet<>();
  private Boolean userAnswer;
}
