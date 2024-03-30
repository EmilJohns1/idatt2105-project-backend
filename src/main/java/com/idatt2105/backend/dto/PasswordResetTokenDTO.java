package com.idatt2105.backend.dto;

import java.time.LocalDateTime;

import lombok.*;

/** Represents a password reset token. */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class PasswordResetTokenDTO {
  private String token;
  private String email;
  private LocalDateTime expirationDateTime;
}
