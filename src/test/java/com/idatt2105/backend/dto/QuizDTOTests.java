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

/** The QuizDTOTests class is a test class that tests the QuizDTO class. */
class QuizDTOTests {

  private QuizDTO quizDTO;

  @BeforeEach
  void setUp() {
    quizDTO = new QuizDTO();
  }

  /** The Constructor class is a test class that tests the constructor of the QuizDTO class. */
  @Nested
  class Constructor {
    /**
     * This method tests the constructor of the QuizDTO class. It verifies that the object is
     * instantiated correctly.
     */
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

  /** The GetterTests class is a test class that tests the getters of the QuizDTO class. */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the QuizDTO class. It verifies that the method returns
     * the correct id.
     */
    @Test
    void testGetId() {
      Long id = 1L;
      quizDTO.setId(id);
      assertEquals(id, quizDTO.getId());
    }

    /**
     * This method tests the getTitle method of the QuizDTO class. It verifies that the method
     * returns the correct title.
     */
    @Test
    void testGetTitle() {
      String title = "Quiz Title";
      quizDTO.setTitle(title);
      assertEquals(title, quizDTO.getTitle());
    }

    /**
     * This method tests the getDescription method of the QuizDTO class. It verifies that the method
     * returns the correct description.
     */
    @Test
    void testGetDescription() {
      String description = "Quiz Description";
      quizDTO.setDescription(description);
      assertEquals(description, quizDTO.getDescription());
    }

    /**
     * This method tests the getCreationDate method of the QuizDTO class. It verifies that the
     * method returns the correct creation date.
     */
    @Test
    void testGetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      quizDTO.setCreationDate(creationDate);
      assertEquals(creationDate, quizDTO.getCreationDate());
    }

    /**
     * This method tests the getLastModifiedDate method of the QuizDTO class. It verifies that the
     * method returns the correct last modified date.
     */
    @Test
    void testGetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      quizDTO.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, quizDTO.getLastModifiedDate());
    }

    /**
     * This method tests the getTags method of the QuizDTO class. It verifies that the method
     * returns the correct tags.
     */
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

  /** The SetterTests class is a test class that tests the setters of the QuizDTO class. */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the QuizDTO class. It verifies that the method sets the
     * correct id.
     */
    @Test
    void testSetId() {
      Long id = 1L;
      quizDTO.setId(id);
      assertEquals(id, quizDTO.getId());
    }

    /**
     * This method tests the setTitle method of the QuizDTO class. It verifies that the method sets
     * the correct title.
     */
    @Test
    void testSetTitle() {
      String title = "Quiz Title";
      quizDTO.setTitle(title);
      assertEquals(title, quizDTO.getTitle());
    }

    /**
     * This method tests the setDescription method of the QuizDTO class. It verifies that the method
     * sets the correct description.
     */
    @Test
    void testSetDescription() {
      String description = "Quiz Description";
      quizDTO.setDescription(description);
      assertEquals(description, quizDTO.getDescription());
    }

    /**
     * This method tests the setCreationDate method of the QuizDTO class. It verifies that the
     * method sets the correct creation date.
     */
    @Test
    void testSetCreationDate() {
      LocalDateTime creationDate = LocalDateTime.now();
      quizDTO.setCreationDate(creationDate);
      assertEquals(creationDate, quizDTO.getCreationDate());
    }

    /**
     * This method tests the setLastModifiedDate method of the QuizDTO class. It verifies that the
     * method sets the correct last modified date.
     */
    @Test
    void testSetLastModifiedDate() {
      LocalDateTime lastModifiedDate = LocalDateTime.now();
      quizDTO.setLastModifiedDate(lastModifiedDate);
      assertEquals(lastModifiedDate, quizDTO.getLastModifiedDate());
    }

    /**
     * This method tests the setTags method of the QuizDTO class. It verifies that the method sets
     * the correct tags.
     */
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

  /**
   * This method tests the toEntity method of the QuizDTO class. It verifies that the method returns
   * the correct Quiz entity.
   */
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

  /** The Builder class is a test class that tests the Builder of the QuizDTO class. */
  @Nested
  class Builder {
    /**
     * This method tests the Builder of the QuizDTO class. It verifies that the builder sets the
     * correct values.
     */
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
