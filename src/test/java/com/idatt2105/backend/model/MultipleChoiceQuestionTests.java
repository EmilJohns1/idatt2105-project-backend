package com.idatt2105.backend.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.dto.AlternativeDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The MultipleChoiceQuestionTests class is a test class that tests the MultipleChoiceQuestion
 * class.
 */
class MultipleChoiceQuestionTests {

  private MultipleChoiceQuestion multipleChoiceQuestion;

  @BeforeEach
  void setUp() {
    multipleChoiceQuestion = new MultipleChoiceQuestion();
  }

  /**
   * The GetterTests class is a test class that tests the getters of the MultipleChoiceQuestion
   * class.
   */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the MultipleChoiceQuestion class. It verifies that the
     * method returns the correct id.
     */
    @Test
    void testGetId() {
      Long id = 1L;
      multipleChoiceQuestion.setId(id);
      assertEquals(id, multipleChoiceQuestion.getId());
    }

    /**
     * This method tests the getAlternatives method of the MultipleChoiceQuestion class. It verifies
     * that the method returns the correct alternatives.
     */
    @Test
    void testGetAlternatives() {
      Set<Alternative> alternatives = new HashSet<>();
      Alternative alternative = new Alternative();
      alternative.setAlternativeText("Alternative text");
      alternatives.add(alternative);
      multipleChoiceQuestion.setAlternatives(alternatives);
      assertEquals(alternatives, multipleChoiceQuestion.getAlternatives());
    }
  }

  /**
   * The SetterTests class is a test class that tests the setters of the MultipleChoiceQuestion
   * class.
   */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the MultipleChoiceQuestion class. It verifies that the
     * method sets the correct id.
     */
    @Test
    void testSetId() {
      Long id = 1L;
      multipleChoiceQuestion.setId(id);
      assertEquals(id, multipleChoiceQuestion.getId());
    }

    /**
     * This method tests the setAlternatives method of the MultipleChoiceQuestion class. It verifies
     * that the method sets the correct alternatives.
     */
    @Test
    void testSetAlternatives() {
      Set<Alternative> alternatives = new HashSet<>();
      Alternative alternative = new Alternative();
      alternative.setId(1L);
      alternatives.add(alternative);
      multipleChoiceQuestion.setAlternatives(alternatives);
      assertEquals(alternatives, multipleChoiceQuestion.getAlternatives());
    }

    /**
     * This method tests the setQuestionText method of the MultipleChoiceQuestion class. It verifies
     * that the method throws an exception when the parameter is null.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void addAlternativeThrowsExceptionWhenParameterIsNull() {
      assertThrows(
          IllegalArgumentException.class, () -> multipleChoiceQuestion.addAlternative(null));
    }
  }

  /**
   * This method tests the addAlternative method of the MultipleChoiceQuestion class. It verifies
   * that the method adds the correct alternative.
   */
  @Test
  void testAddAlternative() {
    AlternativeDTO alternative = new AlternativeDTO();
    alternative.setAlternativeText("Alternative text");
    multipleChoiceQuestion.addAlternative(alternative);
    assertEquals(
        "Alternative text",
        multipleChoiceQuestion.getAlternatives().iterator().next().getAlternativeText());
  }
}
