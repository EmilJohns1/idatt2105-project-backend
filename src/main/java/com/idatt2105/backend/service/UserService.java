package com.idatt2105.backend.service;

import com.idatt2105.backend.util.ExistingUserException;
import com.idatt2105.backend.util.InvalidCredentialsException;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.idatt2105.backend.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.UserRepository;
import org.springframework.validation.annotation.Validated;

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
  public List<UserDTO> getUsers() {
    List<User> users = userRepository.findAll();
    List<UserDTO> userDTOs = new ArrayList<>();

    for (User user : users) {
        List<QuizDTO> quizDTOs = new ArrayList<>();
        for (Quiz quiz : user.getQuizzes()) {
            quizDTOs.add(new QuizDTO(quiz));
        }
        userDTOs.add(new UserDTO(user.getId(), user.getUsername(), quizDTOs));
    }
    return userDTOs;
  }

  /**
   * Gets a user by a unique id.
   *
   * @param id (Long) Unique id of the user.
   * @return Optional of the user with the given id.
   * @throws UserNotFoundException If no user with the given id is found.
   */
  public Optional<UserDTO> getUserById(Long id) {
    return userRepository.findById(id)
      .map(user -> {
        List<QuizDTO> quizDTOs = new ArrayList<>();
        for (Quiz quiz : user.getQuizzes()) {
          quizDTOs.add(new QuizDTO(quiz));
        }
        return new UserDTO(user.getId(), user.getUsername(), quizDTOs);
      })
      .or(() -> Optional.<UserDTO>empty());
    }

  /**
   * Adds a new user to the database.
   *
   * @param user (User) User to add.
   * @return The added user.
   * @throws ExistingUserException If a user with the same username already exists.
   */
  public UserDTO addUser(@Validated @NotNull User user) {
    if (userExists(user.getUsername())) {
      throw new ExistingUserException("User with username " + user.getUsername() + " already exists");
    }
    User savedUser = userRepository.save(user);
    return new UserDTO(savedUser.getId(), savedUser.getUsername(), Collections.emptyList());
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
  public UserDTO updateUser(Long id, @Validated @NotNull User user) {
    User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    existingUser.setUsername(user.getUsername());
    existingUser.setPassword(user.getPassword());
    User updatedUser = userRepository.save(existingUser);
    List<QuizDTO> quizDTOs = new ArrayList<>();
    for (Quiz quiz : updatedUser.getQuizzes()) {
      quizDTOs.add(new QuizDTO(quiz));
    }
    return new UserDTO(updatedUser.getId(), updatedUser.getUsername(), quizDTOs);
  }

  /**
   * Gets a user by username.
   *
   * @param username (String) Username of the user.
   * @return User with the given username.
   * @throws UserNotFoundException If no user with the given username is found.
   */
  public Optional<UserDTO> getUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .map(user -> {
          return new UserDTO(user.getId(), user.getUsername());
        });
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

  /**
   * Logs a user in.
   *
   * @param user (User) The user to log in.
   * @return A token for future authentication.
   */
  public String login(@Validated @NotNull User user) {
    if (validateCredentials(user)) {
      return "Token";
    } else {
      throw new InvalidCredentialsException("Invalid user credentials.");
    }
  }

  /**
   * Checks the credentials of the given user.
   *
   * @param user (User) The user to be validated.
   * @return {@code true} if the credentials are valid, {@code false} otherwise.
   * @throws UserNotFoundException If no user with the given username is found.
   */
  private boolean validateCredentials(@Validated @NotNull User user) {
    User existingUser = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UserNotFoundException("User with username " + user.getUsername() + " not found"));
    return existingUser.getPassword().equals(user.getPassword());
  }

  /*
   * Gets all quizzes created by a user.
   * @param userId (Long) Id of the user.
   * @return Set of quizzes created by the user.
   */
  public Set<QuizDTO> getQuizzesByUserId(Long userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with id " + userId + " not found"));
    Set<QuizDTO> quizDTOs = new HashSet<>();
    for (Quiz quiz : user.getQuizzes()) {
        quizDTOs.add(new QuizDTO(quiz));
    }
    return quizDTOs;
  }

  /**
   * Saves a user to the database.
   *
   * @param user (User) User to save.
   * @return The saved user.
   */
  public UserDTO save(User user) {
    User savedUser = userRepository.save(user);
    return new UserDTO(savedUser.getId(), savedUser.getUsername(), Collections.emptyList());
  }
  
  /**
   * Gets a user by id.
   *
   * @param id (Long) Id of the user to get.
   * @return Optional of the user with the given id.
   */
  public Optional<UserDTO> getUserByIdDTO(Long id) {
    return userRepository.findById(id)
            .map(user -> new UserDTO(user.getId(), user.getUsername(), Collections.emptyList()));
  }
}
