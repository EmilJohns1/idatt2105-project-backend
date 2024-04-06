package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The AlternativeTests class is a test class that tests the Alternative class. */
class AlternativeTests {

  private Alternative alternative;

  @BeforeEach
  void setUp() {
    alternative = new Alternative();
  }

  /** The GetterTests class is a test class that tests the getters of the Alternative class. */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the Alternative class. It verifies that the method
     * returns the correct id.
     */
    @Test
    void testGetId() {
      Long id = 1L;
      alternative.setId(id);
      assertEquals(id, alternative.getId());
    }

    /**
     * This method tests the getAlternativeText method of the Alternative class. It verifies that
     * the method returns the correct alternative text.
     */
    @Test
    void testGetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternative.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternative.getAlternativeText());
    }

    /**
     * This method tests the isCorrect method of the Alternative class. It verifies that the method
     * returns the correct boolean value.
     */
    @Test
    void testIsCorrect() {
      boolean isCorrect = true;
      alternative.setCorrect(isCorrect);
      assertTrue(alternative.isCorrect());
    }

    /**
     * This method tests the getQuestion method of the Alternative class. It verifies that the
     * method returns the correct question.
     */
    @Test
    void testGetQuestion() {
      MultipleChoiceQuestion question = new MultipleChoiceQuestion();
      question.setId(1L);
      alternative.setQuestion(question);
      assertEquals(question, alternative.getQuestion());
    }
  }

  /** The SetterTests class is a test class that tests the setters of the Alternative class. */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the Alternative class. It verifies that the method sets
     * the correct id.
     */
    @Test
    void testSetId() {
      Long id = 1L;
      alternative.setId(id);
      assertEquals(id, alternative.getId());
    }

    /**
     * This method tests the setAlternativeText method of the Alternative class. It verifies that
     * the method sets the correct alternative text.
     */
    @Test
    void testSetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternative.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternative.getAlternativeText());
    }

    /**
     * This method tests the setCorrect method of the Alternative class. It verifies that the method
     * sets the correct boolean value.
     */
    @Test
    void testSetCorrect() {
      boolean isCorrect = true;
      alternative.setCorrect(isCorrect);
      assertTrue(alternative.isCorrect());
    }

    /**
     * This method tests the setQuestion method of the Alternative class. It verifies that the
     * method sets the correct question.
     */
    @Test
    void testSetQuestion() {
      MultipleChoiceQuestion question = new MultipleChoiceQuestion();
      question.setId(1L);
      alternative.setQuestion(question);
      assertEquals(question, alternative.getQuestion());
    }
  }

  /**
   * This method tests the equals method of the Alternative class. It verifies that the method
   * returns the correct boolean value.
   */
  @Test
  void equalAlternativesGenerateTheSameHashcode() {
    Alternative alternative1 = new Alternative();
    alternative1.setId(1L);
    alternative1.setAlternativeText("Alternative Text");
    alternative1.setCorrect(true);
    MultipleChoiceQuestion question1 = new MultipleChoiceQuestion();
    question1.setId(1L);
    alternative1.setQuestion(question1);

    Alternative alternative2 = new Alternative();
    alternative2.setId(1L);
    alternative2.setAlternativeText("Alternative Text");
    alternative2.setCorrect(true);
    MultipleChoiceQuestion question2 = new MultipleChoiceQuestion();
    question2.setId(1L);
    alternative2.setQuestion(question2);

    assertEquals(alternative1, alternative2);
    assertEquals(alternative1.hashCode(), alternative2.hashCode());
  }
}
