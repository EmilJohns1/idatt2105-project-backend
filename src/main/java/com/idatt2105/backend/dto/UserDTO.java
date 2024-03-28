package com.idatt2105.backend.dto;

import java.util.List;

import com.idatt2105.backend.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for User entities. This class is used to transfer User data between the
 * frontend and the backend.
 */
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserDTO {
  private Long id;
  private String username;
  private List<QuizDTO> quizzes;
  private String profilePictureUrl;

  /*
   * Constructor for UserDTO with User entity.
   */
  public UserDTO(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
  }

  /*
   * Constructor for UserDTO with parameters.
   */
  public UserDTO(Long id, String username) {
    this.id = id;
    this.username = username;
  }

  /*
   * Constructor for UserDTO with parameters.
   */
  public UserDTO(Long id, String username, List<QuizDTO> quizzes) {
    this.id = id;
    this.username = username;
    this.quizzes = quizzes;
  }

  /**
   * Constructor for UserDTO with parameters.
   *
   * @param id The ID of the user
   * @param username The username of the user
   * @param profilePictureUrl The URL of the user's profile picture
   */
  public UserDTO(Long id, String username, String profilePictureUrl) {
    this.id = id;
    this.username = username;
    this.profilePictureUrl = profilePictureUrl;
  }

  /*
   * Convert UserDTO to User entity.
   */
  public User toEntity() {
    User user = new User();
    user.setId(this.id);
    user.setUsername(this.username);
    return user;
  }
}
