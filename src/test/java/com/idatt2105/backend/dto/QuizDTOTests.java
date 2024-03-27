package com.idatt2105.backend.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.Tag;
import com.idatt2105.backend.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizDTOTests {

  private QuizDTO quizDTO;

  @BeforeEach
  void setUp() {
    quizDTO = new QuizDTO();
  }

  @Nested
  class Constructor {
    @Test
    void constructorFromQuizExtractsAllValues() {
      Quiz quiz = new Quiz();
      Set<Tag> tags = new HashSet<>();
      tags.add(new Tag("Tag"));
      User user = new User();
      user.setId(1L);
      user.setUsername("Username");
      user.setPassword("Password");
      Set<User> users = new HashSet<>();
      users.add(user);

      quiz.setId(1L);
      quiz.setTitle("Quiz Title");
      quiz.setDescription("Quiz Description");
      quiz.setCreationDate(LocalDateTime.now());
      quiz.setLastModifiedDate(LocalDateTime.now());
      quiz.setUsers(users);
      quiz.setTags(tags);

      QuizDTO quizDTO = new QuizDTO(quiz);

      assertEquals(quiz.getId(), quizDTO.getId());
      assertEquals(quiz.getTitle(), quizDTO.getTitle());
      assertEquals(quiz.getDescription(), quizDTO.getDescription());
      assertEquals(quiz.getCreationDate(), quizDTO.getCreationDate());
      assertEquals(quiz.getLastModifiedDate(), quizDTO.getLastModifiedDate());
      assertEquals(quiz.getTags(), quizDTO.getTags());
      assertEquals(
          quiz.getUsers().iterator().next().getUsername(),
          quizDTO.getUserDTOs().iterator().next().getUsername());
    }
  }

  @Nested
  class GetterTests {
    @Test
    void testGetId() {
      Long id = 1L;
      quizDTO.setId(id);
      assertEquals(id, quizDTO.getId());
    }

    @Test
    void testGetTitle() {
      String title = "Quiz Title";
      quizDTO.setTitle(title);
      assertEquals(title, quizDTO.getTitle());
    }

    @Test
    void testGetDescription() {
      String description = "Quiz Description";
      quizDTO.setDescription(description);
      assertEquals(description, quizDTO.getDescription());
    }

    @Test
    void testGetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      quizDTO.setCreationDate(creationDate);
      assertEquals(creationDate, quizDTO.getCreationDate());
    }

    @Test
    void testGetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      quizDTO.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, quizDTO.getLastModifiedDate());
    }

    @Test
    void testGetTags() {
      Set<Tag> tags = new HashSet<>();
      Tag tag = new Tag();
      tag.setId(1L);
      tags.add(tag);
      quizDTO.setTags(tags);
      assertEquals(tags, quizDTO.getTags());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetId() {
      Long id = 1L;
      quizDTO.setId(id);
      assertEquals(id, quizDTO.getId());
    }

    @Test
    void testSetTitle() {
      String title = "Quiz Title";
      quizDTO.setTitle(title);
      assertEquals(title, quizDTO.getTitle());
    }

    @Test
    void testSetDescription() {
      String description = "Quiz Description";
      quizDTO.setDescription(description);
      assertEquals(description, quizDTO.getDescription());
    }

    @Test
    void testSetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      quizDTO.setCreationDate(creationDate);
      assertEquals(creationDate, quizDTO.getCreationDate());
    }

    @Test
    void testSetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      quizDTO.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, quizDTO.getLastModifiedDate());
    }

    @Test
    void testSetTags() {
      Set<Tag> tags = new HashSet<>();
      Tag tag = new Tag();
      tag.setId(1L);
      tags.add(tag);
      quizDTO.setTags(tags);
      assertEquals(tags, quizDTO.getTags());
    }
  }

  @Test
  void testToEntity() {
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setId(1L);
    quizDTO.setTitle("Quiz Title");
    quizDTO.setDescription("Quiz Description");
    quizDTO.setCreationDate(LocalDateTime.now());
    quizDTO.setLastModifiedDate(LocalDateTime.now());

    Quiz quiz = quizDTO.toEntity();

    assertEquals(quizDTO.getId(), quiz.getId());
    assertEquals(quizDTO.getTitle(), quiz.getTitle());
    assertEquals(quizDTO.getDescription(), quiz.getDescription());
    assertEquals(quizDTO.getCreationDate(), quiz.getCreationDate());
    assertEquals(quizDTO.getLastModifiedDate(), quiz.getLastModifiedDate());
  }

  @Nested
  class Builder {
    @Test
    void testBuilder() {
      LocalDateTime creationDate = LocalDateTime.now();
      LocalDateTime lastModifiedDate = LocalDateTime.now();

      QuizDTO quizDTO =
          new QuizDTO.Builder()
              .setId(1L)
              .setTitle("Quiz Title")
              .setDescription("Quiz Description")
              .setCreationDate(creationDate)
              .setLastModifiedDate(lastModifiedDate)
              .build();

      assertEquals(1L, quizDTO.getId());
      assertEquals("Quiz Title", quizDTO.getTitle());
      assertEquals("Quiz Description", quizDTO.getDescription());
      assertEquals(creationDate, quizDTO.getCreationDate());
      assertEquals(lastModifiedDate, quizDTO.getLastModifiedDate());
    }
  }
}
