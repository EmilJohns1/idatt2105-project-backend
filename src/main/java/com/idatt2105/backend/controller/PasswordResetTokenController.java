package com.idatt2105.backend.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.idatt2105.backend.dto.LoginRequestDTO;
import com.idatt2105.backend.dto.ResetRequestDTO;
import com.idatt2105.backend.model.PasswordResetToken;
import com.idatt2105.backend.service.PasswordResetTokenService;
import com.idatt2105.backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller for PasswordResetToken entities. Handles HTTP requests related to PasswordResetToken
 * entities.
 */
@RestController
@Tag(name = "Password Reset", description = "Operations related to password reset")
@RequestMapping("/api/password-reset")
public class PasswordResetTokenController {

  @Autowired private PasswordResetTokenService tokenService;

  @Autowired private UserService userService;

  /**
   * Resets a user's password.
   *
   * @param resetRequestDTO (PasswordResetRequestDTO) Password reset request containing email and
   *     new password.
   * @return ResponseEntity with a message indicating success or failure.
   */
  @PutMapping("/reset-password")
  @Operation(summary = "Reset a user's password")
  public ResponseEntity<String> resetPassword(@RequestBody ResetRequestDTO resetRequestDTO) {
    Optional<PasswordResetToken> tokenEntityOptional =
        tokenService.findByEmail(resetRequestDTO.getUsername());
    if (tokenEntityOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Failed to reset password. No token found for the provided email.");
    }

    // Check if the provided token matches the retrieved token
    PasswordResetToken tokenEntity = tokenEntityOptional.get();
    if (!tokenEntity.getToken().equals(resetRequestDTO.getToken())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Failed to reset password. Invalid token.");
    }

    // Check if the token has expired
    LocalDateTime expirationDateTime = tokenEntity.getExpirationDateTime();
    if (expirationDateTime.isBefore(LocalDateTime.now())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Failed to reset password. Token has expired.");
    }

    LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
    loginRequestDTO.setUsername(resetRequestDTO.getUsername());
    loginRequestDTO.setPassword(resetRequestDTO.getPassword());

    boolean passwordResetSuccessful = userService.resetPassword(loginRequestDTO);
    if (passwordResetSuccessful) {
      tokenService.deleteTokenByEmail(resetRequestDTO.getUsername());
      return ResponseEntity.ok("Password reset successfully");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to reset password.");
    }
  }
}
