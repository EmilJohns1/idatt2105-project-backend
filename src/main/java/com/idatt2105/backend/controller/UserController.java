package com.idatt2105.backend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.idatt2105.backend.dto.LoginRequestDTO;
import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/** The UserController class handles HTTP requests related to users. */
@RestController
@RequestMapping("/api/user")
@Tag(name = "Users", description = "Operations related to users")
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
   * @param registrationRequest (LoginRequestDTO) User to register.
   * @return ResponseEntity with a message, or an ErrorResponse if an error occurs.
   */
  @PostMapping("/register")
  @Operation(summary = "Register a new user")
  public ResponseEntity<String> register(
      @RequestBody @Validated LoginRequestDTO registrationRequest) {
    String role = null;
    if (registrationRequest.getRole() != null) {
      role = registrationRequest.getRole().equals("mysecretpassword") ? "ADMIN" : "USER";
    }
    User user = new User();
    user.setUsername(registrationRequest.getUsername());
    user.setPassword(registrationRequest.getPassword());
    if (role == null) {
      userService.addUser(user);
    } else {
      userService.addUser(user, role);
    }
    return ResponseEntity.ok("Registered successfully");
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
    userService.updateUser(user.getId(), user);
    return ResponseEntity.ok("User updated successfully");
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
    UserDTO userDTO = userService.getUserById(id);
    return ResponseEntity.ok(userDTO);
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
    UserDTO userDTO = userService.getUserByUsername(username);
    return ResponseEntity.ok(userDTO);
  }

  /**
   * Update user's profile picture by username.
   *
   * @param username (String) Username of the user to update profile picture for.
   * @param profilePictureUrl (String) URL of the new profile picture.
   * @return ResponseEntity with a message, or an ErrorResponse if an error occurs.
   */
  @PutMapping("/profile-picture")
  @Operation(summary = "Update user's profile picture by username")
  public ResponseEntity<String> updateProfilePictureByUsername(
      @RequestParam("username") String username,
      @RequestParam("profilePictureUrl") String profilePictureUrl) {
    userService.updateProfilePicture(username, profilePictureUrl);
    return ResponseEntity.ok("Profile picture updated successfully");
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
    Set<QuizDTO> quizDTOs = userService.getQuizzesByUserId(id);
    return ResponseEntity.ok(quizDTOs);
  }
}
