package com.idatt2105.backend.controller;

import com.idatt2105.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public void register() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @PostMapping("/login")
  public void login() {
    throw new UnsupportedOperationException("Not implemented");
  }
}
