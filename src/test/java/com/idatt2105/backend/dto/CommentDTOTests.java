package com.idatt2105.backend.dto;

import com.idatt2105.backend.model.Comment;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentDTOTests {

  private CommentDTO commentDTO;

  @BeforeEach
  void setUp() {
    commentDTO = new CommentDTO();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetContent() {
      String content = "This is a comment.";
      commentDTO.setContent(content);
      assertEquals(content, commentDTO.getContent());
    }

    @Test
    void testGetUserId() {
      Long userId = 1L;
      commentDTO.setUserId(userId);
      assertEquals(userId, commentDTO.getUserId());
    }

    @Test
    void testGetQuizId() {
      Long quizId = 1L;
      commentDTO.setQuizId(quizId);
      assertEquals(quizId, commentDTO.getQuizId());
    }

    @Test
    void testGetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      commentDTO.setCreationDate(creationDate);
      assertEquals(creationDate, commentDTO.getCreationDate());
    }

    @Test
    void testGetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      commentDTO.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, commentDTO.getLastModifiedDate());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetContent() {
      String content = "This is a comment.";
      commentDTO.setContent(content);
      assertEquals(content, commentDTO.getContent());
    }

    @Test
    void testSetUserId() {
      Long userId = 1L;
      commentDTO.setUserId(userId);
      assertEquals(userId, commentDTO.getUserId());
    }

    @Test
    void testSetQuizId() {
      Long quizId = 1L;
      commentDTO.setQuizId(quizId);
      assertEquals(quizId, commentDTO.getQuizId());
    }

    @Test
    void testSetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      commentDTO.setCreationDate(creationDate);
      assertEquals(creationDate, commentDTO.getCreationDate());
    }

    @Test
    void testSetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      commentDTO.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, commentDTO.getLastModifiedDate());
    }
  }

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