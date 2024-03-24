package com.idatt2105.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Represents a login request data transfer object. */
@Data
@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDTO {
  private String username;
  private String password;

  /**
   * Constructs a new LoginRequestDTO with the specified username and password.
   *
   * @param username the username
   * @param password the password
   */
  public LoginRequestDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
