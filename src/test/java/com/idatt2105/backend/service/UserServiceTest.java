package com.idatt2105.backend.service;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.ExistingUserException;
import com.idatt2105.backend.util.InvalidCredentialsException;
import com.idatt2105.backend.util.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*
 * Test class for the UserService class.
 */
class UserServiceTest {

  @Mock private UserRepository userRepository;

  @Mock private QuizRepository quizRepository;

  @Mock private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @InjectMocks private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /** Test the getUsers method. It verifies that the method returns a list of users. */
  @Test
  public void testGetUsers() {
    // Arrange
    List<User> users = new ArrayList<>();
    users.add(new User("user1", "password1"));
    users.add(new User("user2", "password2"));
    when(userRepository.findAll()).thenReturn(users);

    // Act
    List<UserDTO> userDTOs = userService.getUsers();

    // Assert
    assertEquals(2, userDTOs.size());
    assertEquals("user1", userDTOs.get(0).getUsername());
    assertEquals("user2", userDTOs.get(1).getUsername());
  }

  /** Test the addUser method. It verifies that the method adds a user to the database. */
  @Test
  public void testAddUser() {
    // Arrange
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("password");

    when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
    when(passwordEncoder.encode(user.getPassword()))
        .thenReturn("hashedPassword"); // Adjusted to match the actual method call

    // Mock the save method to return the same user object
    when(userRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

    // Act
    UserDTO savedUser = userService.addUser(user);

    // Assert
    assertNotNull(savedUser);
    assertEquals("testUser", savedUser.getUsername());
    assertEquals(Collections.emptyList(), savedUser.getQuizzes());

    // Verify interactions with mocks
    verify(userRepository, times(1)).findByUsername("testUser");
    verify(userRepository, times(1)).save(any());
  }

  /**
   * Test the addUser method with an existing user. It verifies that the method throws an
   * ExistingUserException.
   *
   * @throws ExistingUserException if the user already exists
   */
  @Test
  public void testAddUser_ExistingUser() {
    // Arrange
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("password");

    when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

    // Act & Assert
    assertThrows(ExistingUserException.class, () -> userService.addUser(user));
    verify(userRepository, times(1)).findByUsername("testUser");
    verify(passwordEncoder, never()).encode(any());
    verify(userRepository, never()).save(any());
  }

  /** Test the deleteUser method. It verifies that the method deletes a user from the database. */
  @Test
  public void testDeleteUser() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    user.setId(userId);

    when(userRepository.existsById(userId)).thenReturn(true);

    // Act
    userService.deleteUser(userId);

    // Assert
    verify(userRepository, times(1)).deleteById(userId);
  }

  /**
   * Test the deleteUser method with a user not found. It verifies that the method throws a
   * UserNotFoundException.
   *
   * @throws UserNotFoundException if the user is not found
   */
  @Test
  public void testDeleteUser_UserNotFound() {
    // Arrange
    Long userId = 1L;

    when(userRepository.existsById(userId)).thenReturn(false);

    // Act & Assert
    assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
    verify(userRepository, times(1)).existsById(userId);
    verify(userRepository, never()).deleteById(userId);
  }

  /** Test the updateUser method. It verifies that the method updates a user in the database. */
  @Test
  public void testUpdateUser() {
    // Arrange
    Long userId = 1L;
    User existingUser = new User();
    existingUser.setId(userId);
    existingUser.setUsername("existingUser");
    existingUser.setPassword("oldPassword");

    User updatedUser = new User();
    updatedUser.setId(userId);
    updatedUser.setUsername("updatedUser");
    updatedUser.setPassword("newPassword");

    when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
    when(userRepository.save(any())).thenReturn(updatedUser);

    // Act
    UserDTO result = userService.updateUser(userId, updatedUser);

    // Assert
    assertNotNull(result);
    assertEquals(userId, result.getId());
    assertEquals("updatedUser", result.getUsername());
    assertEquals(Collections.emptyList(), result.getQuizzes());
    verify(userRepository, times(1)).findById(userId);
    verify(userRepository, times(1)).save(any());
  }

  /**
   * Test the updateUser method with a user not found. It verifies that the method throws a
   * UserNotFoundException.
   *
   * @throws UserNotFoundException if the user is not found
   */
  @Test
  public void testUpdateUser_UserNotFound() {
    // Arrange
    Long userId = 1L;
    User updatedUser = new User();
    updatedUser.setId(userId);
    updatedUser.setUsername("updatedUser");
    updatedUser.setPassword("newPassword");

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(UserNotFoundException.class, () -> userService.updateUser(userId, updatedUser));
    verify(userRepository, times(1)).findById(userId);
    verify(userRepository, never()).save(any());
  }

  /** Test the getQuizzesByUserId method. It verifies that the method returns a set of quizzes. */
  @Test
  public void testGetQuizzesByUserId() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    user.setId(userId);
    Set<Quiz> quizzes = new HashSet<>();
    Quiz quiz1 = new Quiz();
    quiz1.setId(1L);
    quiz1.setTitle("Quiz 1");
    Quiz quiz2 = new Quiz();
    quiz2.setId(2L);
    quiz2.setTitle("Quiz 2");
    quizzes.add(quiz1);
    quizzes.add(quiz2);
    user.setQuizzes(quizzes);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    // Act
    Set<QuizDTO> result = userService.getQuizzesByUserId(userId);

    // Assert
    assertNotNull(result);
    assertEquals(2, result.size());
  }

  /**
   * Test the getQuizzesByUserId method with a user not found. It verifies that the method throws a
   * UserNotFoundException.
   *
   * @throws UserNotFoundException if the user is not found
   */
  @Test
  public void testGetQuizzesByUserId_UserNotFound() {
    // Arrange
    Long userId = 1L;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(UserNotFoundException.class, () -> userService.getQuizzesByUserId(userId));
    verify(userRepository, times(1)).findById(userId);
  }

  /** Test the save method. It verifies that the method saves a user to the database. */
  @Test
  public void testSave() {
    // Arrange
    User user = new User();
    user.setId(1L);
    user.setUsername("testUser");
    user.setPassword("password");

    when(userRepository.save(user)).thenReturn(user);

    // Act
    UserDTO savedUser = userService.save(user);

    // Assert
    assertNotNull(savedUser);
    assertEquals(user.getId(), savedUser.getId());
    assertEquals(user.getUsername(), savedUser.getUsername());
    assertEquals(Collections.emptyList(), savedUser.getQuizzes());
  }

  /** Test the getUserById method. It verifies that the method returns a user. */
  @Test
  public void testGetUserByIdDTO() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    user.setId(userId);
    user.setUsername("testUser");

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    // Act
    Optional<UserDTO> result = userService.getUserByIdDTO(userId);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(userId, result.get().getId());
    assertEquals("testUser", result.get().getUsername());
    assertEquals(Collections.emptyList(), result.get().getQuizzes());
  }

  /**
   * Test the getUserById method with a user not found. It verifies that the method returns an empty
   * optional.
   */
  @Test
  public void testGetUserByIdDTO_UserNotFound() {
    // Arrange
    Long userId = 1L;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    // Act
    Optional<UserDTO> result = userService.getUserByIdDTO(userId);

    // Assert
    assertFalse(result.isPresent());
  }

  /** Test the getUserByUsername method. It verifies that the method returns a user. */
  @Test
  public void testSuccessfulLogin() {
    // Arrange
    // Mocking user and its saved version
    User user = new User("testUser", "password");
    user.setId(1L);
    when(userRepository.save(any())).thenReturn(user);

    // Adding the user to the database
    UserDTO loggedInUserDTO = userService.addUser(user);
    assertNotNull(loggedInUserDTO); // Ensure that the user was successfully added

    // Encode the password for login
    String encodedPassword = passwordEncoder.encode("password");

    // Create a new user object for login
    User userToLogin = new User();
    userToLogin.setUsername("testUser");
    userToLogin.setPassword("password"); // Set the unencoded password

    // Mock UserRepository findByUsername method to return the user object
    when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

    // Act
    String result = userService.login(userToLogin);

    // Assert
    assertEquals("Token", result);
  }

  /**
   * Test the login method with an invalid user. It verifies that the method throws an
   * InvalidCredentialsException.
   *
   * @throws InvalidCredentialsException if the user is invalid
   */
  @Test
  public void testLogin_InvalidUser() {
    // Arrange
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("incorrectPassword");

    // Act & Assert
    assertThrows(InvalidCredentialsException.class, () -> userService.login(user));
  }

  /**
   * Test the login method with invalid credentials. It verifies that the method throws an
   * InvalidCredentialsException.
   *
   * @throws InvalidCredentialsException if the credentials are invalid
   */
  @Test
  public void testLogin_InvalidCredentials() {
    // Arrange
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("incorrectPassword");

    User wrongUser = new User();
    wrongUser.setUsername("testUser");
    wrongUser.setPassword("password");

    when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(wrongUser));

    // Act & Assert
    assertThrows(InvalidCredentialsException.class, () -> userService.login(user));
  }

  /** Test the getUserById method. It verifies that the method returns the expected user. */
  @Test
  public void getUserById() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    user.setId(userId);
    user.setUsername("testUser");

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    // Act
    UserDTO result = userService.getUserById(userId);

    // Assert
    assertNotNull(result);
    assertEquals("testUser", result.getUsername());
  }

  /** Test the getUserByUsername method. It verifies that the method returns the expected user. */
  @Test
  public void getUserByUsername() {
    // Arrange
    String username = "testUser";
    User user = new User();
    user.setUsername(username);

    when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

    // Act
    UserDTO result = userService.getUserByUsername(username);

    // Assert
    assertNotNull(result);
    assertEquals(username, result.getUsername());
  }
}
