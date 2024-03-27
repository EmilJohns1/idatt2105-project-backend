package com.idatt2105.backend.dto;

import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizAttemptDTOTests {
  private Validator validator;

  private QuizAttemptDTO quizAttemptDTO;

  @BeforeEach
  void setUp() {
    quizAttemptDTO = new QuizAttemptDTO();
    validator = buildDefaultValidatorFactory().getValidator();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetAttemptTime() {
      LocalDateTime attemptTime = LocalDateTime.now();
      quizAttemptDTO.setAttemptTime(attemptTime);
      assertEquals(attemptTime, quizAttemptDTO.getAttemptTime());
    }

    @Test
    void testGetScore() {
      int score = 85;
      quizAttemptDTO.setScore(score);
      assertEquals(score, quizAttemptDTO.getScore());
    }

    @Test
    void testGetUserId() {
      Long userId = 1L;
      quizAttemptDTO.setUserId(userId);
      assertEquals(userId, quizAttemptDTO.getUserId());
    }

    @Test
    void testGetQuizId() {
      Long quizId = 1L;
      quizAttemptDTO.setQuizId(quizId);
      assertEquals(quizId, quizAttemptDTO.getQuizId());
    }

    @Test
    void testGetQuestionAttempts() {
      Set<QuestionAttemptDTO> questionAttempts = new HashSet<>();
      QuestionAttemptDTO questionAttemptDTO = new QuestionAttemptDTO();
      questionAttempts.add(questionAttemptDTO);
      quizAttemptDTO.setQuestionAttempts(questionAttempts);
      assertEquals(questionAttempts, quizAttemptDTO.getQuestionAttempts());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetAttemptTime() {
      LocalDateTime attemptTime = LocalDateTime.now();
      quizAttemptDTO.setAttemptTime(attemptTime);
      assertEquals(attemptTime, quizAttemptDTO.getAttemptTime());
    }

    @Test
    void testSetScore() {
      int score = 85;
      quizAttemptDTO.setScore(score);
      assertEquals(score, quizAttemptDTO.getScore());
    }

    @Test
    void testSetUserId() {
      Long userId = 1L;
      quizAttemptDTO.setUserId(userId);
      assertEquals(userId, quizAttemptDTO.getUserId());
    }

    @Test
    void testSetQuizId() {
      Long quizId = 1L;
      quizAttemptDTO.setQuizId(quizId);
      assertEquals(quizId, quizAttemptDTO.getQuizId());
    }

    @Test
    void testSetQuestionAttempts() {
      Set<QuestionAttemptDTO> questionAttempts = new HashSet<>();
      QuestionAttemptDTO questionAttemptDTO = new QuestionAttemptDTO();
      questionAttempts.add(questionAttemptDTO);
      quizAttemptDTO.setQuestionAttempts(questionAttempts);
      assertEquals(questionAttempts, quizAttemptDTO.getQuestionAttempts());
    }
  }

  @Test
  void nullElementsAreNotAllowedInQuestionAttempts() {
    quizAttemptDTO.getQuestionAttempts().add(null);
    assertEquals(1, validator.validate(quizAttemptDTO).size());
  }
}