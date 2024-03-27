package com.idatt2105.backend.model;

import com.idatt2105.backend.dto.AlternativeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MultipleChoiceQuestionTests {

  private MultipleChoiceQuestion multipleChoiceQuestion;

  @BeforeEach
  void setUp() {
    multipleChoiceQuestion = new MultipleChoiceQuestion();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetId() {
      Long id = 1L;
      multipleChoiceQuestion.setId(id);
      assertEquals(id, multipleChoiceQuestion.getId());
    }

    @Test
    void testGetAlternatives() {
      Set<Alternative> alternatives = new HashSet<>();
      Alternative alternative = new Alternative();
      alternative.setAlternativeText("Alternative text");
      alternatives.add(alternative);
      multipleChoiceQuestion.setAlternatives(alternatives);
      assertEquals(alternatives, multipleChoiceQuestion.getAlternatives());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetId() {
      Long id = 1L;
      multipleChoiceQuestion.setId(id);
      assertEquals(id, multipleChoiceQuestion.getId());
    }

    @Test
    void testSetAlternatives() {
      Set<Alternative> alternatives = new HashSet<>();
      Alternative alternative = new Alternative();
      alternative.setId(1L);
      alternatives.add(alternative);
      multipleChoiceQuestion.setAlternatives(alternatives);
      assertEquals(alternatives, multipleChoiceQuestion.getAlternatives());
    }

    @Test
    void addAlternativeThrowsExceptionWhenParameterIsNull() {
      assertThrows(IllegalArgumentException.class, () -> multipleChoiceQuestion.addAlternative(null));
    }
  }

  @Test
  void testAddAlternative() {
    AlternativeDTO alternative = new AlternativeDTO();
    alternative.setAlternativeText("Alternative text");
    multipleChoiceQuestion.addAlternative(alternative);
    assertEquals("Alternative text", multipleChoiceQuestion.getAlternatives().iterator().next().getAlternativeText());
  }
}