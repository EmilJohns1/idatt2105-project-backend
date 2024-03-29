package com.idatt2105.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idatt2105.backend.model.PasswordResetToken;

/** Repository for PasswordResetToken entities. */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

  PasswordResetToken findByToken(String token);

  Optional<PasswordResetToken> findByEmail(String email);

  void deleteByEmail(String email);
}
