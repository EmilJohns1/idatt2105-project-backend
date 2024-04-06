package com.idatt2105.backend.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/** The PasswordResetTokenTest class is a test class that tests the PasswordResetToken class. */
@ExtendWith(MockitoExtension.class)
class PasswordResetTokenTest {
  /**
   * This method tests the constructor and the getters of the PasswordResetToken class. It verifies
   * that the constructor sets the correct values and that the getters return the correct values.
   */
  @Test
  void testConstructorAndGetters() {
    // Arrange
    Long id = 1L;
    String token = "abc123";
    String email = "test@example.com";
    LocalDateTime expirationDateTime = LocalDateTime.now();

    // Act
    PasswordResetToken passwordResetToken =
        new PasswordResetToken(id, token, email, expirationDateTime);

    // Assert
    assertNotNull(passwordResetToken);
    assertEquals(id, passwordResetToken.getId());
    assertEquals(token, passwordResetToken.getToken());
    assertEquals(email, passwordResetToken.getEmail());
    assertEquals(expirationDateTime, passwordResetToken.getExpirationDateTime());
  }

  /**
   * This method tests the setters of the PasswordResetToken class. It verifies that the setters set
   * the correct values.
   */
  @Test
  void testSetters() {
    // Arrange
    PasswordResetToken passwordResetToken = new PasswordResetToken();

    Long id = 1L;
    String token = "abc123";
    String email = "test@example.com";
    LocalDateTime expirationDateTime = LocalDateTime.now();

    // Act
    passwordResetToken.setId(id);
    passwordResetToken.setToken(token);
    passwordResetToken.setEmail(email);
    passwordResetToken.setExpirationDateTime(expirationDateTime);

    // Assert
    assertEquals(id, passwordResetToken.getId());
    assertEquals(token, passwordResetToken.getToken());
    assertEquals(email, passwordResetToken.getEmail());
    assertEquals(expirationDateTime, passwordResetToken.getExpirationDateTime());
  }

  /**
   * This method tests the toString method of the PasswordResetToken class. It verifies that the
   * method returns the correct string.
   */
  @Test
  void testToString() {
    // Arrange
    PasswordResetToken passwordResetToken = new PasswordResetToken();
    passwordResetToken.setId(1L);
    passwordResetToken.setToken("abc");
    passwordResetToken.setEmail("john-doe@gmail.com");
    passwordResetToken.setExpirationDateTime(LocalDateTime.now());

    String expected =
        "PasswordResetToken(id=1, token=abc, email=john-doe@gmail.com, expirationDateTime="
            + passwordResetToken.getExpirationDateTime()
            + ")";

    // Act
    String actual = passwordResetToken.toString();

    // Assert
    assertEquals(expected, actual);
  }

  /**
   * This method tests the equals method of the PasswordResetToken class. It verifies that the
   * method returns the correct boolean value.
   */
  @Test
  void testEquals() {
    // Arrange
    PasswordResetToken passwordResetToken1 = new PasswordResetToken();
    passwordResetToken1.setId(1L);
    passwordResetToken1.setToken("abc");
    passwordResetToken1.setEmail("john-doe@gmail.com");

    PasswordResetToken passwordResetToken2 = new PasswordResetToken();
    passwordResetToken2.setId(1L);
    passwordResetToken2.setToken("abc");
    passwordResetToken2.setEmail("john-doe@gmail.com");

    // Act
    boolean actual = passwordResetToken1.equals(passwordResetToken2);
    boolean isNull = passwordResetToken1.equals(null);

    // Assert
    assertEquals(true, actual);
    assertEquals(passwordResetToken1.canEqual(passwordResetToken2), true);
    assertEquals(passwordResetToken1.canEqual(null), isNull);
  }

  /**
   * This method tests the hashCode method of the PasswordResetToken class. It verifies that the
   * method returns the correct hash code.
   */
  @Test
  void testHashCode() {
    // Arrange
    PasswordResetToken passwordResetToken1 = new PasswordResetToken();
    passwordResetToken1.setId(1L);
    passwordResetToken1.setToken("abc");
    passwordResetToken1.setEmail("john-doe@gmail.com");

    PasswordResetToken passwordResetToken2 = new PasswordResetToken();
    passwordResetToken2.setId(1L);
    passwordResetToken2.setToken("abc");
    passwordResetToken2.setEmail("john-doe@gmail.com");

    // Act
    int hashCode1 = passwordResetToken1.hashCode();
    int hashCode2 = passwordResetToken2.hashCode();

    // Assert
    assertEquals(hashCode1, hashCode2);
    assertEquals(passwordResetToken1.canEqual(passwordResetToken2), true);
  }
}
