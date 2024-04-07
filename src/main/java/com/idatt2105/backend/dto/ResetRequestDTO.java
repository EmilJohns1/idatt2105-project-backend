package com.idatt2105.backend.dto;

import lombok.*;

/** Represents a reset request data transfer object. */
@Data
@NoArgsConstructor
@Getter
@Setter
public class ResetRequestDTO {
  private String username;
  private String password;
  private String token;

  /**
   * Constructs a new ResetRequestDTO with the specified username, password and token.
   *
   * @param username the username
   * @param password the password
   * @param token the token
   */
  public ResetRequestDTO(String username, String password, String token) {
    this.username = username;
    this.password = password;
    this.token = token;
  }
}
