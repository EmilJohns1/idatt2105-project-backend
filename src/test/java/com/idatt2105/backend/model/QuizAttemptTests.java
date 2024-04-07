package com.idatt2105.backend.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The QuizAttemptTests class is a test class that tests the QuizAttempt class. */
class QuizAttemptTests {
  private QuizAttempt quizAttempt;

  @BeforeEach
  void setup() {
    quizAttempt = new QuizAttempt();
  }

  /** The GetterTests class is a test class that tests the getters of the QuizAttempt class. */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the QuizAttempt class. It verifies that the method
     * returns the correct id.
     */
    @Test
    void getIdReturnsCorrectId() {
      Long expectedId = 1L;
      quizAttempt.setId(expectedId);
      assertEquals(expectedId, quizAttempt.getId());
    }

    /**
     * This method tests the getScore method of the QuizAttempt class. It verifies that the method
     * returns the correct score.
     */
    @Test
    void getScoreReturnsCorrectScore() {
      int expectedScore = 85;
      quizAttempt.setScore(expectedScore);
      assertEquals(expectedScore, quizAttempt.getScore());
    }

    /**
     * This method tests the getAttemptTime method of the QuizAttempt class. It verifies that the
     * method returns the correct attempt time.
     */
    @Test
    void getAttemptTimeReturnsCorrectAttemptTime() {
      LocalDateTime expectedAttemptTime = LocalDateTime.now();
      quizAttempt.setAttemptTime(expectedAttemptTime);
      assertEquals(expectedAttemptTime, quizAttempt.getAttemptTime());
    }

    /**
     * This method tests the getUser method of the QuizAttempt class. It verifies that the method
     * returns the correct user.
     */
    @Test
    void getUserReturnsCorrectUser() {
      User expectedUser = new User();
      quizAttempt.setUser(expectedUser);
      assertEquals(expectedUser, quizAttempt.getUser());
    }

    /**
     * This method tests the getQuizId method of the QuizAttempt class. It verifies that the method
     * returns the correct quiz id.
     */
    @Test
    void getQuizIdReturnsCorrectQuizId() {
      Long expectedQuizId = 1L;
      quizAttempt.setQuizId(expectedQuizId);
      assertEquals(expectedQuizId, quizAttempt.getQuizId());
    }

    /**
     * This method tests the getQuestionAttempts method of the QuizAttempt class. It verifies that
     * the method returns the correct question attempts.
     */
    @Test
    void getQuestionAttemptsReturnsCorrectQuestionAttempts() {
      Set<QuestionAttempt> expectedQuestionAttempts = new HashSet<>();
      quizAttempt.setQuestionAttempts(expectedQuestionAttempts);
      assertEquals(expectedQuestionAttempts, quizAttempt.getQuestionAttempts());
    }
  }

  /** The SetterTests class is a test class that tests the setters of the QuizAttempt class. */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the QuizAttempt class. It verifies that the method sets
     * the correct id.
     */
    @Test
    void setIdSetsIdCorrectly() {
      Long expectedId = 1L;
      quizAttempt.setId(expectedId);
      assertEquals(expectedId, quizAttempt.getId());
    }

    /**
     * This method tests the setScore method of the QuizAttempt class. It verifies that the method
     * sets the correct score.
     */
    @Test
    void setScoreSetsScoreCorrectly() {
      int expectedScore = 85;
      quizAttempt.setScore(expectedScore);
      assertEquals(expectedScore, quizAttempt.getScore());
    }

    /**
     * This method tests the setAttemptTime method of the QuizAttempt class. It verifies that the
     * method sets the correct attempt time.
     */
    @Test
    void setAttemptTimeSetsAttemptTimeCorrectly() {
      LocalDateTime expectedAttemptTime = LocalDateTime.now();
      quizAttempt.setAttemptTime(expectedAttemptTime);
      assertEquals(expectedAttemptTime, quizAttempt.getAttemptTime());
    }

    /**
     * This method tests the setUser method of the QuizAttempt class. It verifies that the method
     * sets the correct user.
     */
    @Test
    void setUserSetsUserCorrectly() {
      User expectedUser = new User();
      quizAttempt.setUser(expectedUser);
      assertEquals(expectedUser, quizAttempt.getUser());
    }

    /**
     * This method tests the setQuizId method of the QuizAttempt class. It verifies that the method
     * sets the correct quiz id.
     */
    @Test
    void setQuizIdSetsQuizIdCorrectly() {
      Long expectedQuizId = 1L;
      quizAttempt.setQuizId(expectedQuizId);
      assertEquals(expectedQuizId, quizAttempt.getQuizId());
    }

    /**
     * This method tests the setQuestionAttempts method of the QuizAttempt class. It verifies that
     * the method sets the correct question attempts.
     */
    @Test
    void setQuestionAttemptsSetsQuestionAttemptsCorrectly() {
      Set<QuestionAttempt> expectedQuestionAttempts = new HashSet<>();
      quizAttempt.setQuestionAttempts(expectedQuestionAttempts);
      assertEquals(expectedQuestionAttempts, quizAttempt.getQuestionAttempts());
    }
  }

  @Nested
  class TestEqualsAndHashCode {

    /**
     * This method tests the equals method of the QuizAttempt class. It verifies that the method
     * returns true when comparing two objects with the same values and false when comparing two
     * objects with different values.
     */
    @Test
    void testEqualsReturnsTrueWhenComparingObjectsWithSameValues() {
      QuizAttempt quizAttempt1 = new QuizAttempt();
      quizAttempt1.setId(1L);
      quizAttempt1.setScore(85);
      quizAttempt1.setUser(new User());
      quizAttempt1.setQuizId(1L);
      quizAttempt1.setQuestionAttempts(new HashSet<>());

      QuizAttempt quizAttempt2 = new QuizAttempt();
      quizAttempt2.setId(1L);
      quizAttempt2.setScore(85);
      quizAttempt2.setUser(new User());
      quizAttempt2.setQuizId(1L);
      quizAttempt2.setQuestionAttempts(new HashSet<>());

      assertEquals(quizAttempt1, quizAttempt2);
    }

    /**
     * This method tests the equals method of the QuizAttempt class. It verifies that the method
     * returns false when comparing two objects with different values.
     */
    @Test
    void testEqualsReturnsFalseWhenComparingObjectsWithDifferentValues() {
      QuizAttempt quizAttempt1 = new QuizAttempt();
      quizAttempt1.setId(1L);
      quizAttempt1.setScore(85);
      quizAttempt1.setAttemptTime(LocalDateTime.now());
      quizAttempt1.setUser(new User());
      quizAttempt1.setQuizId(1L);
      quizAttempt1.setQuestionAttempts(new HashSet<>());

      QuizAttempt quizAttempt2 = new QuizAttempt();
      quizAttempt2.setId(2L);
      quizAttempt2.setScore(85);
      quizAttempt2.setAttemptTime(LocalDateTime.now());
      quizAttempt2.setUser(new User());
      quizAttempt2.setQuizId(1L);

      assertEquals(false, quizAttempt1.equals(quizAttempt2));
      assertTrue(quizAttempt1.canEqual(quizAttempt2));
    }

    /**
     * This method tests the hashCode method of the QuizAttempt class. It verifies that the method
     * returns the same value for two objects with the same values and different values for two
     * objects with different values.
     */
    @Test
    void testHashCodeReturnsSameValueForObjectsWithSameValues() {
      QuizAttempt quizAttempt1 = new QuizAttempt();
      quizAttempt1.setId(1L);
      quizAttempt1.setScore(85);
      quizAttempt1.setUser(new User());
      quizAttempt1.setQuizId(1L);
      quizAttempt1.setQuestionAttempts(new HashSet<>());

      QuizAttempt quizAttempt2 = new QuizAttempt();
      quizAttempt2.setId(1L);
      quizAttempt2.setScore(85);
      quizAttempt2.setUser(new User());
      quizAttempt2.setQuizId(1L);
      quizAttempt2.setQuestionAttempts(new HashSet<>());

      assertEquals(quizAttempt1.hashCode(), quizAttempt2.hashCode());
    }
  }
}
