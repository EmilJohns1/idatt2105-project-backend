package com.idatt2105.backend.dto;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** The ResetRequestDTOTest class is a test class that tests the ResetRequestDTO class. */
class ResetRequestDTOTest {

  /**
   * The testConstructorGetterAndSetter class is a test class that tests the constructor, getters
   * and setters of the ResetRequestDTO class.
   */
  @Nested
  class testConstructorGetterAndSetter {
    /**
     * This method tests the constructor and the getters of the ResetRequestDTO class. It verifies
     * that the constructor sets the correct values and that the getters return the correct values.
     */
    @Test
    public void testConstructorAndGetters() {
      // Arrange
      String username = "testUser";
      String password = "testPassword";
      String token = "testToken";

      // Act
      ResetRequestDTO resetRequestDTO = new ResetRequestDTO(username, password, token);
      ResetRequestDTO resetRequestDTO2 = new ResetRequestDTO();

      // Assert
      assertEquals(username, resetRequestDTO.getUsername());
      assertEquals(password, resetRequestDTO.getPassword());
      assertEquals(token, resetRequestDTO.getToken());
      assertNull(resetRequestDTO2.getUsername());
      assertNull(resetRequestDTO2.getPassword());
      assertNull(resetRequestDTO2.getToken());
      assertNotNull(resetRequestDTO2);
    }

    /**
     * This method tests the setters of the ResetRequestDTO class. It verifies that the setters set
     * the correct values.
     */
    @Test
    public void testSetters() {
      // Arrange
      ResetRequestDTO resetRequestDTO = new ResetRequestDTO();
      String username = "testUser";
      String password = "testPassword";
      String token = "testToken";

      // Act
      resetRequestDTO.setUsername(username);
      resetRequestDTO.setPassword(password);
      resetRequestDTO.setToken(token);

      // Assert
      assertEquals(username, resetRequestDTO.getUsername());
      assertEquals(password, resetRequestDTO.getPassword());
      assertEquals(token, resetRequestDTO.getToken());
    }
  }

  /**
   * The EqualsAndHashCodeAndtoString class is a test class that tests the equals, canEqual,
   * hashCode, and toString methods of the ResetRequestDTO class.
   */
  @Nested
  class EqualsAndHashCodeAndtoString {

    @Test
    void testEqualsHashCodeToStringCanEquals() {
      // Arrange
      ResetRequestDTO resetRequestDTO1 = new ResetRequestDTO();
      resetRequestDTO1.setUsername("testUser");
      resetRequestDTO1.setPassword("testPassword");
      resetRequestDTO1.setToken("testToken");

      ResetRequestDTO resetRequestDTO2 = new ResetRequestDTO();
      resetRequestDTO2.setUsername("testUser");
      resetRequestDTO2.setPassword("testPassword");
      resetRequestDTO2.setToken("testToken");

      ResetRequestDTO resetRequestDTO3 = new ResetRequestDTO();
      resetRequestDTO3.setUsername("testUser2");
      resetRequestDTO3.setPassword("testPassword2");
      resetRequestDTO3.setToken("testToken2");

      // Act & Assert
      assertEquals(resetRequestDTO1, resetRequestDTO2);
      assertEquals(resetRequestDTO1.getPassword(), resetRequestDTO2.getPassword());
      assertNotEquals(resetRequestDTO1, resetRequestDTO3);

      assertEquals(resetRequestDTO1.hashCode(), resetRequestDTO2.hashCode());
      assertNotEquals(resetRequestDTO1.hashCode(), resetRequestDTO3.hashCode());
      assertNotNull(resetRequestDTO1.toString());
      assertNotNull(resetRequestDTO1);
      assertNotNull(resetRequestDTO1.hashCode());
      assertNotNull(resetRequestDTO1.canEqual(resetRequestDTO2));
      assertNotNull(resetRequestDTO1.equals(resetRequestDTO2));

      assertEquals(
          "ResetRequestDTO(username=testUser, password=testPassword, token=testToken)",
          resetRequestDTO1.toString());

      assertTrue(resetRequestDTO1.canEqual(resetRequestDTO3));
    }
  }
}
