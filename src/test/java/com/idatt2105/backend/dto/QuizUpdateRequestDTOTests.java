package com.idatt2105.backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The QuizUpdateRequestDTOTests class is a test class that tests the QuizUpdateRequestDTO class.
 */
class QuizUpdateRequestDTOTests {

  private QuizUpdateRequestDTO quizUpdateRequestDTO;

  @BeforeEach
  void setUp() {
    quizUpdateRequestDTO = new QuizUpdateRequestDTO();
  }

  /**
   * The GetterTests class is a test class that tests the getters of the QuizUpdateRequestDTO class.
   */
  @Nested
  class GetterTests {
    /**
     * This method tests the getTitle method of the QuizUpdateRequestDTO class. It verifies that the
     * method returns the correct title.
     */
    @Test
    void testGetTitle() {
      String title = "Quiz Title";
      quizUpdateRequestDTO.setTitle(title);
      assertEquals(title, quizUpdateRequestDTO.getTitle());
    }

    /**
     * This method tests the getDescription method of the QuizUpdateRequestDTO class. It verifies
     * that the method returns the correct description.
     */
    @Test
    void testGetDescription() {
      String description = "Quiz Description";
      quizUpdateRequestDTO.setDescription(description);
      assertEquals(description, quizUpdateRequestDTO.getDescription());
    }

    /**
     * This method tests the getQuizPictureUrl method of the QuizUpdateRequestDTO class. It verifies
     * that the method returns the correct quiz picture URL.
     */
    @Test
    void testGetQuizPictureUrl() {
      // Arrange
      String quizPictureUrl = "https://example.com/quiz.jpg";
      quizUpdateRequestDTO.setQuizPictureUrl(quizPictureUrl);

      // Act & Assert
      assertEquals(quizPictureUrl, quizUpdateRequestDTO.getQuizPictureUrl());
    }
  }

  /**
   * The SetterTests class is a test class that tests the setters of the QuizUpdateRequestDTO class.
   */
  @Nested
  class SetterTests {
    /**
     * This method tests the setTitle method of the QuizUpdateRequestDTO class. It verifies that the
     * method sets the correct title.
     */
    @Test
    void testSetTitle() {
      String title = "Quiz Title";
      quizUpdateRequestDTO.setTitle(title);
      assertEquals(title, quizUpdateRequestDTO.getTitle());
    }

    /**
     * This method tests the setDescription method of the QuizUpdateRequestDTO class. It verifies
     * that the method sets the correct description.
     */
    @Test
    void testSetDescription() {
      String description = "Quiz Description";
      quizUpdateRequestDTO.setDescription(description);
      assertEquals(description, quizUpdateRequestDTO.getDescription());
    }

    /**
     * This method tests the setQuizPictureUrl method of the QuizUpdateRequestDTO class. It verifies
     * that the method sets the correct quiz picture URL.
     */
    @Test
    void testSetQuizPictureUrl() {
      // Arrange
      String quizPictureUrl = "https://example.com/quiz.jpg";
      quizUpdateRequestDTO.setQuizPictureUrl(quizPictureUrl);

      // Act & Assert
      assertEquals(quizPictureUrl, quizUpdateRequestDTO.getQuizPictureUrl());
    }
  }

  /**
   * The EqualsAndHashCodeAndConstructorTests class is a test class that tests the equals, hashCode,
   * and constructor of the QuizUpdateRequestDTO class.
   */
  @Nested
  class EqualsAndHashCodeAndConstructorTests {
    /**
     * This method tests the equals method of the QuizUpdateRequestDTO class. It verifies that the
     * method returns true when comparing two objects with the same title and description, and false
     * otherwise.
     */
    @Test
    void testEquals() {
      // Arrange
      QuizUpdateRequestDTO quiz1 = new QuizUpdateRequestDTO("Title", "Description");
      QuizUpdateRequestDTO quiz2 = new QuizUpdateRequestDTO("Title", "Description");
      QuizUpdateRequestDTO quiz3 = new QuizUpdateRequestDTO("Different Title", "Description");

      // Act & Assert
      assertEquals(quiz1, quiz2);
      assertEquals(quiz1, quiz1);
      assertNotEquals(quiz1, quiz3);
    }

    /**
     * This method tests the hashCode method of the QuizUpdateRequestDTO class. It verifies that the
     * method returns the same hash code for two objects with the same title and description.
     */
    @Test
    void testHashCode() {
      // Arrange
      QuizUpdateRequestDTO quiz1 = new QuizUpdateRequestDTO("Title", "Description");
      QuizUpdateRequestDTO quiz2 = new QuizUpdateRequestDTO("Title", "Description");

      // Act & Assert
      assertEquals(quiz1.hashCode(), quiz2.hashCode());
    }

    /**
     * This method tests the constructor of the QuizUpdateRequestDTO class. It verifies that the
     * constructor sets the correct title and description.
     */
    @Test
    void testConstructor() {
      // Arrange
      String title = "Quiz Title";
      String description = "Quiz Description";
      String quizPictureUrl = "https://example.com/quiz.jpg";

      // Act
      QuizUpdateRequestDTO quizUpdateRequestDTO =
          new QuizUpdateRequestDTO(title, description, quizPictureUrl);

      // Assert
      assertEquals(title, quizUpdateRequestDTO.getTitle());
      assertEquals(description, quizUpdateRequestDTO.getDescription());
      assertEquals(quizPictureUrl, quizUpdateRequestDTO.getQuizPictureUrl());
    }

    /**
     * This method tests the toString method of the QuizUpdateRequestDTO class. It verifies that the
     * method returns a string representation of the object.
     */
    @Test
    void testToString() {
      // Arrange
      String title = "Quiz Title";
      String description = "Quiz Description";
      QuizUpdateRequestDTO quizUpdateRequestDTO = new QuizUpdateRequestDTO(title, description);

      // Act
      String result = quizUpdateRequestDTO.toString();

      // Assert
      assertTrue(result.contains(title));
      assertTrue(result.contains(description));
    }

    /**
     * This method tests the canEqual method of the QuizUpdateRequestDTO class. It verifies that the
     * method returns true when comparing two objects of the same class, and false otherwise.
     */
    @Test
    void testCanEqual() {
      // Arrange
      QuizUpdateRequestDTO quiz1 = new QuizUpdateRequestDTO();
      QuizUpdateRequestDTO quiz2 = new QuizUpdateRequestDTO();
      Object obj = new Object();

      // Act & Assert
      assertTrue(quiz1.canEqual(quiz2));
      assertFalse(quiz1.canEqual(obj));
    }

    /**
     * This method tests the constructor of the QuizUpdateRequestDTO class. It verifies that the
     * constructor sets the correct title and description.
     */
    @Test
    void constructorTest() {
      String title = "Quiz Title";
      String description = "Quiz Description";
      QuizUpdateRequestDTO quizUpdateRequestDTO = new QuizUpdateRequestDTO(title, description);
      assertEquals(title, quizUpdateRequestDTO.getTitle());
      assertEquals(description, quizUpdateRequestDTO.getDescription());
    }
  }
}
