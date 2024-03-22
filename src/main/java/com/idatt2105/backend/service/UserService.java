package com.idatt2105.backend.service;

import java.util.List;

import com.idatt2105.backend.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.UserRepository;

/**
 * Service class for User entities. Handles business logic for User entities.
 */
@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Gets all registered users.
   *
   * @return List of all registered users.
   */
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  /**
   * Gets a user by a unique id.
   *
   * @param id (Long) Unique id of the user.
   * @return User with the given id.
   * @throws UserNotFoundException If no user with the given id is found.
   */
  public User getUser(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
  }

  /**
   * Adds a new user to the database.
   *
   * @param user (User) User to add.
   * @return The added user.
   */
  public User addUser(User user) {
    return userRepository.save(user);
  }

  /**
   * Deletes a user from the database.
   *
   * @param id (Long) Id of the user to delete.
   * @throws UserNotFoundException If no user with the given id is found.
   */
  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new UserNotFoundException("User with id " + id + " not found");
    }
    userRepository.deleteById(id);
  }

  /**
   * Updates a user in the database.
   *
   * @param id (Long) Id of the user to update.
   * @param user (User) User with updated information.
   * @return The updated user.
   * @throws UserNotFoundException If no user with the given id is found.
   */
  public User updateUser(Long id, User user) {
    User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    existingUser.setUsername(user.getUsername());
    existingUser.setPassword(user.getPassword());
    return userRepository.save(existingUser);
  }

  /**
   * Gets a user by username.
   *
   * @param username (String) Username of the user.
   * @return User with the given username.
   * @throws UserNotFoundException If no user with the given username is found.
   */
  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
  }

  /**
   * Checks if a user with the given username exists.
   *
   * @param username (String) Username to check.
   * @return True if a user with the given username exists, false otherwise.
   */
  public boolean userExists(String username) {
    return userRepository.findByUsername(username).isPresent();
  }
}
