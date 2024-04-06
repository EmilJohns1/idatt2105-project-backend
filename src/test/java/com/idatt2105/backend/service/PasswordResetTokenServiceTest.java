package com.idatt2105.backend.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.idatt2105.backend.model.PasswordResetToken;
import com.idatt2105.backend.repository.PasswordResetTokenRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The PasswordResetTokenServiceTest class is a test class that tests the PasswordResetTokenService
 * class.
 */
class PasswordResetTokenServiceTest {

  @Mock private PasswordResetTokenRepository tokenRepository;

  @InjectMocks private PasswordResetTokenService tokenService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * This method tests the generateToken method of the PasswordResetTokenService class. It verifies
   * that the method generates a new token and deletes the existing token if it exists.
   */
  @Test
  void testGenerateToken() {
    // Arrange
    String email = "test@example.com";
    String tokenValue = UUID.randomUUID().toString();
    LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(15);

    PasswordResetToken existingToken = new PasswordResetToken();
    existingToken.setEmail(email);
    existingToken.setToken(tokenValue);
    existingToken.setExpirationDateTime(LocalDateTime.now().minusMinutes(1));

    PasswordResetToken newToken = new PasswordResetToken();
    newToken.setEmail(email);
    newToken.setToken(tokenValue);
    newToken.setExpirationDateTime(expirationDateTime);

    when(tokenRepository.findByEmail(email)).thenReturn(Optional.of(existingToken));
    when(tokenRepository.save(any(PasswordResetToken.class))).thenReturn(newToken);

    // Act
    PasswordResetToken generatedToken = tokenService.generateToken(email);

    // Assert
    assertNotNull(generatedToken);
    assertEquals(email, generatedToken.getEmail());
    assertEquals(tokenValue, generatedToken.getToken());
    assertEquals(expirationDateTime, generatedToken.getExpirationDateTime());

    verify(tokenRepository, times(1)).findByEmail(email);
    verify(tokenRepository, times(1)).delete(existingToken);
  }

  /**
   * This method tests the generateToken method of the PasswordResetTokenService class. It verifies
   * that the method generates a new token if no existing token is found.
   */
  @Test
  void testGenerateToken_NoExistingToken() {
    // Arrange
    String email = "test@example.com";
    String tokenValue = UUID.randomUUID().toString();
    LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(15);

    PasswordResetToken newToken = new PasswordResetToken();
    newToken.setEmail(email);
    newToken.setToken(tokenValue);
    newToken.setExpirationDateTime(expirationDateTime);

    when(tokenRepository.findByEmail(email)).thenReturn(Optional.empty());
    when(tokenRepository.save(any(PasswordResetToken.class))).thenReturn(newToken);

    // Act
    PasswordResetToken generatedToken = tokenService.generateToken(email);

    // Assert
    assertNotNull(generatedToken);
    assertEquals(email, generatedToken.getEmail());
    assertEquals(tokenValue, generatedToken.getToken());
    assertEquals(expirationDateTime, generatedToken.getExpirationDateTime());

    verify(tokenRepository, times(1)).findByEmail(email);
    verify(tokenRepository, never()).delete(any(PasswordResetToken.class));
  }

  /**
   * This method tests the findByToken method of the PasswordResetTokenService class. It verifies
   * that the method returns the token if it exists.
   */
  @Test
  void testFindByToken() {
    // Arrange
    String token = "testToken";
    PasswordResetToken expectedToken = new PasswordResetToken();

    when(tokenRepository.findByToken(token)).thenReturn(expectedToken);

    // Act
    PasswordResetToken result = tokenService.findByToken(token);

    // Assert
    assertEquals(expectedToken, result);

    verify(tokenRepository, times(1)).findByToken(token);
  }

  /**
   * This method tests the findByEmail method of the PasswordResetTokenService class. It verifies
   * that the method returns the token if it exists.
   */
  @Test
  void testFindByEmail() {
    // Arrange
    String email = "test@example.com";
    Optional<PasswordResetToken> expectedToken = Optional.of(new PasswordResetToken());

    when(tokenRepository.findByEmail(email)).thenReturn(expectedToken);

    // Act
    Optional<PasswordResetToken> result = tokenService.findByEmail(email);

    // Assert
    assertEquals(expectedToken, result);

    verify(tokenRepository, times(1)).findByEmail(email);
  }

  /**
   * This method tests the deleteToken method of the PasswordResetTokenService class. It verifies
   * that the method deletes the token if given a valid email with a valid token.
   */
  @Test
  void testDeleteTokenByEmail() {
    // Arrange
    String email = "test@example.com";

    // Act
    tokenService.deleteTokenByEmail(email);

    // Assert
    verify(tokenRepository, times(1)).deleteByEmail(email);
  }
}
