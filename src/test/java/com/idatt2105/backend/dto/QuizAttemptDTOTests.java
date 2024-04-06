package com.idatt2105.backend.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validator;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** The QuizAttemptDTOTests class is a test class that tests the QuizAttemptDTO class. */
class QuizAttemptDTOTests {
  private Validator validator;

  private QuizAttemptDTO quizAttemptDTO;

  @BeforeEach
  void setUp() {
    quizAttemptDTO = new QuizAttemptDTO();
    validator = buildDefaultValidatorFactory().getValidator();
  }

  /** The GetterTests class is a test class that tests the getters of the QuizAttemptDTO class. */
  @Nested
  class GetterTests {
    /**
     * This method tests the getAttemptTime method of the QuizAttemptDTO class. It verifies that the
     * method returns the correct attempt time.
     */
    @Test
    void testGetAttemptTime() {
      LocalDateTime attemptTime = LocalDateTime.now();
      quizAttemptDTO.setAttemptTime(attemptTime);
      assertEquals(attemptTime, quizAttemptDTO.getAttemptTime());
    }

    /**
     * This method tests the getScore method of the QuizAttemptDTO class. It verifies that the
     * method returns the correct score.
     */
    @Test
    void testGetScore() {
      int score = 85;
      quizAttemptDTO.setScore(score);
      assertEquals(score, quizAttemptDTO.getScore());
    }

    /**
     * This method tests the getUserId method of the QuizAttemptDTO class. It verifies that the
     * method returns the correct user id.
     */
    @Test
    void testGetUserId() {
      Long userId = 1L;
      quizAttemptDTO.setUserId(userId);
      assertEquals(userId, quizAttemptDTO.getUserId());
    }

    /**
     * This method tests the getQuizId method of the QuizAttemptDTO class. It verifies that the
     * method returns the correct quiz id.
     */
    @Test
    void testGetQuizId() {
      Long quizId = 1L;
      quizAttemptDTO.setQuizId(quizId);
      assertEquals(quizId, quizAttemptDTO.getQuizId());
    }

    /**
     * This method tests the getQuestionAttempts method of the QuizAttemptDTO class. It verifies
     * that the method returns the correct question attempts.
     */
    @Test
    void testGetQuestionAttempts() {
      Set<QuestionAttemptDTO> questionAttempts = new HashSet<>();
      QuestionAttemptDTO questionAttemptDTO = new QuestionAttemptDTO();
      questionAttempts.add(questionAttemptDTO);
      quizAttemptDTO.setQuestionAttempts(questionAttempts);
      assertEquals(questionAttempts, quizAttemptDTO.getQuestionAttempts());
    }
  }

  /** The SetterTests class is a test class that tests the setters of the QuizAttemptDTO class. */
  @Nested
  class SetterTests {
    /**
     * This method tests the setAttemptTime method of the QuizAttemptDTO class. It verifies that the
     * method sets the correct attempt time.
     */
    @Test
    void testSetAttemptTime() {
      LocalDateTime attemptTime = LocalDateTime.now();
      quizAttemptDTO.setAttemptTime(attemptTime);
      assertEquals(attemptTime, quizAttemptDTO.getAttemptTime());
    }

    /**
     * This method tests the setScore method of the QuizAttemptDTO class. It verifies that the
     * method sets the correct score.
     */
    @Test
    void testSetScore() {
      int score = 85;
      quizAttemptDTO.setScore(score);
      assertEquals(score, quizAttemptDTO.getScore());
    }

    /**
     * This method tests the setUserId method of the QuizAttemptDTO class. It verifies that the
     * method sets the correct user id.
     */
    @Test
    void testSetUserId() {
      Long userId = 1L;
      quizAttemptDTO.setUserId(userId);
      assertEquals(userId, quizAttemptDTO.getUserId());
    }

    /**
     * This method tests the setQuizId method of the QuizAttemptDTO class. It verifies that the
     * method sets the correct quiz id.
     */
    @Test
    void testSetQuizId() {
      Long quizId = 1L;
      quizAttemptDTO.setQuizId(quizId);
      assertEquals(quizId, quizAttemptDTO.getQuizId());
    }

    /**
     * This method tests the setQuestionAttempts method of the QuizAttemptDTO class. It verifies
     * that the method sets the correct question attempts.
     */
    @Test
    void testSetQuestionAttempts() {
      Set<QuestionAttemptDTO> questionAttempts = new HashSet<>();
      QuestionAttemptDTO questionAttemptDTO = new QuestionAttemptDTO();
      questionAttempts.add(questionAttemptDTO);
      quizAttemptDTO.setQuestionAttempts(questionAttempts);
      assertEquals(questionAttempts, quizAttemptDTO.getQuestionAttempts());
    }
  }

  /**
   * This method tests if the validator allows null elements in the question attempts. It verifies
   * that the method returns the correct boolean value.
   */
  @Test
  void nullElementsAreNotAllowedInQuestionAttempts() {
    quizAttemptDTO.getQuestionAttempts().add(null);
    assertEquals(1, validator.validate(quizAttemptDTO).size());
  }
}
