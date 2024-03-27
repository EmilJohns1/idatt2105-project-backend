package com.idatt2105.backend.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.idatt2105.backend.dto.LoginRequestDTO;
import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/*
 * Test class for the UserController class.
 */
class UserControllerTest {

  @Mock private UserService userService;

  @InjectMocks private UserController userController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /*
   * Test the getUsers method.
   */
  @Test
  void testGetUsers() {
    // Arrange
    List<UserDTO> users = Collections.singletonList(new UserDTO());
    when(userService.getUsers()).thenReturn(users);

    // Act
    ResponseEntity<List<UserDTO>> response = userController.getUsers();

    // Assert
    assertEquals(users, response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  /*
   * Test the register method.
   */
  @Test
  void testRegister() {
    // Arrange
    LoginRequestDTO requestDTO = new LoginRequestDTO("username", "password");

    // Act
    ResponseEntity<String> response = userController.register(requestDTO);

    // Assert
    assertEquals("Registered successfully", response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(userService, times(1)).addUser(any());
  }

  /*
   * Test the login method.
   */
  @Test
  void testLogin() {
    // Arrange
    LoginRequestDTO requestDTO = new LoginRequestDTO("username", "password");

    // Act
    ResponseEntity<String> response = userController.login(requestDTO);

    // Assert
    assertEquals("Logged in successfully", response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(userService, times(1)).login(any());
  }

  /*
   * Test the update method.
   */
  @Test
  void testUpdate() {
    // Arrange
    UserDTO userDTO = new UserDTO();
    userDTO.setId(1L);
    when(userService.updateUser(eq(1L), any())).thenReturn(userDTO);

    User user = new User();
    user.setId(1L); // Set the ID to match the expected value

    // Act
    ResponseEntity<String> response = userController.update(user);

    // Assert
    assertEquals("User updated successfully", response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(userService, times(1)).updateUser(eq(1L), any());
  }

  /*
   * Test the delete method.
   */
  @Test
  void testDelete() {
    // Arrage & Act
    ResponseEntity<String> response = userController.delete(1L);

    // Assert
    assertEquals("User deleted successfully", response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(userService, times(1)).deleteUser(eq(1L));
  }

  /*
   * Test the getUserById method.
   */
  @Test
  void testGetUserById() {
    // Arrange
    UserDTO userDTO = new UserDTO();
    when(userService.getUserById(eq(1L))).thenReturn(Optional.of(userDTO));

    // Act
    ResponseEntity<UserDTO> response = userController.getUserById(1L);

    // Assert
    assertEquals(userDTO, response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(userService, times(1)).getUserById(eq(1L));
  }

  /*
   * Test the getUserByUsername method.
   */
  @Test
  void testGetUserByUsername() {
    // Arrange
    UserDTO userDTO = new UserDTO();
    when(userService.getUserByUsername(anyString())).thenReturn(Optional.of(userDTO));

    // Act
    ResponseEntity<UserDTO> response = userController.getUserByUsername("username");

    // Assert
    assertEquals(userDTO, response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(userService, times(1)).getUserByUsername(anyString());
  }

  /*
   * Test the getUserQuizzes method.
   */
  @Test
  void testGetUserQuizzes() {
    // Arrange
    Set<QuizDTO> quizDTOs = Collections.singleton(new QuizDTO());
    when(userService.getQuizzesByUserId(eq(1L))).thenReturn(quizDTOs);

    // Act
    ResponseEntity<Set<QuizDTO>> response = userController.getUserQuizzes(1L);

    // Assert
    assertEquals(quizDTOs, response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(userService, times(1)).getQuizzesByUserId(eq(1L));
  }
}
