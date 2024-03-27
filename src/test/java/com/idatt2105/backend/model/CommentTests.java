package com.idatt2105.backend.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentTests {

  private Comment comment;

  @BeforeEach
  void setUp() {
    comment = new Comment();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetId() {
      Long id = 1L;
      comment.setId(id);
      assertEquals(id, comment.getId());
    }

    @Test
    void testGetContent() {
      String content = "This is a comment.";
      comment.setContent(content);
      assertEquals(content, comment.getContent());
    }

    @Test
    void testGetUser() {
      User user = new User();
      user.setId(1L);
      comment.setUser(user);
      assertEquals(user, comment.getUser());
    }

    @Test
    void testGetQuiz() {
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      comment.setQuiz(quiz);
      assertEquals(quiz, comment.getQuiz());
    }

    @Test
    void testGetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      comment.setCreationDate(creationDate);
      assertEquals(creationDate, comment.getCreationDate());
    }

    @Test
    void testGetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      comment.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, comment.getLastModifiedDate());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetId() {
      Long id = 1L;
      comment.setId(id);
      assertEquals(id, comment.getId());
    }

    @Test
    void testSetContent() {
      String content = "This is a comment.";
      comment.setContent(content);
      assertEquals(content, comment.getContent());
    }

    @Test
    void testSetUser() {
      User user = new User();
      user.setId(1L);
      comment.setUser(user);
      assertEquals(user, comment.getUser());
    }

    @Test
    void testSetQuiz() {
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      comment.setQuiz(quiz);
      assertEquals(quiz, comment.getQuiz());
    }

    @Test
    void testSetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      comment.setCreationDate(creationDate);
      assertEquals(creationDate, comment.getCreationDate());
    }

    @Test
    void testSetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      comment.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, comment.getLastModifiedDate());
    }

    @Test
    void setUserId() {
      Long userId = 1L;
      comment.setUserId(userId);
      assertEquals(userId, comment.getUser().getId());
    }

    @Test
    void setUserIdThrowsExceptionWhenParameterIsNull() {
      assertThrows(IllegalArgumentException.class, () -> comment.setUserId(null));
    }

    @Test
    void setQuizId() {
      Long quizId = 1L;
      comment.setQuizId(quizId);
      assertEquals(quizId, comment.getQuiz().getId());
    }

    @Test
    void setQuizIdThrowsExceptionWhenParameterIsNull() {
      assertThrows(IllegalArgumentException.class, () -> comment.setQuizId(null));
    }
  }
}
