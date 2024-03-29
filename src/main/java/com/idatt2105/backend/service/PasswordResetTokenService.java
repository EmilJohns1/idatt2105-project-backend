package com.idatt2105.backend.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.model.PasswordResetToken;
import com.idatt2105.backend.repository.PasswordResetTokenRepository;

import jakarta.transaction.Transactional;

/**
 * Service class for PasswordResetToken entities. Handles business logic for PasswordResetToken
 * entities.
 */
@Service
public class PasswordResetTokenService {

  @Autowired private PasswordResetTokenRepository tokenRepository;

  /**
   * Generates a new password reset token for the given email.
   *
   * @param email (String) Email to generate token for.
   * @return PasswordResetToken entity.
   */
  public PasswordResetToken generateToken(String email) {
    // Check if a token already exists for the given email
    Optional<PasswordResetToken> existingTokenOptional = tokenRepository.findByEmail(email);
    if (existingTokenOptional.isPresent()) {
      // Token already exists, delete it
      tokenRepository.delete(existingTokenOptional.get());
    }

    // Generate a new token
    String tokenValue = UUID.randomUUID().toString();

    // Set expiration date to 15 minutes after creation
    LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(15);

    // Create the token entity
    PasswordResetToken newToken = new PasswordResetToken();
    newToken.setEmail(email);
    newToken.setToken(tokenValue);
    newToken.setExpirationDateTime(expirationDateTime);

    // Save the token in the database
    return tokenRepository.save(newToken);
  }

  /**
   * Finds a password reset token by the token value.
   *
   * @param token (String) Token value to search for.
   * @return PasswordResetToken entity.
   */
  public PasswordResetToken findByToken(String token) {
    return tokenRepository.findByToken(token);
  }

  /**
   * Finds a password reset token by the email.
   *
   * @param email (String) Email to search for.
   * @return PasswordResetToken entity.
   */
  public Optional<PasswordResetToken> findByEmail(String email) {
    return tokenRepository.findByEmail(email);
  }

  /**
   * Deletes a password reset token by the email.
   *
   * @param email (String) Email to delete token for.
   */
  @Transactional
  public void deleteTokenByEmail(String email) {
    tokenRepository.deleteByEmail(email);
  }
}
