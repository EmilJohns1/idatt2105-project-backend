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

class MultipleChoiceQuestionAttemptTests {

  private MultipleChoiceQuestionAttempt multipleChoiceQuestionAttempt;

  @BeforeEach
  void setUp() {
    multipleChoiceQuestionAttempt = new MultipleChoiceQuestionAttempt();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetId() {
      Long id = 1L;
      multipleChoiceQuestionAttempt.setId(id);
      assertEquals(id, multipleChoiceQuestionAttempt.getId());
    }

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

  @Nested
  class SetterTests {
    @Test
    void testSetId() {
      Long id = 1L;
      multipleChoiceQuestionAttempt.setId(id);
      assertEquals(id, multipleChoiceQuestionAttempt.getId());
    }

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

  @Test
  void testAddAlternative() {
    AlternativeRecord alternativeRecord = new AlternativeRecord();
    alternativeRecord.setId(1L);
    multipleChoiceQuestionAttempt.addAlternative(alternativeRecord);
    assertTrue(multipleChoiceQuestionAttempt.getAlternatives().contains(alternativeRecord));
  }

  @Test
  void addAlternativeThrowsExceptionWhenGivenNullAsParameter() {
    assertThrows(IllegalArgumentException.class, () -> multipleChoiceQuestionAttempt.addAlternative(null));
  }

  @Test
  void equalObjectsCreateTheSameHashcode() {
    MultipleChoiceQuestionAttempt attempt1 = new MultipleChoiceQuestionAttempt();
    MultipleChoiceQuestionAttempt attempt2 = new MultipleChoiceQuestionAttempt();
    List<MultipleChoiceQuestionAttempt> both = List.of(attempt1, attempt2);
    both.forEach(attempt -> {
      AlternativeRecord record = new AlternativeRecord();
      record.setId(1L);
      attempt.addAlternative(record);
    });
    assertEquals(attempt1, attempt2);
    assertEquals(attempt1.hashCode(), attempt2.hashCode());
  }
}