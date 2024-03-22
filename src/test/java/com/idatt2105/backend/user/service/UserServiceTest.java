package com.idatt2105.backend.user.service;

import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repositories.QuizRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.service.UserService;
import com.idatt2105.backend.util.ExistingUserException;
import com.idatt2105.backend.util.InvalidCredentialsException;
import com.idatt2105.backend.util.UserNotFoundException;

import jakarta.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private QuizRepository quizRepository;

  @InjectMocks
  private UserService userService;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddUser() {
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("password");

    when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
    when(userRepository.save(any())).thenReturn(user);

    User savedUser = userService.addUser(user);

    assertNotNull(savedUser);
    assertEquals("testUser", savedUser.getUsername());
    assertEquals("password", savedUser.getPassword());
    verify(userRepository, times(1)).findByUsername("testUser");
    verify(userRepository, times(1)).save(any());
  }

  @Test
  void testAddUser_existingUser() {
    User user = new User();
    user.setUsername("existingUser");
    user.setPassword("password");

    when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));

    assertThrows(ExistingUserException.class, () -> userService.addUser(user));
    verify(userRepository, times(1)).findByUsername("existingUser");
    verify(userRepository, never()).save(any());
  }

  @Test
  void testGetUsers() {
    List<User> userList = new ArrayList<>();

    User user1 = new User("testUser1", "password1");
    User user2 = new User("testUser2", "password");

    userList.add(user1);
    userList.add(user2);

    when(userRepository.findAll()).thenReturn(userList);

    List<User> users = userService.getUsers();

    assertNotNull(users);
    assertEquals(2, users.size());
    verify(userRepository, times(1)).findAll();
  }

  @Test
  void testGetUserById() {
    User user = new User("testUser", "password");
    user.setId(1L);

    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    User foundUser = userService.getUserById(1L);

    assertNotNull(foundUser);
    assertEquals("testUser", foundUser.getUsername());
    assertEquals("password", foundUser.getPassword());
    verify(userRepository, times(1)).findById(1L);
  }

  @Test
  void testGetUserById_userNotFound() {
    when(userRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    verify(userRepository, times(1)).findById(1L);
  }

  @Test 
  void testDeleteUser() {
    User user = new User("testUser", "password");
    user.setId(1L);

    when(userRepository.existsById(1L)).thenReturn(true);

    userService.deleteUser(1L);

    verify(userRepository, times(1)).existsById(1L);
    verify(userRepository, times(1)).deleteById(1L);
  }

  @Test
  void testUpdateUser() {
    User user = new User("testUser", "password");
    user.setId(1L);

    User updatedUser = new User("updatedUser", "updatedPassword");
    updatedUser.setId(1L);

    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    when(userRepository.save(any())).thenReturn(updatedUser);

    User savedUser = userService.updateUser(1L, updatedUser);

    assertNotNull(savedUser);
    assertEquals("updatedUser", savedUser.getUsername());
    assertEquals("updatedPassword", savedUser.getPassword());
    verify(userRepository, times(1)).findById(1L);
    verify(userRepository, times(1)).save(any());
  }

  @Test
  void testUpdateUser_userNotFound() {
    User updatedUser = new User("updatedUser", "updatedPassword");
    updatedUser.setId(1L);

    when(userRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, updatedUser));
    verify(userRepository, times(1)).findById(1L);
    verify(userRepository, never()).save(any());
  }

  @Test
  void TestGetUserByUsername() {
    User user = new User("testUser", "password");
    user.setId(1L);

    when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

    User foundUser = userService.getUserByUsername("testUser");

    assertNotNull(foundUser);
    assertEquals("testUser", foundUser.getUsername());
    assertEquals("password", foundUser.getPassword());
    verify(userRepository, times(1)).findByUsername("testUser");
  }

  @Test
  void TestLogin() {
    User user = new User("testUser", "password");
    user.setId(1L);

    when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

    String foundUser = userService.login(user);

    assertNotNull(foundUser);
    assertEquals(foundUser, "Token");
    verify(userRepository, times(1)).findByUsername("testUser");
  }

  @Test
  public void testAddQuizToUser() {
      Long userId = 1L;
      Long quizId = 1L;

      User user = new User();
      user.setId(userId);

      Quiz quiz = new Quiz();
      quiz.setId(quizId);

      when(userRepository.findById(userId)).thenReturn(Optional.of(user));
      when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));

      userService.addQuizToUser(userId, quizId);

      verify(userRepository, times(1)).save(user);

      assertEquals(1, user.getQuizzes().size());
      assertEquals(quiz, user.getQuizzes().iterator().next());
  }

  @Test
  public void removeQuizFromUser() {
    Long userId = 1L;
    Long quizId = 1L;

    User user = new User();
    user.setId(userId);

    Quiz quiz = new Quiz();
    quiz.setId(quizId);

    user.getQuizzes().add(quiz);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));

    userService.removeQuizFromUser(userId, quizId);

    verify(userRepository, times(1)).save(user);

    assertEquals(0, user.getQuizzes().size());
  }

  @Test
  public void getQuizzesByUserId() {
    Long userId = 1L;

    User user = new User();
    user.setId(userId);

    Quiz quiz = new Quiz();
    quiz.setId(1L);

    user.getQuizzes().add(quiz);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    Set<Quiz> quizzes = userService.getQuizzesByUserId(userId);

    assertEquals(1, quizzes.size());
    assertEquals(quiz, quizzes.iterator().next());
  }
      
}
