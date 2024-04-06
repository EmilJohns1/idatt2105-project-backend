package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The TrueOrFalseQuestionAttemptTests class is a test class that tests the
 * TrueOrFalseQuestionAttempt class.
 */
class TrueOrFalseQuestionAttemptTests {

  private TrueOrFalseQuestionAttempt trueOrFalseQuestionAttempt;

  @BeforeEach
  void setUp() {
    trueOrFalseQuestionAttempt = new TrueOrFalseQuestionAttempt();
  }

  /**
   * The GetterTests class is a test class that tests the getters of the TrueOrFalseQuestionAttempt
   * class.
   */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the TrueOrFalseQuestionAttempt class. It verifies that
     * the method returns the correct id.
     */
    @Test
    void testGetId() {
      Long id = 1L;
      trueOrFalseQuestionAttempt.setId(id);
      assertEquals(id, trueOrFalseQuestionAttempt.getId());
    }

    /**
     * This method tests the getUserAnswer method of the TrueOrFalseQuestionAttempt class. It
     * verifies that the method returns the correct user answer.
     */
    @Test
    void testGetUserAnswer() {
      Boolean userAnswer = true;
      trueOrFalseQuestionAttempt.setUserAnswer(userAnswer);
      assertEquals(userAnswer, trueOrFalseQuestionAttempt.getUserAnswer());
    }

    /**
     * This method tests the getCorrectAnswer method of the TrueOrFalseQuestionAttempt class. It
     * verifies that the method returns the correct answer.
     */
    @Test
    void testGetCorrectAnswer() {
      Boolean correctAnswer = false;
      trueOrFalseQuestionAttempt.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, trueOrFalseQuestionAttempt.getCorrectAnswer());
    }
  }

  /**
   * The SetterTests class is a test class that tests the setters of the TrueOrFalseQuestionAttempt
   * class.
   */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the TrueOrFalseQuestionAttempt class. It verifies that
     * the method sets the correct id.
     */
    @Test
    void testSetId() {
      Long id = 1L;
      trueOrFalseQuestionAttempt.setId(id);
      assertEquals(id, trueOrFalseQuestionAttempt.getId());
    }

    /**
     * This method tests the setUserAnswer method of the TrueOrFalseQuestionAttempt class. It
     * verifies that the method sets the correct user answer.
     */
    @Test
    void testSetUserAnswer() {
      Boolean userAnswer = true;
      trueOrFalseQuestionAttempt.setUserAnswer(userAnswer);
      assertEquals(userAnswer, trueOrFalseQuestionAttempt.getUserAnswer());
    }

    /**
     * This method tests the setCorrectAnswer method of the TrueOrFalseQuestionAttempt class. It
     * verifies that the method sets the correct answer.
     */
    @Test
    void testSetCorrectAnswer() {
      Boolean correctAnswer = false;
      trueOrFalseQuestionAttempt.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, trueOrFalseQuestionAttempt.getCorrectAnswer());
    }
  }

  /**
   * This method tests the equals and hashCode methods of the TrueOrFalseQuestionAttempt class. It
   * verifies that two objects are equal if they have the same id, user answer and correct answer.
   */
  @Test
  void testEqualsAndHashCode() {
    TrueOrFalseQuestionAttempt attempt1 = new TrueOrFalseQuestionAttempt();
    attempt1.setId(1L);
    attempt1.setUserAnswer(true);
    attempt1.setCorrectAnswer(false);

    TrueOrFalseQuestionAttempt attempt2 = new TrueOrFalseQuestionAttempt();
    attempt2.setId(1L);
    attempt2.setUserAnswer(true);
    attempt2.setCorrectAnswer(false);

    assertEquals(attempt1, attempt2);
    assertEquals(attempt1.hashCode(), attempt2.hashCode());

    TrueOrFalseQuestionAttempt attempt3 = new TrueOrFalseQuestionAttempt();
    attempt3.setId(2L);
    attempt3.setUserAnswer(false);
    attempt3.setCorrectAnswer(true);

    assertNotEquals(attempt1, attempt3);
    assertNotEquals(attempt1.hashCode(), attempt3.hashCode());
  }
}
