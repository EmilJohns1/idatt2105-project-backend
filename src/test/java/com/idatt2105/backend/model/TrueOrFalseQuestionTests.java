package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TrueOrFalseQuestionTests {

  private TrueOrFalseQuestion trueOrFalseQuestion;

  @BeforeEach
  void setUp() {
    trueOrFalseQuestion = new TrueOrFalseQuestion();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetId() {
      Long id = 1L;
      trueOrFalseQuestion.setId(id);
      assertEquals(id, trueOrFalseQuestion.getId());
    }

    @Test
    void testGetCorrectAnswer() {
      Boolean correctAnswer = true;
      trueOrFalseQuestion.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, trueOrFalseQuestion.getCorrectAnswer());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetId() {
      Long id = 1L;
      trueOrFalseQuestion.setId(id);
      assertEquals(id, trueOrFalseQuestion.getId());
    }

    @Test
    void testSetCorrectAnswer() {
      Boolean correctAnswer = true;
      trueOrFalseQuestion.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, trueOrFalseQuestion.getCorrectAnswer());
    }
  }

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