package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/** The TrueOrFalseQuestionTests class is a test class that tests the TrueOrFalseQuestion class. */
class TrueOrFalseQuestionTests {

  private TrueOrFalseQuestion trueOrFalseQuestion;

  @BeforeEach
  void setUp() {
    trueOrFalseQuestion = new TrueOrFalseQuestion();
  }

  /**
   * The GetterTests class is a test class that tests the getters of the TrueOrFalseQuestion class.
   */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the TrueOrFalseQuestion class. It verifies that the
     * method returns the correct id.
     */
    @Test
    void testGetId() {
      Long id = 1L;
      trueOrFalseQuestion.setId(id);
      assertEquals(id, trueOrFalseQuestion.getId());
    }

    /**
     * This method tests the getCorrectAnswer method of the TrueOrFalseQuestion class. It verifies
     * that the method returns the correct answer.
     */
    @Test
    void testGetCorrectAnswer() {
      Boolean correctAnswer = true;
      trueOrFalseQuestion.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, trueOrFalseQuestion.getCorrectAnswer());
    }
  }

  /**
   * The SetterTests class is a test class that tests the setters of the TrueOrFalseQuestion class.
   */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the TrueOrFalseQuestion class. It verifies that the
     * method sets the correct id.
     */
    @Test
    void testSetId() {
      Long id = 1L;
      trueOrFalseQuestion.setId(id);
      assertEquals(id, trueOrFalseQuestion.getId());
    }

    /**
     * This method tests the setCorrectAnswer method of the TrueOrFalseQuestion class. It verifies
     * that the method sets the correct answer.
     */
    @Test
    void testSetCorrectAnswer() {
      Boolean correctAnswer = true;
      trueOrFalseQuestion.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, trueOrFalseQuestion.getCorrectAnswer());
    }
  }

  /**
   * This method tests the equals method of the TrueOrFalseQuestion class. It verifies that two
   * TrueOrFalseQuestion objects are equal if they have the same id and correct answer.
   */
  @Test
  void testEqualsAndHashCode() {
    TrueOrFalseQuestion question1 = new TrueOrFalseQuestion();
    question1.setId(1L);
    question1.setCorrectAnswer(true);

    TrueOrFalseQuestion question2 = new TrueOrFalseQuestion();
    question2.setId(1L);
    question2.setCorrectAnswer(true);

    assertEquals(question1, question2);
    assertEquals(question1.hashCode(), question2.hashCode());

    TrueOrFalseQuestion question3 = new TrueOrFalseQuestion();
    question3.setId(2L);
    question3.setCorrectAnswer(false);

    assertNotEquals(question1, question3);
    assertNotEquals(question1.hashCode(), question3.hashCode());
  }
}
