package com.idatt2105.backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
