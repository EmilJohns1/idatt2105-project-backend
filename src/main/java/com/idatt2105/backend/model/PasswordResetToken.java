package com.idatt2105.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

/** Represents a password reset token. */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
public class PasswordResetToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private LocalDateTime expirationDateTime;
}
