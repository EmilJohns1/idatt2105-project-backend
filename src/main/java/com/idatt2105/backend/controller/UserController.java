package com.idatt2105.backend.controller;

import com.idatt2105.backend.model.User;
import com.idatt2105.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Registers a new user.
   *
   * @param user (User) User to register.
   * @return ResponseEntity with a message, or an ErrorResponse if an error occurs.
   */
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody @Validated User user) {
      try {
          userService.addUser(user);
          return ResponseEntity.ok("Registered successfully");
      } catch (RuntimeException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
      }
  }

  /**
   * Logs in a user.
   *
   * @param user (User) User to log in.
   * @return ResponseEntity with a message, or an ErrorResponse if an error occurs.
   */
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody @Validated User user) {
    try {
      userService.login(user);
      return ResponseEntity.ok("Logged in successfully");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  /**
   * Updates a user.
   *
   * @param user (User) User to update.
   * @return ResponseEntity with a message, or an ErrorResponse if an error occurs.
   */
  @PostMapping("/update")
  public ResponseEntity<String> update(@RequestBody @Validated User user) {
    try {
      userService.updateUser(user.getId(), user);
      return ResponseEntity.ok("User updated successfully");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  /**
   * Adds a quiz to a user
   *
   * @param userId (Long) Id of the user to add the quiz to, quizId (Long) Id of the quiz to add to the user.
   * @return ResponseEntity with a message, or an ErrorResponse if an error occurs.
   */
  @PostMapping("/addQuiz/{userId}/{quizId}")
  public ResponseEntity<String> addQuiz(@PathVariable Long userId, @PathVariable Long quizId) {
    try {
      userService.addQuizToUser(userId, quizId);
      return ResponseEntity.ok("Quiz added successfully");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  /**
   * Removes a quiz from a user
   *
   * @param userId (Long) Id of the user to remove the quiz from, quizId (Long) Id of the quiz to remove from the user.
   * @return ResponseEntity with a message, or an ErrorResponse if an error occurs.
   */
  @DeleteMapping("/removeQuiz/{userId}/{quizId}")
  public ResponseEntity<String> removeQuiz(@PathVariable Long userId, @PathVariable Long quizId) {
    try {
      userService.removeQuizFromUser(userId, quizId);
      return ResponseEntity.ok("Quiz removed successfully");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

}
