package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlternativeRecordTests {

  private AlternativeRecord alternativeRecord;

  @BeforeEach
  void setUp() {
    alternativeRecord = new AlternativeRecord();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetId() {
      Long id = 1L;
      alternativeRecord.setId(id);
      assertEquals(id, alternativeRecord.getId());
    }

    @Test
    void testGetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternativeRecord.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternativeRecord.getAlternativeText());
    }

    @Test
    void testWasCorrect() {
      boolean wasCorrect = true;
      alternativeRecord.wasCorrect(wasCorrect);
      assertTrue(alternativeRecord.wasCorrect());
    }

    @Test
    void testWasSelected() {
      boolean wasSelected = true;
      alternativeRecord.wasSelected(wasSelected);
      assertTrue(alternativeRecord.wasSelected());
    }

    @Test
    void testGetQuestionAttempt() {
      MultipleChoiceQuestionAttempt questionAttempt = new MultipleChoiceQuestionAttempt();
      questionAttempt.setId(1L);
      alternativeRecord.setQuestionAttempt(questionAttempt);
      assertEquals(questionAttempt, alternativeRecord.getQuestionAttempt());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetId() {
      Long id = 1L;
      alternativeRecord.setId(id);
      assertEquals(id, alternativeRecord.getId());
    }

    @Test
    void testSetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternativeRecord.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternativeRecord.getAlternativeText());
    }

    @Test
    void testSetWasCorrect() {
      boolean wasCorrect = true;
      alternativeRecord.wasCorrect(wasCorrect);
      assertTrue(alternativeRecord.wasCorrect());
    }

    @Test
    void testSetWasSelected() {
      boolean wasSelected = true;
      alternativeRecord.wasSelected(wasSelected);
      assertTrue(alternativeRecord.wasSelected());
    }

    @Test
    void testSetQuestionAttempt() {
      MultipleChoiceQuestionAttempt questionAttempt = new MultipleChoiceQuestionAttempt();
      questionAttempt.setId(1L);
      alternativeRecord.setQuestionAttempt(questionAttempt);
      assertEquals(questionAttempt, alternativeRecord.getQuestionAttempt());
    }
  }

  @Test
  void equalAlternativeRecordsGenerateTheSameHashcode() {
    AlternativeRecord alternativeRecord1 = new AlternativeRecord();
    alternativeRecord1.setId(1L);
    alternativeRecord1.setAlternativeText("Alternative Text");
    alternativeRecord1.wasCorrect(true);
    alternativeRecord1.wasSelected(true);
    MultipleChoiceQuestionAttempt questionAttempt1 = new MultipleChoiceQuestionAttempt();
    questionAttempt1.setId(1L);
    alternativeRecord1.setQuestionAttempt(questionAttempt1);

    AlternativeRecord alternativeRecord2 = new AlternativeRecord();
    alternativeRecord2.setId(1L);
    alternativeRecord2.setAlternativeText("Alternative Text");
    alternativeRecord2.wasCorrect(true);
    alternativeRecord2.wasSelected(true);
    MultipleChoiceQuestionAttempt questionAttempt2 = new MultipleChoiceQuestionAttempt();
    questionAttempt2.setId(1L);
    alternativeRecord2.setQuestionAttempt(questionAttempt2);

    assertEquals(alternativeRecord1, alternativeRecord2);
    assertEquals(alternativeRecord1.hashCode(), alternativeRecord2.hashCode());
  }
}