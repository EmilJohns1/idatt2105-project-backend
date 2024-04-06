package com.idatt2105.backend.dto;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The PasswordResetTokenDTOTest class is a test class that tests the PasswordResetTokenDTO class.
 */
class PasswordResetTokenDTOTest {

  /**
   * This method tests the constructor and the getters of the PasswordResetTokenDTO class. It
   * verifies that the constructor sets the correct values and that the getters return the correct
   * values.
   */
  @Test
  void testConstructorAndGetters() {
    // Arrange
    String token = "abc123";
    String email = "test@example.com";
    LocalDateTime expirationDateTime = LocalDateTime.now();

    // Act
    PasswordResetTokenDTO passwordResetTokenDTO =
        new PasswordResetTokenDTO(token, email, expirationDateTime);

    // Assert
    assertNotNull(passwordResetTokenDTO);
    assertEquals(token, passwordResetTokenDTO.getToken());
    assertEquals(email, passwordResetTokenDTO.getEmail());
    assertEquals(expirationDateTime, passwordResetTokenDTO.getExpirationDateTime());
  }

  /**
   * This method tests the setters of the PasswordResetTokenDTO class. It verifies that the setters
   * set the correct values.
   */
  @Test
  void testSetters() {
    // Arrange
    PasswordResetTokenDTO passwordResetTokenDTO = new PasswordResetTokenDTO();

    // Act
    String token = "abc123";
    String email = "test@example.com";
    LocalDateTime expirationDateTime = LocalDateTime.now();

    passwordResetTokenDTO.setToken(token);
    passwordResetTokenDTO.setEmail(email);
    passwordResetTokenDTO.setExpirationDateTime(expirationDateTime);

    // Assert
    assertEquals(token, passwordResetTokenDTO.getToken());
    assertEquals(email, passwordResetTokenDTO.getEmail());
    assertEquals(expirationDateTime, passwordResetTokenDTO.getExpirationDateTime());
  }

  @Test
  void testEquals() {
    // Arrange
    String token = "abc123";
    String email = "test@example.com";
    LocalDateTime expirationDateTime = LocalDateTime.now();

    PasswordResetTokenDTO passwordResetTokenDTO1 =
        new PasswordResetTokenDTO(token, email, expirationDateTime);
    PasswordResetTokenDTO passwordResetTokenDTO2 =
        new PasswordResetTokenDTO(token, email, expirationDateTime);
    PasswordResetTokenDTO passwordResetTokenDTO3 =
        new PasswordResetTokenDTO("def456", email, expirationDateTime);

    // Act & Assert
    assertEquals(passwordResetTokenDTO1, passwordResetTokenDTO1); // Reflexive
    assertEquals(passwordResetTokenDTO1, passwordResetTokenDTO2); // Symmetric
    assertEquals(passwordResetTokenDTO2, passwordResetTokenDTO1); // Symmetric
    assertNotEquals(passwordResetTokenDTO1, passwordResetTokenDTO3); // Different token
    assertNotEquals(passwordResetTokenDTO1, null); // Not null
  }

  @Test
  void testHashCode() {
    // Arrange
    String token = "abc123";
    String email = "test@example.com";
    LocalDateTime expirationDateTime = LocalDateTime.now();

    PasswordResetTokenDTO passwordResetTokenDTO1 =
        new PasswordResetTokenDTO(token, email, expirationDateTime);
    PasswordResetTokenDTO passwordResetTokenDTO2 =
        new PasswordResetTokenDTO(token, email, expirationDateTime);

    // Act & Assert
    assertEquals(passwordResetTokenDTO1.hashCode(), passwordResetTokenDTO2.hashCode());
  }

  @Test
  void testToString() {
    // Arrange
    String token = "abc123";
    String email = "test@example.com";
    LocalDateTime expirationDateTime = LocalDateTime.now();

    PasswordResetTokenDTO passwordResetTokenDTO =
        new PasswordResetTokenDTO(token, email, expirationDateTime);

    // Act
    String toStringResult = passwordResetTokenDTO.toString();

    // Assert
    assertTrue(toStringResult.contains("token=" + token));
    assertTrue(toStringResult.contains("email=" + email));
    assertTrue(toStringResult.contains("expirationDateTime=" + expirationDateTime));
  }
}
