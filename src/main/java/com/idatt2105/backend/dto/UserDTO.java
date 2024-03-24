package com.idatt2105.backend.dto;

import java.util.List;

import com.idatt2105.backend.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class UserDTO {
  private Long id;
  private String username;
  private List<QuizDTO> quizzes;

  public UserDTO(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
  }

  public UserDTO(Long id, String username) {
    this.id = id;
    this.username = username;
  }

  public UserDTO(Long id, String username, List<QuizDTO> quizzes) {
    this.id = id;
    this.username = username;
    this.quizzes = quizzes;
  }

  public User toEntity() {
    User user = new User();
    user.setId(this.id);
    user.setUsername(this.username);
    return user;
  }
}
