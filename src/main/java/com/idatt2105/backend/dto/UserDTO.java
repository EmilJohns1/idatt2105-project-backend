package com.idatt2105.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

  @JsonIgnore
  private String role;

  /**
   * Constructor for UserDTO with User entity.
   *
   * @param user the user entity
   */
  public UserDTO(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
  }

  /**
   * Constructor for UserDTO with parameters.
   *
   * @param id the id of the user
   * @param username the username of the user
   */
  public UserDTO(Long id, String username) {
    this.id = id;
    this.username = username;
  }

  /**
   * Constructor for UserDTO with parameters.
   *
   * @param id the id of the user
   * @param username the username of the user
   * @param quizzes the quizzes of the user
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

  /**
   * Constructor for UserDTO with parameters.
   *
   * @param id The ID of the user
   * @param username The username of the user
   * @param profilePictureUrl The URL of the user's profile picture
   * @param quizzes The quizzes of the user
   */
  public UserDTO(Long id, String username, String profilePictureUrl, List<QuizDTO> quizzes) {
    this.id = id;
    this.username = username;
    this.profilePictureUrl = profilePictureUrl;
    this.quizzes = quizzes;
  }

  /**
   * Convert UserDTO to User entity.
   *
   * @return the User entity
   */
  public User toEntity() {
    User user = new User();
    user.setId(this.id);
    user.setUsername(this.username);
    return user;
  }
}
