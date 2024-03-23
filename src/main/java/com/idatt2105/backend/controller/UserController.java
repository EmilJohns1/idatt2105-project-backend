package com.idatt2105.backend.controller;

import com.idatt2105.backend.dto.LoginRequestDTO;
import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Operations related to users")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Gets all registered users.
   *
   * @return List of all registered users.
   */
  @GetMapping
  @Operation(summary = "Get all users")
  public ResponseEntity<List<UserDTO>> getUsers() {
    List<UserDTO> userDTOs = userService.getUsers();
    return new ResponseEntity<>(userDTOs, HttpStatus.OK);
  }

  /**
   * Registers a new user.
   *
   * @param user (User) User to register.
   * @return ResponseEntity with a message, or an ErrorResponse if an error occurs.
   */
  @PostMapping("/register")
  @Operation(summary = "Register a new user")
  public ResponseEntity<String> register(@RequestBody @Validated LoginRequestDTO registrationRequest) {
      try {
          User user = new User();
          user.setUsername(registrationRequest.getUsername());
          user.setPassword(registrationRequest.getPassword());
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
  @Operation(summary = "Log in a user")
  public ResponseEntity<String> login(@RequestBody @Validated LoginRequestDTO loginRequest) {
      try {
          User user = new User();
          user.setUsername(loginRequest.getUsername());
          user.setPassword(loginRequest.getPassword());
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
  @Operation(summary = "Update a user")
  public ResponseEntity<String> update(@RequestBody @Validated User user) {
    try {
      userService.updateUser(user.getId(), user);
      return ResponseEntity.ok("User updated successfully");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  /**
   * Deletes a user.
   *
   * @param id (Long) Id of the user to delete.
   * @return ResponseEntity with a message, or an ErrorResponse if an error occurs.
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a user")
  public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    try {
      userService.deleteUser(id);
      return ResponseEntity.ok("User deleted successfully");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  /**
   * Gets a user by id.
   *
   * @param id (Long) Id of the user to get.
   * @return ResponseEntity with the user, or an ErrorResponse if an error occurs.
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get user by id")
  public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
    try {
      UserDTO userDTO = userService.getUserById(id).orElseThrow();
      return ResponseEntity.ok(userDTO);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserDTO());
    }
  }

  /**
   * Gets a user by username.
   *
   * @param username (String) Username of the user to get.
   * @return ResponseEntity with the user, or an ErrorResponse if an error occurs.
   */
  @GetMapping("/username")
  @Operation(summary = "Get user by username")
  public ResponseEntity<UserDTO> getUserByUsername(@RequestParam("username") String username) {
    try {
      UserDTO userDTO = userService.getUserByUsername(username).orElseThrow();
      return ResponseEntity.ok(userDTO);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserDTO());
    }
  }

  /**
   * Get user quizzes by user id.
   * 
   * @param id (Long) Id of the user to get quizzes for.
   * @return ResponseEntity with the user's quizzes, or an ErrorResponse if an error occurs.
   */
  @GetMapping("/{id}/quizzes")
  @Operation(summary = "Get quizzes by user id")
  public ResponseEntity<Set<QuizDTO>> getUserQuizzes(@PathVariable("id") Long id) {
    try {
      Set<QuizDTO> quizDTOs = userService.getQuizzesByUserId(id);
      return ResponseEntity.ok(quizDTOs);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptySet());
    }
  }
  
  

}
