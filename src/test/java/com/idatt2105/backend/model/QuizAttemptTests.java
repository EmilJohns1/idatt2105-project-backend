package com.idatt2105.backend.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizAttemptTests {
  private QuizAttempt quizAttempt;

  @BeforeEach
  void setup() {
    quizAttempt = new QuizAttempt();
  }

  @Nested
  class GetterTests {
    @Test
    void getIdReturnsCorrectId() {
      Long expectedId = 1L;
      quizAttempt.setId(expectedId);
      assertEquals(expectedId, quizAttempt.getId());
    }

    @Test
    void getScoreReturnsCorrectScore() {
      int expectedScore = 85;
      quizAttempt.setScore(expectedScore);
      assertEquals(expectedScore, quizAttempt.getScore());
    }

    @Test
    void getAttemptTimeReturnsCorrectAttemptTime() {
      LocalDateTime expectedAttemptTime = LocalDateTime.now();
      quizAttempt.setAttemptTime(expectedAttemptTime);
      assertEquals(expectedAttemptTime, quizAttempt.getAttemptTime());
    }

    @Test
    void getUserReturnsCorrectUser() {
      User expectedUser = new User();
      quizAttempt.setUser(expectedUser);
      assertEquals(expectedUser, quizAttempt.getUser());
    }

    @Test
    void getQuizIdReturnsCorrectQuizId() {
      Long expectedQuizId = 1L;
      quizAttempt.setQuizId(expectedQuizId);
      assertEquals(expectedQuizId, quizAttempt.getQuizId());
    }

    @Test
    void getQuestionAttemptsReturnsCorrectQuestionAttempts() {
      Set<QuestionAttempt> expectedQuestionAttempts = new HashSet<>();
      quizAttempt.setQuestionAttempts(expectedQuestionAttempts);
      assertEquals(expectedQuestionAttempts, quizAttempt.getQuestionAttempts());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void setIdSetsIdCorrectly() {
      Long expectedId = 1L;
      quizAttempt.setId(expectedId);
      assertEquals(expectedId, quizAttempt.getId());
    }

    @Test
    void setScoreSetsScoreCorrectly() {
      int expectedScore = 85;
      quizAttempt.setScore(expectedScore);
      assertEquals(expectedScore, quizAttempt.getScore());
    }

    @Test
    void setAttemptTimeSetsAttemptTimeCorrectly() {
      LocalDateTime expectedAttemptTime = LocalDateTime.now();
      quizAttempt.setAttemptTime(expectedAttemptTime);
      assertEquals(expectedAttemptTime, quizAttempt.getAttemptTime());
    }

    @Test
    void setUserSetsUserCorrectly() {
      User expectedUser = new User();
      quizAttempt.setUser(expectedUser);
      assertEquals(expectedUser, quizAttempt.getUser());
    }

    @Test
    void setQuizIdSetsQuizIdCorrectly() {
      Long expectedQuizId = 1L;
      quizAttempt.setQuizId(expectedQuizId);
      assertEquals(expectedQuizId, quizAttempt.getQuizId());
    }

    @Test
    void setQuestionAttemptsSetsQuestionAttemptsCorrectly() {
      Set<QuestionAttempt> expectedQuestionAttempts = new HashSet<>();
      quizAttempt.setQuestionAttempts(expectedQuestionAttempts);
      assertEquals(expectedQuestionAttempts, quizAttempt.getQuestionAttempts());
    }
  }
}
