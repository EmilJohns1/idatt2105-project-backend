package com.idatt2105.backend.dto;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.model.Comment;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The CommentDTOTests class is a test class that tests the CommentDTO class. */
class CommentDTOTests {

  private CommentDTO commentDTO;

  @BeforeEach
  void setUp() {
    commentDTO = new CommentDTO();
  }

  /** The GetterTests class is a test class that tests the getters of the CommentDTO class. */
  @Nested
  class GetterTests {
    /**
     * This method tests the getContent method of the CommentDTO class. It verifies that the method
     * returns the correct content.
     */
    @Test
    void testGetContent() {
      String content = "This is a comment.";
      commentDTO.setContent(content);
      assertEquals(content, commentDTO.getContent());
    }

    /**
     * This method tests the getUserId method of the CommentDTO class. It verifies that the method
     * returns the correct user id.
     */
    @Test
    void testGetUserId() {
      Long userId = 1L;
      commentDTO.setUserId(userId);
      assertEquals(userId, commentDTO.getUserId());
    }

    /**
     * This method tests the getQuizId method of the CommentDTO class. It verifies that the method
     * returns the correct quiz id.
     */
    @Test
    void testGetQuizId() {
      Long quizId = 1L;
      commentDTO.setQuizId(quizId);
      assertEquals(quizId, commentDTO.getQuizId());
    }

    /**
     * This method tests the getCreationDate method of the CommentDTO class. It verifies that the
     * method returns the correct creation date.
     */
    @Test
    void testGetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      commentDTO.setCreationDate(creationDate);
      assertEquals(creationDate, commentDTO.getCreationDate());
    }

    /**
     * This method tests the getLastModifiedDate method of the CommentDTO class. It verifies that
     * the method returns the correct last modified date.
     */
    @Test
    void testGetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      commentDTO.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, commentDTO.getLastModifiedDate());
    }
  }

  /** The SetterTests class is a test class that tests the setters of the CommentDTO class. */
  @Nested
  class SetterTests {
    /**
     * This method tests the setContent method of the CommentDTO class. It verifies that the method
     * sets the correct content.
     */
    @Test
    void testSetContent() {
      String content = "This is a comment.";
      commentDTO.setContent(content);
      assertEquals(content, commentDTO.getContent());
    }

    /**
     * This method tests the setUserId method of the CommentDTO class. It verifies that the method
     * sets the correct user id.
     */
    @Test
    void testSetUserId() {
      Long userId = 1L;
      commentDTO.setUserId(userId);
      assertEquals(userId, commentDTO.getUserId());
    }

    /**
     * This method tests the setQuizId method of the CommentDTO class. It verifies that the method
     * sets the correct quiz id.
     */
    @Test
    void testSetQuizId() {
      Long quizId = 1L;
      commentDTO.setQuizId(quizId);
      assertEquals(quizId, commentDTO.getQuizId());
    }

    /**
     * This method tests the setCreationDate method of the CommentDTO class. It verifies that the
     * method sets the correct creation date.
     */
    @Test
    void testSetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      commentDTO.setCreationDate(creationDate);
      assertEquals(creationDate, commentDTO.getCreationDate());
    }

    /**
     * This method tests the setLastModifiedDate method of the CommentDTO class. It verifies that
     * the method sets the correct last modified date.
     */
    @Test
    void testSetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      commentDTO.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, commentDTO.getLastModifiedDate());
    }
  }

  /**
   * This method tests the toEntity method of the CommentDTO class. It verifies that the method
   * returns the correct Comment entity.
   */
  @Test
  void testToEntity() {
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setContent("This is a comment.");
    commentDTO.setUserId(1L);
    commentDTO.setQuizId(1L);
    commentDTO.setCreationDate(LocalDateTime.now());
    commentDTO.setLastModifiedDate(LocalDateTime.now());

    Comment comment = commentDTO.toEntity();

    assertEquals(commentDTO.getContent(), comment.getContent());
    assertEquals(commentDTO.getCreationDate(), comment.getCreationDate());
    assertEquals(commentDTO.getLastModifiedDate(), comment.getLastModifiedDate());
  }

  /**
   * This method tests the constructor of the CommentDTO class. It verifies that the constructor
   * sets the correct values.
   */
  @Test
  void constructorWithCommentParameterExtractsAllValues() {
    Comment comment = new Comment();
    comment.setContent("This is a comment.");
    comment.setCreationDate(LocalDateTime.now());
    comment.setLastModifiedDate(LocalDateTime.now());
    Quiz quiz = new Quiz();
    quiz.setId(1L);
    comment.setQuiz(quiz);
    User user = new User();
    user.setId(1L);
    comment.setUser(user);

    CommentDTO commentDTO = new CommentDTO(comment);

    assertEquals(comment.getContent(), commentDTO.getContent());
    assertEquals(comment.getCreationDate(), commentDTO.getCreationDate());
    assertEquals(comment.getLastModifiedDate(), commentDTO.getLastModifiedDate());
    assertEquals(comment.getQuiz().getId(), commentDTO.getQuizId());
    assertEquals(comment.getUser().getId(), commentDTO.getUserId());
  }
}
