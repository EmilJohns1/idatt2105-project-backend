package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The AlternativeRecordTests class is a test class that tests the AlternativeRecord class. */
class AlternativeRecordTests {

  private AlternativeRecord alternativeRecord;

  @BeforeEach
  void setUp() {
    alternativeRecord = new AlternativeRecord();
  }

  /**
   * The GetterTests class is a test class that tests the getters of the AlternativeRecord class.
   */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the AlternativeRecord class. It verifies that the
     * method returns the correct id.
     */
    @Test
    void testGetId() {
      Long id = 1L;
      alternativeRecord.setId(id);
      assertEquals(id, alternativeRecord.getId());
    }

    /**
     * This method tests the getAlternativeText method of the AlternativeRecord class. It verifies
     * that the method returns the correct alternative text.
     */
    @Test
    void testGetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternativeRecord.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternativeRecord.getAlternativeText());
    }

    /**
     * This method tests the wasCorrect method of the AlternativeRecord class. It verifies that the
     * method returns the correct boolean value.
     */
    @Test
    void testWasCorrect() {
      boolean wasCorrect = true;
      alternativeRecord.wasCorrect(wasCorrect);
      assertTrue(alternativeRecord.wasCorrect());
    }

    /**
     * This method tests the wasSelected method of the AlternativeRecord class. It verifies that the
     * method returns the correct boolean value.
     */
    @Test
    void testWasSelected() {
      boolean wasSelected = true;
      alternativeRecord.wasSelected(wasSelected);
      assertTrue(alternativeRecord.wasSelected());
    }

    /**
     * This method tests the getQuestionAttempt method of the AlternativeRecord class. It verifies
     * that the method returns the correct question attempt.
     */
    @Test
    void testGetQuestionAttempt() {
      MultipleChoiceQuestionAttempt questionAttempt = new MultipleChoiceQuestionAttempt();
      questionAttempt.setId(1L);
      alternativeRecord.setQuestionAttempt(questionAttempt);
      assertEquals(questionAttempt, alternativeRecord.getQuestionAttempt());
    }
  }

  /**
   * The SetterTests class is a test class that tests the setters of the AlternativeRecord class.
   */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the AlternativeRecord class. It verifies that the
     * method sets the correct id.
     */
    @Test
    void testSetId() {
      Long id = 1L;
      alternativeRecord.setId(id);
      assertEquals(id, alternativeRecord.getId());
    }

    /**
     * This method tests the setAlternativeText method of the AlternativeRecord class. It verifies
     * that the method sets the correct alternative text.
     */
    @Test
    void testSetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternativeRecord.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternativeRecord.getAlternativeText());
    }

    /**
     * This method tests the wasCorrect method of the AlternativeRecord class. It verifies that the
     * method sets the correct boolean value.
     */
    @Test
    void testSetWasCorrect() {
      boolean wasCorrect = true;
      alternativeRecord.wasCorrect(wasCorrect);
      assertTrue(alternativeRecord.wasCorrect());
    }

    /**
     * This method tests the wasSelected method of the AlternativeRecord class. It verifies that the
     * method sets the correct boolean value.
     */
    @Test
    void testSetWasSelected() {
      boolean wasSelected = true;
      alternativeRecord.wasSelected(wasSelected);
      assertTrue(alternativeRecord.wasSelected());
    }

    /**
     * This method tests the setQuestionAttempt method of the AlternativeRecord class. It verifies
     * that the method sets the correct question attempt.
     */
    @Test
    void testSetQuestionAttempt() {
      MultipleChoiceQuestionAttempt questionAttempt = new MultipleChoiceQuestionAttempt();
      questionAttempt.setId(1L);
      alternativeRecord.setQuestionAttempt(questionAttempt);
      assertEquals(questionAttempt, alternativeRecord.getQuestionAttempt());
    }
  }

  /**
   * This method tests the equals method of the AlternativeRecord class. It verifies that the method
   * returns the correct boolean value.
   */
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
