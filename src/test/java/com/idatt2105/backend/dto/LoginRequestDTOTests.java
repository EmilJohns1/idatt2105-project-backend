package com.idatt2105.backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
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

  @Nested
  class Constructor {
    /**
     * This method tests the constructor of the LoginRequestDTO class. It verifies that the
     * constructor sets the correct values.
     */
    @Test
    void testConstructor() {
      assertEquals("User", dto.getUsername());
      assertEquals("Password", dto.getPassword());

      LoginRequestDTO newDTO = new LoginRequestDTO();
      assertEquals(null, newDTO.getUsername());
      assertEquals(null, newDTO.getPassword());
      assertNotNull(newDTO);
    }
  }

  /**
   * The EqualsAndHashCode class is a test class that tests the equals and hashCode methods of the
   * LoginRequestDTO class.
   */
  @Nested
  class EqualsAndHashCode {
    /**
     * This method tests the equals method of the LoginRequestDTO class. It verifies that the method
     * returns true when the objects are equal and false when the objects are not equal.
     */
    @Test
    void testEquals() {
      LoginRequestDTO dto1 = new LoginRequestDTO("User", "Password");
      LoginRequestDTO dto2 = new LoginRequestDTO("User", "Password");
      LoginRequestDTO dto3 = new LoginRequestDTO("User", "Password1");

      assertEquals(dto1, dto2);
      assertEquals(dto1.hashCode(), dto2.hashCode());
      assertEquals(dto1, dto1);
      assertEquals(dto1.hashCode(), dto1.hashCode());
      assertNotEquals(dto2, dto3);
      assertNotEquals(dto2.hashCode(), dto3.hashCode());
      assertNotEquals(dto1, dto3);
      assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    /**
     * This method tests the hashCode method of the LoginRequestDTO class. It verifies that the
     * method returns the correct hash code and that the hash code is the same for equal objects.
     */
    @Test
    void testHashcode() {
      LoginRequestDTO dto1 = new LoginRequestDTO("User", "Password");
      LoginRequestDTO dto2 = new LoginRequestDTO("User", "Password");
      LoginRequestDTO dto3 = new LoginRequestDTO("User", "Password1");

      assertEquals(dto1.hashCode(), dto2.hashCode());
      assertNotEquals(dto1.hashCode(), dto3.hashCode());

      assertEquals(dto1, dto2);
      assertEquals(dto1.hashCode(), dto2.hashCode());
      assertNotEquals(dto2.hashCode(), dto3.hashCode());
    }
  }

  /**
   * The ToString class is a test class that tests the toString method of the LoginRequestDTO class.
   */
  @Nested
  class ToString {
    /**
     * This method tests the toString method of the LoginRequestDTO class. It verifies that the
     * method returns the correct string representation of the object.
     */
    @Test
    void testToString() {
      assertEquals("LoginRequestDTO(username=User, password=Password, role=null)", dto.toString());
    }
  }

  /**
   * The SetRole class is a test class that tests the setRole method of the LoginRequestDTO class.
   */
  @Nested
  class testSetRole {
    /**
     * This method tests the setRole method of the LoginRequestDTO class. It verifies that the
     * method sets the correct role.
     */
    @Test
    void testSetRole() {
      dto.setRole("ROLE_USER");
      assertEquals("ROLE_USER", dto.getRole());
    }
  }
}
