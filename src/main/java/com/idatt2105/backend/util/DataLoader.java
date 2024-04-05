package com.idatt2105.backend.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.idatt2105.backend.model.User;
import com.idatt2105.backend.service.UserService;

@Component
public class DataLoader implements CommandLineRunner {
  private final UserService userService;

  public DataLoader(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void run(String... args) {
    try {
      userService.getUserByUsername(
          "admin"); // This will throw exception if the user does not exist
    } catch (UserNotFoundException e) {
      User admin = new User();
      admin.setUsername("admin");
      admin.setPassword("password");
      userService.addUser(admin, "ADMIN");
    }
  }
}
