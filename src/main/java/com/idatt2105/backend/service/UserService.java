package com.idatt2105.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repositories.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }
  
  public User getUser(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + " not found"));
  }

  public User addUser(User user) {
    return userRepository.save(user);
  }

  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new IllegalStateException("User with id " + id + " not found");
    }
    userRepository.deleteById(id);
  }

  public User updateUser(Long id, User user) {
    User existingUser = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + " not found"));
    existingUser.setUsername(user.getUsername());
    existingUser.setPassword(user.getPassword());
    return userRepository.save(existingUser);
  }

  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException("User with username " + username + " not found"));
  }

  public boolean userExists(String username) {
    return userRepository.findByUsername(username).isPresent();
  }
}
