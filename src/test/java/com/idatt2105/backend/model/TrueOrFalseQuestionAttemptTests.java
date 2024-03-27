package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TrueOrFalseQuestionAttemptTests {

  private TrueOrFalseQuestionAttempt trueOrFalseQuestionAttempt;

  @BeforeEach
  void setUp() {
    trueOrFalseQuestionAttempt = new TrueOrFalseQuestionAttempt();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetId() {
      Long id = 1L;
      trueOrFalseQuestionAttempt.setId(id);
      assertEquals(id, trueOrFalseQuestionAttempt.getId());
    }

    @Test
    void testGetUserAnswer() {
      Boolean userAnswer = true;
      trueOrFalseQuestionAttempt.setUserAnswer(userAnswer);
      assertEquals(userAnswer, trueOrFalseQuestionAttempt.getUserAnswer());
    }

    @Test
    void testGetCorrectAnswer() {
      Boolean correctAnswer = false;
      trueOrFalseQuestionAttempt.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, trueOrFalseQuestionAttempt.getCorrectAnswer());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetId() {
      Long id = 1L;
      trueOrFalseQuestionAttempt.setId(id);
      assertEquals(id, trueOrFalseQuestionAttempt.getId());
    }

    @Test
    void testSetUserAnswer() {
      Boolean userAnswer = true;
      trueOrFalseQuestionAttempt.setUserAnswer(userAnswer);
      assertEquals(userAnswer, trueOrFalseQuestionAttempt.getUserAnswer());
    }

    @Test
    void testSetCorrectAnswer() {
      Boolean correctAnswer = false;
      trueOrFalseQuestionAttempt.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, trueOrFalseQuestionAttempt.getCorrectAnswer());
    }
  }

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
