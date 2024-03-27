package com.idatt2105.backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginRequestDTOTests {
  private LoginRequestDTO dto;

  @BeforeEach
  void setUP() {
    dto = new LoginRequestDTO("User", "Password");
  }

  @Nested
  class Getters {
    @Test
    void getUsernameTest() {
      assertEquals("User", dto.getUsername());
    }

    @Test
    void getPasswordTest() {
      assertEquals("Password", dto.getPassword());
    }
  }

  @Nested
  class Setters {
    @Test
    void setUsernameTest() {
      dto.setUsername("NewUser");
      assertEquals("NewUser", dto.getUsername());
    }

    @Test
    void setPasswordTest() {
      dto.setPassword("NewPassword");
      assertEquals("NewPassword", dto.getPassword());
    }
  }
}
