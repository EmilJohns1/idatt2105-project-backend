package com.idatt2105.backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The AlternativeDTOTests class is a test class that tests the AlternativeDTO class. */
class AlternativeDTOTests {
  private AlternativeDTO alternativeDTO;

  @BeforeEach
  void setUp() {
    alternativeDTO = new AlternativeDTO();
  }

  /** The Getters class is a test class that tests the getters of the AlternativeDTO class. */
  @Nested
  class Getters {
    /**
     * This method tests the getId method of the AlternativeDTO class. It verifies that the method
     * returns the correct id.
     */
    @Test
    void testGetId() {
      Long id = 1L;
      alternativeDTO.setId(id);
      assertEquals(id, alternativeDTO.getId());
    }

    /**
     * This method tests the getAlternativeText method of the AlternativeDTO class. It verifies that
     * the method returns the correct alternative text.
     */
    @Test
    void testGetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternativeDTO.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternativeDTO.getAlternativeText());
    }

    /**
     * This method tests the isCorrect method of the AlternativeDTO class. It verifies that the
     * method returns the correct boolean value.
     */
    @Test
    void testIsCorrect() {
      boolean isCorrect = true;
      alternativeDTO.setCorrect(isCorrect);
      assertEquals(isCorrect, alternativeDTO.isCorrect());
    }

    /**
     * This method tests the getQuestionId method of the AlternativeDTO class. It verifies that the
     * method returns the correct question id.
     */
    @Test
    void testGetQuestionId() {
      Long questionId = 1L;
      alternativeDTO.setQuestionId(questionId);
      assertEquals(questionId, alternativeDTO.getQuestionId());
    }
  }

  /** The Setters class is a test class that tests the setters of the AlternativeDTO class. */
  @Nested
  class Setters {
    /**
     * This method tests the setId method of the AlternativeDTO class. It verifies that the method
     * sets the correct id.
     */
    @Test
    void testSetId() {
      Long id = 1L;
      alternativeDTO.setId(id);
      assertEquals(id, alternativeDTO.getId());
    }

    /**
     * This method tests the setAlternativeText method of the AlternativeDTO class. It verifies that
     * the method sets the correct alternative text.
     */
    @Test
    void testSetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternativeDTO.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternativeDTO.getAlternativeText());
    }

    /**
     * This method tests the setCorrect method of the AlternativeDTO class. It verifies that the
     * method sets the correct boolean value.
     */
    @Test
    void testSetCorrect() {
      boolean isCorrect = true;
      alternativeDTO.setCorrect(isCorrect);
      assertEquals(isCorrect, alternativeDTO.isCorrect());
    }

    /**
     * This method tests the setQuestionId method of the AlternativeDTO class. It verifies that the
     * method sets the correct question id.
     */
    @Test
    void testSetQuestionId() {
      Long questionId = 1L;
      alternativeDTO.setQuestionId(questionId);
      assertEquals(questionId, alternativeDTO.getQuestionId());
    }
  }

  /**
   * The EqualsAndHashCodeAndtoString class is a test class that tests the equals, canEqual,
   * hashCode,
   */
  @Nested
  class EqualsAndHashCodeAndtoString {
    @Test
    void testEquals() {
      // Arrange
      AlternativeDTO alternative1 = new AlternativeDTO();
      alternative1.setId(1L);
      alternative1.setAlternativeText("Alternative Text");
      alternative1.setCorrect(true);
      alternative1.setQuestionId(1L);

      AlternativeDTO alternative2 = new AlternativeDTO();
      alternative2.setId(1L);
      alternative2.setAlternativeText("Alternative Text");
      alternative2.setCorrect(true);
      alternative2.setQuestionId(1L);

      AlternativeDTO alternative3 = new AlternativeDTO();
      alternative3.setId(2L);
      alternative3.setAlternativeText("Different Alternative Text");
      alternative3.setCorrect(false);
      alternative3.setQuestionId(2L);

      // Act & Assert
      assertEquals(alternative1, alternative2);
      assertNotEquals(alternative1, alternative3);
    }

    /** Test the canEqual method of the AlternativeDTO class. */
    @Test
    void testCanEqual() {
      // Arrange
      AlternativeDTO alternative1 = new AlternativeDTO();
      alternative1.setId(1L);
      alternative1.setAlternativeText("Alternative Text");
      alternative1.setCorrect(true);
      alternative1.setQuestionId(1L);

      AlternativeDTO alternative2 = new AlternativeDTO();
      alternative2.setId(1L);
      alternative2.setAlternativeText("Alternative Text");
      alternative2.setCorrect(true);
      alternative2.setQuestionId(1L);

      // Act & Assert
      assertTrue(alternative1.canEqual(alternative2));
    }

    /** Test the hashCode method of the AlternativeDTO class. */
    @Test
    void testHashCode() {
      // Arrange
      AlternativeDTO alternative1 = new AlternativeDTO();
      alternative1.setId(1L);
      alternative1.setAlternativeText("Alternative Text");
      alternative1.setCorrect(true);
      alternative1.setQuestionId(1L);

      AlternativeDTO alternative2 = new AlternativeDTO();
      alternative2.setId(1L);
      alternative2.setAlternativeText("Alternative Text");
      alternative2.setCorrect(true);
      alternative2.setQuestionId(1L);

      // Act & Assert
      assertEquals(alternative1.hashCode(), alternative2.hashCode());
    }

    /** Test the toString method of the AlternativeDTO class. */
    @Test
    void testToString() {
      // Arrange
      AlternativeDTO alternative = new AlternativeDTO();
      alternative.setId(1L);
      alternative.setAlternativeText("Alternative Text");
      alternative.setCorrect(true);
      alternative.setQuestionId(1L);

      // Act
      String result = alternative.toString();

      // Assert
      assertEquals(
          "AlternativeDTO(id=1, alternativeText=Alternative Text, isCorrect=true, questionId=1)",
          result);
    }
  }
}
