package com.idatt2105.backend.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.idatt2105.backend.dto.ResetRequestDTO;
import com.idatt2105.backend.model.PasswordResetToken;
import com.idatt2105.backend.service.PasswordResetTokenService;
import com.idatt2105.backend.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The PasswordResetTokenControllerTest class is a test class that tests the
 * PasswordResetTokenController class.
 */
class PasswordResetTokenControllerTest {

  @Mock private PasswordResetTokenService tokenService;

  @Mock private UserService userService;

  @InjectMocks private PasswordResetTokenController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * This method tests the resetPassword endpoint in the PasswordResetTokenController class with a
   * valid token. It verifies that the endpoint returns an HTTP status code of 200 OK when the
   * password is successfully reset.
   */
  @Test
  void testResetPasswordWithValidToken() {
    // Arrange
    String username = "test@example.com";
    String token = "validToken";
    String newPassword = "newPassword";

    ResetRequestDTO resetRequestDTO = new ResetRequestDTO();
    resetRequestDTO.setUsername(username);
    resetRequestDTO.setToken(token);
    resetRequestDTO.setPassword(newPassword);

    PasswordResetToken tokenEntity = new PasswordResetToken();
    tokenEntity.setToken(token);
    tokenEntity.setExpirationDateTime(LocalDateTime.now().plusHours(1));

    when(tokenService.findByEmail(username)).thenReturn(Optional.of(tokenEntity));
    when(userService.resetPassword(any())).thenReturn(true);

    // Act
    ResponseEntity<String> response = controller.resetPassword(resetRequestDTO);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Password reset successfully", response.getBody());
    verify(tokenService, times(1)).deleteTokenByEmail(username);
  }

  /**
   * This method tests the resetPassword endpoint in the PasswordResetTokenController class with an
   * invalid token. It verifies that the endpoint returns an HTTP status code of 400 Bad Request
   * when the token is invalid.
   */
  @Test
  void testResetPasswordWithInvalidToken() {
    // Arrange
    String username = "test@example.com";
    String token = "invalidToken";
    String newPassword = "newPassword";

    ResetRequestDTO resetRequestDTO = new ResetRequestDTO();
    resetRequestDTO.setUsername(username);
    resetRequestDTO.setToken(token);
    resetRequestDTO.setPassword(newPassword);

    PasswordResetToken tokenEntity = new PasswordResetToken();
    tokenEntity.setToken("validToken");
    tokenEntity.setExpirationDateTime(LocalDateTime.now().plusHours(1));

    when(tokenService.findByEmail(username)).thenReturn(Optional.of(tokenEntity));

    // Act
    ResponseEntity<String> response = controller.resetPassword(resetRequestDTO);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Failed to reset password. Invalid token.", response.getBody());
    verify(tokenService, never()).deleteTokenByEmail(username);
  }

  /**
   * This method tests the resetPassword endpoint in the PasswordResetTokenController class with an
   * expired token. It verifies that the endpoint returns an HTTP status code of 400 Bad Request
   * when the token has expired.
   */
  @Test
  void testResetPasswordWithExpiredToken() {
    // Arrange
    String username = "test@example.com";
    String token = "validToken";
    String newPassword = "newPassword";

    ResetRequestDTO resetRequestDTO = new ResetRequestDTO();
    resetRequestDTO.setUsername(username);
    resetRequestDTO.setToken(token);
    resetRequestDTO.setPassword(newPassword);

    PasswordResetToken tokenEntity = new PasswordResetToken();
    tokenEntity.setToken(token);
    tokenEntity.setExpirationDateTime(LocalDateTime.now().minusHours(1));

    when(tokenService.findByEmail(username)).thenReturn(Optional.of(tokenEntity));

    // Act
    ResponseEntity<String> response = controller.resetPassword(resetRequestDTO);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Failed to reset password. Token has expired.", response.getBody());
    verify(tokenService, never()).deleteTokenByEmail(username);
  }

  /**
   * This method tests the resetPassword endpoint in the PasswordResetTokenController class with no
   * token found. It verifies that the endpoint returns an HTTP status code of 400 Bad Request when
   * no token is found for the provided email.
   */
  @Test
  void testResetPasswordWithNoTokenFound() {
    // Arrange
    String username = "test@example.com";
    String token = "validToken";
    String newPassword = "newPassword";

    ResetRequestDTO resetRequestDTO = new ResetRequestDTO();
    resetRequestDTO.setUsername(username);
    resetRequestDTO.setToken(token);
    resetRequestDTO.setPassword(newPassword);

    when(tokenService.findByEmail(username)).thenReturn(Optional.empty());

    // Act
    ResponseEntity<String> response = controller.resetPassword(resetRequestDTO);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(
        "Failed to reset password. No token found for the provided email.", response.getBody());
    verify(tokenService, never()).deleteTokenByEmail(username);
  }
}
