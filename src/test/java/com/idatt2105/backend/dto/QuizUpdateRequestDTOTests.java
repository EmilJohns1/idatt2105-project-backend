package com.idatt2105.backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
