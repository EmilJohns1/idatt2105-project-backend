package com.idatt2105.backend.dto;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.model.Comment;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;

import static org.junit.Assert.assertNotEquals;
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
     * This method tests the setContent and equals methods of the CommentDTO class. It verifies that
     * the method sets the correct content and that the equals method works as expected.
     */
    @Test
    void testSetContentAndEquals() {
      String content = "This is a comment.";
      String contentDuplicate = "This is a comment.";
      String differentString = "This is a different comment.";

      commentDTO.setContent(content);
      CommentDTO commentDTO2 = new CommentDTO();
      commentDTO2.setContent(contentDuplicate);
      CommentDTO commentDTO3 = new CommentDTO();
      commentDTO3.setContent(differentString);

      assertEquals(content, commentDTO.getContent());
      assertEquals(contentDuplicate, commentDTO2.getContent());
      assertEquals(differentString, commentDTO3.getContent());

      assertEquals(commentDTO, commentDTO2);
      assertNotEquals(commentDTO, commentDTO3);
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

  /**
   * This method tests the hashCode method of the CommentDTO class. It verifies that the method
   * returns the correct hash code and that the hash code is the same for equal objects.
   */
  @Test
  void testHashcode() {
    // Can't test creationDate and lastModifiedDate because we can't create them at the exact same
    // time
    CommentDTO commentDTO1 = new CommentDTO();
    commentDTO1.setContent("This is a comment.");
    commentDTO1.setUserId(1L);
    commentDTO1.setQuizId(1L);

    CommentDTO commentDTO2 = new CommentDTO();
    commentDTO2.setContent("This is a comment.");
    commentDTO2.setUserId(1L);
    commentDTO2.setQuizId(1L);

    CommentDTO commentDTO3 = new CommentDTO();
    commentDTO3.setContent("This is a different comment.");
    commentDTO3.setUserId(2L);
    commentDTO3.setQuizId(2L);

    assertEquals(commentDTO1.hashCode(), commentDTO2.hashCode());
    assertNotEquals(commentDTO1.hashCode(), commentDTO3.hashCode());

    assertEquals(commentDTO1, commentDTO2);
    assertEquals(commentDTO1.hashCode(), commentDTO2.hashCode());
    assertNotEquals(commentDTO2.hashCode(), commentDTO3.hashCode());
  }

  /**
   * This method tests the toString method of the CommentDTO class. It verifies that the method
   * returns the correct string representation of the object.
   */
  @Test
  void testToString() {
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setContent("This is a comment.");
    commentDTO.setUserId(1L);
    commentDTO.setQuizId(1L);
    commentDTO.setCreationDate(LocalDateTime.now());
    commentDTO.setLastModifiedDate(LocalDateTime.now());

    String expected =
        "CommentDTO(content=This is a comment., userId=1, quizId=1, creationDate="
            + commentDTO.getCreationDate()
            + ", lastModifiedDate="
            + commentDTO.getLastModifiedDate()
            + ")";
    assertEquals(expected, commentDTO.toString());
  }
}
