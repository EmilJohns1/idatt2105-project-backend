package com.idatt2105.backend.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.idatt2105.backend.model.Quiz;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class QuizDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
    private Set<UserDTO> userDTOs;

    public QuizDTO(Quiz quiz) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.description = quiz.getDescription();
        this.creationDate = quiz.getCreationDate();
        this.lastModifiedDate = quiz.getLastModifiedDate();
    }

    public QuizDTO(Long id, String title, String description, LocalDateTime creationDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public QuizDTO(Long id, String title, String description, LocalDateTime creationDate, LocalDateTime lastModifiedDate, Set<UserDTO> userDTOs) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.creationDate = creationDate;
      this.lastModifiedDate = lastModifiedDate;
      this.userDTOs = userDTOs;
    }

    public QuizDTO(Long id, String title, String description, Set<UserDTO> userDTOs) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Quiz toEntity() {
      Quiz quiz = new Quiz();
      quiz.setId(this.id);
      quiz.setTitle(this.title);
      quiz.setDescription(this.description);
      return quiz;
  }
    
}
