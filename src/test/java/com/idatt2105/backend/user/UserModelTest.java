package com.idatt2105.backend.user;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Test class for the User model.
 */
@SpringBootTest
public class UserModelTest {

  private User user;

  /*
   * Set up a new user before each test.
   */
  @BeforeEach
  public void setUp() {
    user = new User("testuser", "password");
  }

  /*
   * Test creating a new user with valid input.
   */
  @Test
  public void createUser_ValidInput_Success() {
    assertNotNull(user);
    assertEquals("testuser", user.getUsername());
    assertEquals("password", user.getPassword());
    assertTrue(user.getQuizzes().isEmpty());
    assertTrue(user.getQuizAttempts().isEmpty());
  }

  /*
   * Test adding a quiz to a user.
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

  /*
   * Test adding a quiz to a user with a null quiz.
   */
  @Test
  public void addQuiz_NullQuiz_NoChange() {
    user.addQuiz(null);
    assertTrue(user.getQuizzes().isEmpty());
  }

  /*
   * Test adding multiple quizzes to a user.
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

  /*
   * Test set username with valid input.
   */
  @Test
  public void setUsername_Valid_Success() {
    user.setUsername("newuser");
    assertEquals("newuser", user.getUsername());
  }

  /*
   * Test set password with valid input.
   */
  @Test
  public void setPassword_Valid_Success() {
    user.setPassword("newpassword");
    assertEquals("newpassword", user.getPassword());
  }

  /*
   * Test set id with valid input.
   */
  @Test
  public void setId_Valid_Success() {
    user.setId(123L);
    assertEquals(123L, user.getId());
  }

  /*
   * Test set quizzes with valid input.
   */
  @Test
  public void getUserName_Valid_Success() {
    assertEquals("testuser", user.getUsername());
  }

  /*
   * Test get password with valid input.
   */
  @Test
  public void getPassword_Valid_Success() {
    assertEquals("password", user.getPassword());
  }

  /*
   * Test add quiz with null quiz.
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

  /*
   * Test add duplicate quiz to user.
   */
  @Test
  public void addQuiz_AlreadyAdded_NoDuplicate() {
    Quiz quiz = new Quiz();
    quiz.setTitle("Test Quiz");
    user.addQuiz(quiz);
    user.addQuiz(quiz);
    assertEquals(1, user.getQuizzes().size());
  }

  /*
   * Test add quiz with null user in quiz.
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

  /*
   * Test equals method with two equal users.
   */
  @Test
  public void testEquals() {
    User user1 = new User("testuser", "password");
    User user2 = new User("testuser", "password");
    assertTrue(user1.equals(user2));
  }

  /*
   * Test equals method with two different users.
   */
  @Test
  public void testNotEquals() {
    User user1 = new User("testuser1", "password");
    User user2 = new User("testuser2", "password");
    assertFalse(user1.equals(user2));
  }

  /*
   * Test hash code method with two equal users.
   */
  @Test
  public void testHashCode() {
    User user1 = new User("testuser", "password");
    User user2 = new User("testuser", "password");
    assertEquals(user1.hashCode(), user2.hashCode());
  }

  /*
   * Test toString method.
   */
  @Test
  public void testToString() {
    assertEquals(
        "User(id=null, username=testuser, password=password, profilePictureUrl=null, quizzes=[], "
            + "quizAttempts=[])",
        user.toString());
  }

  /*
   * Test no argument constructor.
   */
  @Test
  public void testNoArgsConstructor() {
    User user = new User();
    assertNotNull(user);
  }

  /*
   * Test user and password constructor.
   */
  @Test
  public void testUserAndPasswordArgsConstructor() {
    User user = new User("testuser", "password");
    assertNotNull(user);
    assertEquals("testuser", user.getUsername());
    assertEquals("password", user.getPassword());
  }

  /*
   * Test id and username constructor.
   */
  @Test
  public void testIdAndUsernameConstructor() {
    User user = new User(1L, "testuser");
    assertNotNull(user);
    assertEquals(1L, user.getId());
    assertEquals("testuser", user.getUsername());
  }

  /*
   * Test all argument constructor, two last arguments are quizzes and quizAttempts.
   */
  @Test
  public void testAllArgsConstructor() {
    User user = new User(1L, "testuser", "password", "imageURL", new HashSet<>(), new HashSet<>());
    assertNotNull(user);
    assertEquals(1L, user.getId());
    assertEquals("testuser", user.getUsername());
    assertEquals("password", user.getPassword());
    assertTrue(user.getQuizzes().isEmpty());
    assertTrue(user.getQuizAttempts().isEmpty());
  }
}
