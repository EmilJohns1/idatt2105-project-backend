package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlternativeTests {

  private Alternative alternative;

  @BeforeEach
  void setUp() {
    alternative = new Alternative();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetId() {
      Long id = 1L;
      alternative.setId(id);
      assertEquals(id, alternative.getId());
    }

    @Test
    void testGetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternative.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternative.getAlternativeText());
    }

    @Test
    void testIsCorrect() {
      boolean isCorrect = true;
      alternative.setCorrect(isCorrect);
      assertTrue(alternative.isCorrect());
    }

    @Test
    void testGetQuestion() {
      MultipleChoiceQuestion question = new MultipleChoiceQuestion();
      question.setId(1L);
      alternative.setQuestion(question);
      assertEquals(question, alternative.getQuestion());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetId() {
      Long id = 1L;
      alternative.setId(id);
      assertEquals(id, alternative.getId());
    }

    @Test
    void testSetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternative.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternative.getAlternativeText());
    }

    @Test
    void testSetCorrect() {
      boolean isCorrect = true;
      alternative.setCorrect(isCorrect);
      assertTrue(alternative.isCorrect());
    }

    @Test
    void testSetQuestion() {
      MultipleChoiceQuestion question = new MultipleChoiceQuestion();
      question.setId(1L);
      alternative.setQuestion(question);
      assertEquals(question, alternative.getQuestion());
    }
  }

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
