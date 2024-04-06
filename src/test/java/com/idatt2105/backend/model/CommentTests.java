package com.idatt2105.backend.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  }
}
