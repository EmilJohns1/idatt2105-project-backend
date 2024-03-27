package com.idatt2105.backend.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AlternativeDTOTests {
  private AlternativeDTO alternativeDTO;

  @BeforeEach
  void setUp() {
    alternativeDTO = new AlternativeDTO();
  }

  @Nested
  class Getters {
    @Test
    void testGetId() {
      Long id = 1L;
      alternativeDTO.setId(id);
      assertEquals(id, alternativeDTO.getId());
    }

    @Test
    void testGetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternativeDTO.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternativeDTO.getAlternativeText());
    }

    @Test
    void testIsCorrect() {
      boolean isCorrect = true;
      alternativeDTO.setCorrect(isCorrect);
      assertEquals(isCorrect, alternativeDTO.isCorrect());
    }

    @Test
    void testGetQuestionId() {
      Long questionId = 1L;
      alternativeDTO.setQuestionId(questionId);
      assertEquals(questionId, alternativeDTO.getQuestionId());
    }
  }

  @Nested
  class Setters {
    @Test
    void testSetId() {
      Long id = 1L;
      alternativeDTO.setId(id);
      assertEquals(id, alternativeDTO.getId());
    }

    @Test
    void testSetAlternativeText() {
      String alternativeText = "Alternative Text";
      alternativeDTO.setAlternativeText(alternativeText);
      assertEquals(alternativeText, alternativeDTO.getAlternativeText());
    }

    @Test
    void testSetCorrect() {
      boolean isCorrect = true;
      alternativeDTO.setCorrect(isCorrect);
      assertEquals(isCorrect, alternativeDTO.isCorrect());
    }

    @Test
    void testSetQuestionId() {
      Long questionId = 1L;
      alternativeDTO.setQuestionId(questionId);
      assertEquals(questionId, alternativeDTO.getQuestionId());
    }
  }
}
