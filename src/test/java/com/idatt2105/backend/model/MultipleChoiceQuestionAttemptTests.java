package com.idatt2105.backend.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The MultipleChoiceQuestionAttemptTests class is a test class that tests the
 * MultipleChoiceQuestionAttempt class.
 */
class MultipleChoiceQuestionAttemptTests {

  private MultipleChoiceQuestionAttempt multipleChoiceQuestionAttempt;

  @BeforeEach
  void setUp() {
    multipleChoiceQuestionAttempt = new MultipleChoiceQuestionAttempt();
  }

  /** The GetterTests class is a test class that tests the getters of the */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the MultipleChoiceQuestionAttempt class. It verifies
     * that the method returns the correct id.
     */
    @Test
    void testGetId() {
      Long id = 1L;
      multipleChoiceQuestionAttempt.setId(id);
      assertEquals(id, multipleChoiceQuestionAttempt.getId());
    }

    /**
     * This method tests the getAlternatives method of the MultipleChoiceQuestionAttempt class. It
     * verifies that the method returns the correct alternatives.
     */
    @Test
    void testGetAlternatives() {
      Set<AlternativeRecord> alternatives = new HashSet<>();
      AlternativeRecord alternativeRecord = new AlternativeRecord();
      alternativeRecord.setId(1L);
      alternatives.add(alternativeRecord);
      multipleChoiceQuestionAttempt.setAlternatives(alternatives);
      assertEquals(alternatives, multipleChoiceQuestionAttempt.getAlternatives());
    }
  }

  /**
   * The SetterTests class is a test class that tests the setters of the
   * MultipleChoiceQuestionAttempt class.
   */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the MultipleChoiceQuestionAttempt class. It verifies
     * that the method sets the correct id.
     */
    @Test
    void testSetId() {
      Long id = 1L;
      multipleChoiceQuestionAttempt.setId(id);
      assertEquals(id, multipleChoiceQuestionAttempt.getId());
    }

    /**
     * This method tests the setAlternatives method of the MultipleChoiceQuestionAttempt class. It
     * verifies that the method sets the correct alternatives.
     */
    @Test
    void testSetAlternatives() {
      Set<AlternativeRecord> alternatives = new HashSet<>();
      AlternativeRecord alternativeRecord = new AlternativeRecord();
      alternativeRecord.setId(1L);
      alternatives.add(alternativeRecord);
      multipleChoiceQuestionAttempt.setAlternatives(alternatives);
      assertEquals(alternatives, multipleChoiceQuestionAttempt.getAlternatives());
    }
  }

  /**
   * This method tests the addAlternative method of the MultipleChoiceQuestionAttempt class. It
   * verifies that the method adds the correct alternative.
   */
  @Test
  void testAddAlternative() {
    AlternativeRecord alternativeRecord = new AlternativeRecord();
    alternativeRecord.setId(1L);
    multipleChoiceQuestionAttempt.addAlternative(alternativeRecord);
    assertTrue(multipleChoiceQuestionAttempt.getAlternatives().contains(alternativeRecord));
  }

  /**
   * This method tests the addAlternative method of the MultipleChoiceQuestionAttempt class. It
   * verifies that the method throws an IllegalArgumentException when given null as parameter.
   *
   * @throws IllegalArgumentException
   */
  @Test
  void addAlternativeThrowsExceptionWhenGivenNullAsParameter() {
    assertThrows(
        IllegalArgumentException.class, () -> multipleChoiceQuestionAttempt.addAlternative(null));
  }

  /**
   * This method tests the equals method of the MultipleChoiceQuestionAttempt class. It verifies
   * that the method returns true when the objects are equal.
   */
  @Test
  void equalObjectsCreateTheSameHashcode() {
    MultipleChoiceQuestionAttempt attempt1 = new MultipleChoiceQuestionAttempt();
    MultipleChoiceQuestionAttempt attempt2 = new MultipleChoiceQuestionAttempt();
    List<MultipleChoiceQuestionAttempt> both = List.of(attempt1, attempt2);
    both.forEach(
        attempt -> {
          AlternativeRecord record = new AlternativeRecord();
          record.setId(1L);
          attempt.addAlternative(record);
        });
    assertEquals(attempt1, attempt2);
    assertEquals(attempt1.hashCode(), attempt2.hashCode());
  }
}
