package com.idatt2105.backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizUpdateRequestDTOTests {

  private QuizUpdateRequestDTO quizUpdateRequestDTO;

  @BeforeEach
  void setUp() {
    quizUpdateRequestDTO = new QuizUpdateRequestDTO();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetTitle() {
      String title = "Quiz Title";
      quizUpdateRequestDTO.setTitle(title);
      assertEquals(title, quizUpdateRequestDTO.getTitle());
    }

    @Test
    void testGetDescription() {
      String description = "Quiz Description";
      quizUpdateRequestDTO.setDescription(description);
      assertEquals(description, quizUpdateRequestDTO.getDescription());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetTitle() {
      String title = "Quiz Title";
      quizUpdateRequestDTO.setTitle(title);
      assertEquals(title, quizUpdateRequestDTO.getTitle());
    }

    @Test
    void testSetDescription() {
      String description = "Quiz Description";
      quizUpdateRequestDTO.setDescription(description);
      assertEquals(description, quizUpdateRequestDTO.getDescription());
    }
  }

  @Test
  void constructorTest() {
    String title = "Quiz Title";
    String description = "Quiz Description";
    QuizUpdateRequestDTO quizUpdateRequestDTO = new QuizUpdateRequestDTO(title, description);
    assertEquals(title, quizUpdateRequestDTO.getTitle());
    assertEquals(description, quizUpdateRequestDTO.getDescription());
  }
}