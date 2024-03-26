package com.idatt2105.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
public class EmailController {

  @Autowired private JavaMailSender emailSender;

  @PostMapping("/api/sendEmail")
  public String sendEmail(@RequestParam String to) {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper;
    try {
      helper = new MimeMessageHelper(message, true); // true indicates multipart message
      helper.setFrom("quiz.fullstack.noreply@gmail.com"); // Set your email address as the sender
      helper.setTo(to); // Set the recipient's email address
      helper.setSubject("Reset Password"); // Set the email subject

      // Set HTML content here
      String htmlContent =
          "<html><body><h1>Hello!</h1><p>You requested to reset your password, "
              + "click this link!</p></body></html>";
      helper.setText(htmlContent, true); // true indicates HTML content

      emailSender.send(message);
      return "Email sent successfully!";
    } catch (MessagingException e) {
      e.printStackTrace(); // Handle the exception
      return "Failed to send email";
    }
  }
}
