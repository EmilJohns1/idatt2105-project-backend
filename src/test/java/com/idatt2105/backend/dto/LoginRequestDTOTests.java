package com.idatt2105.backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The LoginRequestDTOTests class is a test class that tests the LoginRequestDTO class. */
class LoginRequestDTOTests {
  private LoginRequestDTO dto;

  @BeforeEach
  void setUP() {
    dto = new LoginRequestDTO("User", "Password");
  }

  /** The Getters class is a test class that tests the getters of the LoginRequestDTO class. */
  @Nested
  class Getters {
    /**
     * This method tests the getUsername method of the LoginRequestDTO class. It verifies that the
     * method returns the correct username.
     */
    @Test
    void getUsernameTest() {
      assertEquals("User", dto.getUsername());
    }

    /**
     * This method tests the getPassword method of the LoginRequestDTO class. It verifies that the
     * method returns the correct password.
     */
    @Test
    void getPasswordTest() {
      assertEquals("Password", dto.getPassword());
    }
  }

  /** The Setters class is a test class that tests the setters of the LoginRequestDTO class. */
  @Nested
  class Setters {
    /**
     * This method tests the setUsername method of the LoginRequestDTO class. It verifies that the
     * method sets the correct username.
     */
    @Test
    void setUsernameTest() {
      dto.setUsername("NewUser");
      assertEquals("NewUser", dto.getUsername());
    }

    /**
     * This method tests the setPassword method of the LoginRequestDTO class. It verifies that the
     * method sets the correct password.
     */
    @Test
    void setPasswordTest() {
      dto.setPassword("NewPassword");
      assertEquals("NewPassword", dto.getPassword());
    }
  }
}
