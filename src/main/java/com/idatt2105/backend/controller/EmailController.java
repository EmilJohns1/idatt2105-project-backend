package com.idatt2105.backend.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idatt2105.backend.service.PasswordResetTokenService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/** Controller for sending emails. */
@RestController
public class EmailController {

  @Autowired private JavaMailSender emailSender;
  @Autowired private PasswordResetTokenService passwordResetTokenService;

  /**
   * Sends an email to the specified email address.
   *
   * @param to the email address to send the email to
   * @return a message indicating if the email was sent successfully
   */
  @PostMapping("/api/sendEmail")
  @Operation(summary = "Send an email")
  public ResponseEntity<Map<String, String>> sendEmail(@RequestParam String to) {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper;
    try {
      helper = new MimeMessageHelper(message, true); // true indicates multipart message
      helper.setFrom("quiz.fullstack.noreply@gmail.com"); // Set your email address as the sender
      helper.setTo(to); // Set the recipient's email address
      helper.setSubject("Reset Password"); // Set the email subject

      // Generate token using PasswordResetTokenService
      String token = passwordResetTokenService.generateToken(to).getToken();

      String resetLink = "http://localhost:5173/reset-password?token=" + token;

      String htmlContent =
          "<html><body><h1>Hello!</h1><p>You requested to reset your password. "
              + "Click <a href=\""
              + resetLink
              + "\">here</a> to reset your password.</p></body></html>";
      helper.setText(htmlContent, true); // true indicates HTML content

      emailSender.send(message);

      // Create a map to hold the email and token
      Map<String, String> response = new HashMap<>();
      response.put("email", to);
      response.put("token", token);

      // Return the email and token in the response body
      return ResponseEntity.ok(response);
    } catch (MessagingException e) {
      e.printStackTrace(); // Handle the exception
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("error", "Failed to send email"));
    }
  }
}
