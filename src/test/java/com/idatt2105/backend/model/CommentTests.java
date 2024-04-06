package com.idatt2105.backend.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The CommentTests class is a test class that tests the Comment class. */
class CommentTests {

  private Comment comment;

  @BeforeEach
  void setUp() {
    comment = new Comment();
  }

  /** The GetterTests class is a test class that tests the getters of the Comment class. */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the Comment class. It verifies that the method returns
     * the correct id.
     */
    @Test
    void testGetId() {
      Long id = 1L;
      comment.setId(id);
      assertEquals(id, comment.getId());
    }

    /**
     * This method tests the getContent method of the Comment class. It verifies that the method
     * returns the correct content.
     */
    @Test
    void testGetContent() {
      String content = "This is a comment.";
      comment.setContent(content);
      assertEquals(content, comment.getContent());
    }

    /**
     * This method tests the getUser method of the Comment class. It verifies that the method
     * returns the correct user.
     */
    @Test
    void testGetUser() {
      User user = new User();
      user.setId(1L);
      comment.setUser(user);
      assertEquals(user, comment.getUser());
    }

    /**
     * This method tests the getQuiz method of the Comment class. It verifies that the method
     * returns the correct quiz.
     */
    @Test
    void testGetQuiz() {
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      comment.setQuiz(quiz);
      assertEquals(quiz, comment.getQuiz());
    }

    /**
     * This method tests the getCreationDate method of the Comment class. It verifies that the
     * method returns the correct creation date.
     */
    @Test
    void testGetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      comment.setCreationDate(creationDate);
      assertEquals(creationDate, comment.getCreationDate());
    }

    /**
     * This method tests the getLastModifiedDate method of the Comment class. It verifies that the
     * method returns the correct last modified date.
     */
    @Test
    void testGetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      comment.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, comment.getLastModifiedDate());
    }
  }

  /** The SetterTests class is a test class that tests the setters of the Comment class. */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the Comment class. It verifies that the method sets the
     * correct id.
     */
    @Test
    void testSetId() {
      Long id = 1L;
      comment.setId(id);
      assertEquals(id, comment.getId());
    }

    /**
     * This method tests the setContent method of the Comment class. It verifies that the method
     * sets the correct content.
     */
    @Test
    void testSetContent() {
      String content = "This is a comment.";
      comment.setContent(content);
      assertEquals(content, comment.getContent());
    }

    /**
     * This method tests the setUser method of the Comment class. It verifies that the method sets
     * the correct user.
     */
    @Test
    void testSetUser() {
      User user = new User();
      user.setId(1L);
      comment.setUser(user);
      assertEquals(user, comment.getUser());
    }

    /**
     * This method tests the setQuiz method of the Comment class. It verifies that the method sets
     * the correct quiz.
     */
    @Test
    void testSetQuiz() {
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      comment.setQuiz(quiz);
      assertEquals(quiz, comment.getQuiz());
    }

    /**
     * This method tests the setCreationDate method of the Comment class. It verifies that the
     * method sets the correct creation date.
     */
    @Test
    void testSetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      comment.setCreationDate(creationDate);
      assertEquals(creationDate, comment.getCreationDate());
    }

    /**
     * This method tests the setLastModifiedDate method of the Comment class. It verifies that the
     * method sets the correct last modified date.
     */
    @Test
    void testSetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      comment.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, comment.getLastModifiedDate());
    }

    /**
     * This method tests the constructor, toString and hashCode methods of the Comment class. It
     * verifies that the constructor sets the correct values and that the toString and hashCode
     * methods return the correct values.
     */
    @Test
    void testConstructorHashCodeToString() {
      // Arrange
      Long id = 1L;
      String content = "This is a comment.";
      User user = new User();
      user.setId(1L);
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      LocalDateTime creationDate = LocalDateTime.now();
      LocalDateTime lastModifiedDate = LocalDateTime.now();

      // Act
      Comment comment = new Comment(id, content, user, quiz, creationDate, lastModifiedDate);

      // Assert
      assertEquals(id, comment.getId());
      assertEquals(content, comment.getContent());
      assertEquals(user, comment.getUser());
      assertEquals(quiz, comment.getQuiz());
      assertEquals(creationDate, comment.getCreationDate());
      assertEquals(lastModifiedDate, comment.getLastModifiedDate());

      assertEquals(comment, comment);
      assertEquals(comment.hashCode(), comment.hashCode());
      assertEquals(
          ("Comment(id=1, content=This is a comment., user=User(id=1, username=null, password=null, profilePictureUrl=null, role=null, quizzes=[], quizAttempts=[]), quiz=Quiz(id=1, title=null, description=null, quizPictureUrl=null, creationDate=null, lastModifiedDate=null, isPublic=false, randomizedOrder=false, authorId=null, users=[], questions=[], tags=[], category=null)"
              + ", creationDate="
              + creationDate
              + ", lastModifiedDate="
              + lastModifiedDate
              + ")"),
          comment.toString());
    }

    /**
     * This method tests the equals method of the Comment class. It verifies that the method returns
     * true when comparing two objects with the same values and false when comparing two objects
     * with different values.
     */
    @Test
    void testEquals() {
      // Arrange
      Comment comment1 = new Comment();
      comment1.setId(1L);
      comment1.setContent("This is a comment.");
      User user1 = new User();
      user1.setId(1L);
      comment1.setUser(user1);
      Quiz quiz1 = new Quiz();
      quiz1.setId(1L);
      comment1.setQuiz(quiz1);

      Comment comment2 = new Comment();
      comment2.setId(1L);
      comment2.setContent("This is a comment.");
      comment2.setUser(user1);
      comment2.setQuiz(quiz1);

      // Act & Assert
      assertTrue(comment1.equals(comment2));
      assertTrue(comment1.canEqual(comment2));
    }
  }
}
