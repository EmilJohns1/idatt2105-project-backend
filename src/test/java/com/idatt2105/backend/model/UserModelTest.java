package com.idatt2105.backend.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The UserModelTest class is a test class that tests the User class. */
@SpringBootTest
public class UserModelTest {

  private User user;

  @BeforeEach
  public void setUp() {
    user = new User("testuser", "password");
  }

  /**
   * This method tests the createUser method of the User class. It verifies that the method creates
   * a user with the correct username and password.
   */
  @Test
  public void createUser_ValidInput_Success() {
    assertNotNull(user);
    assertEquals("testuser", user.getUsername());
    assertEquals("password", user.getPassword());
    assertTrue(user.getQuizzes().isEmpty());
    assertTrue(user.getQuizAttempts().isEmpty());
  }

  /**
   * This method tests the addQuiz method of the User class. It verifies that the method adds a quiz
   * to the user.
   */
  @Test
  public void addQuiz_ValidQuiz_Success() {
    Quiz quiz = new Quiz();
    quiz.setTitle("Test Quiz");
    user.addQuiz(quiz);
    assertEquals(1, user.getQuizzes().size());
    assertTrue(user.getQuizzes().contains(quiz));
    assertTrue(quiz.getUsers().contains(user));
  }

  /**
   * This method tests the addQuiz method of the User class. It verifies that the method does not
   * add a null quiz to the user.
   */
  @Test
  public void addQuiz_NullQuiz_NoChange() {
    user.addQuiz(null);
    assertTrue(user.getQuizzes().isEmpty());
  }

  /**
   * This method tests the addQuiz method of the User class. It verifies that the method adds
   * multiple quizzes to the user.
   */
  @Test
  public void addQuiz_MultipleQuizzes_Success() {
    Set<Quiz> quizzes = new HashSet<>();
    for (int i = 0; i < 3; i++) {
      Quiz quiz = new Quiz();
      quiz.setTitle("Test Quiz " + i);
      quizzes.add(quiz);
      user.addQuiz(quiz);
    }
    assertEquals(3, user.getQuizzes().size());
    assertTrue(user.getQuizzes().containsAll(quizzes));
    quizzes.forEach(quiz -> assertTrue(quiz.getUsers().contains(user)));
  }

  /**
   * This method tests the setUserName method of the User class. It verifies that the method sets
   * the correct username.
   */
  @Test
  public void setUsername_Valid_Success() {
    user.setUsername("newuser");
    assertEquals("newuser", user.getUsername());
  }

  /**
   * This method tests the setPassword method of the User class. It verifies that the method sets
   * the correct password.
   */
  @Test
  public void setPassword_Valid_Success() {
    user.setPassword("newpassword");
    assertEquals("newpassword", user.getPassword());
  }

  /**
   * This method tests the setQuizzes method of the User class. It verifies that the method sets the
   * correct quizzes.
   */
  @Test
  public void setId_Valid_Success() {
    user.setId(123L);
    assertEquals(123L, user.getId());
  }

  /**
   * This method tests the getUserName method of the User class. It verifies that the method returns
   * the correct username.
   */
  @Test
  public void getUserName_Valid_Success() {
    assertEquals("testuser", user.getUsername());
  }

  /**
   * This method tests the getPassword method of the User class. It verifies that the method returns
   * the correct password.
   */
  @Test
  public void getPassword_Valid_Success() {
    assertEquals("password", user.getPassword());
  }

  /**
   * This method tests the addQuiz method of the User class. It verifies that a quiz is not added to
   * the user if the quiz is null.
   */
  @Test
  public void addQuiz_NullQuiz_NoChangeInUser() {
    Quiz quiz = null;
    try {
      user.addQuiz(quiz);
    } catch (NullPointerException e) {
      assertTrue(user.getQuizzes().isEmpty());
    }
  }

  /**
   * This method tests the addQuiz method of the User class. It verifies that a quiz is not added to
   * the user if the quiz is already added.
   */
  @Test
  public void addQuiz_AlreadyAdded_NoDuplicate() {
    Quiz quiz = new Quiz();
    quiz.setTitle("Test Quiz");
    user.addQuiz(quiz);
    user.addQuiz(quiz);
    assertEquals(1, user.getQuizzes().size());
  }

  /**
   * This method tests the addQuiz method of the User class. It verifies that a quiz is not added to
   * the user if the user is null.
   */
  @Test
  public void addQuiz_NullUserInQuiz_NoChangeInUser() {
    Quiz quiz = new Quiz();
    quiz.setTitle("Test Quiz");
    quiz.setUsers(null);
    try {
      user.addQuiz(quiz);
    } catch (NullPointerException e) {
      assertNull(quiz.getUsers());
    }
  }

  /**
   * This method tests the equals method of the User class. It verifies that the method returns true
   * when the objects are equal.
   */
  @Test
  public void testEquals() {
    User user1 = new User("testuser", "password");
    User user2 = new User("testuser", "password");
    assertTrue(user1.equals(user2));
  }

  /**
   * This method tests the equals method of the User class. It verifies that the method returns
   * false when the objects are not equal.
   */
  @Test
  public void testNotEquals() {
    User user1 = new User("testuser1", "password");
    User user2 = new User("testuser2", "password");
    assertFalse(user1.equals(user2));
  }

  /**
   * This method tests the equals method of the User class. It verifies that the method returns true
   * when the objects are equal.
   */
  @Test
  public void testHashCode() {
    User user1 = new User("testuser", "password");
    User user2 = new User("testuser", "password");
    assertEquals(user1.hashCode(), user2.hashCode());
  }

  /**
   * This method tests the toString method of the User class. It verifies that the method returns a
   * string.
   */
  @Test
  public void testToString() {
    assertDoesNotThrow(() -> user.toString());
  }

  /**
   * This method tests the no argument constructor of the User class. It verifies that the method
   * creates a user with no arguments.
   */
  @Test
  public void testNoArgsConstructor() {
    User user = new User();
    assertNotNull(user);
  }

  /**
   * This method tests the user and password constructor of the User class. It verifies that the
   * constructor creates a user with a username and password.
   */
  @Test
  public void testUserAndPasswordArgsConstructor() {
    User user = new User("testuser", "password");
    assertNotNull(user);
    assertEquals("testuser", user.getUsername());
    assertEquals("password", user.getPassword());
  }

  /**
   * This method tests the id and username constructor of the User class. It verifies that the
   * constructor creates a user with an id and username.
   */
  @Test
  public void testIdAndUsernameConstructor() {
    User user = new User(1L, "testuser");
    assertNotNull(user);
    assertEquals(1L, user.getId());
    assertEquals("testuser", user.getUsername());
  }

  /**
   * This method tests the all argument constructor of the User class. It verifies that the
   * constructor creates a user with all arguments.
   */
  @Test
  public void testAllArgsConstructor() {
    User user =
        new User(1L, "testuser", "password", "imageURL", "USER", new HashSet<>(), new HashSet<>());
    assertNotNull(user);
    assertEquals(1L, user.getId());
    assertEquals("testuser", user.getUsername());
    assertEquals("password", user.getPassword());
    assertTrue(user.getQuizzes().isEmpty());
    assertTrue(user.getQuizAttempts().isEmpty());
  }
}
