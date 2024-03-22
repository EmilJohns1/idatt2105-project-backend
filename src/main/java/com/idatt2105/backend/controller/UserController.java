package com.idatt2105.backend.controller;

import com.idatt2105.backend.model.User;
import com.idatt2105.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    userService.addUser(user);
    return ResponseEntity.ok("Registered successfully");
  }

  /**
   * Logs in a user.
   *
   * @param user (User) User to log in.
   * @return ResponseEntity with a message, or an ErrorResponse if an error occurs.
   */
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody @Validated User user) {
    return ResponseEntity.ok(userService.login(user));
  }

  /**
   * Updates a user.
   *
   * @param user (User) User to update.
   * @return ResponseEntity with a message, or an ErrorResponse if an error occurs.
   */
  @PostMapping("/update")
  public ResponseEntity<String> update(@RequestBody @Validated User user) {
    userService.updateUser(user.getId(), user);
    return ResponseEntity.ok("Updated successfully");
  }
}
