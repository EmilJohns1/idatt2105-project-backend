package com.idatt2105.backend.model;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The QuestionAttemptTests class is a test class that tests the QuestionAttempt class. */
class QuestionAttemptTests {
  private QuestionAttempt questionAttempt;

  @BeforeEach
  public void setup() {
    questionAttempt = new QuestionAttempt();
  }

  /** The GetterTests class is a test class that tests the getters of the QuestionAttempt class. */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the QuestionAttempt class. It verifies that the method
     * returns the correct id.
     */
    @Test
    void getIdReturnsCorrectId() {
      Long expectedId = 1L;
      questionAttempt.setId(expectedId);
      assertEquals(expectedId, questionAttempt.getId());
    }

    /**
     * This method tests the getQuestionText method of the QuestionAttempt class. It verifies that
     * the method returns the correct question text.
     */
    @Test
    void getQuestionTextReturnsCorrectQuestionText() {
      String expectedQuestionText = "What is the capital of France?";
      questionAttempt.setQuestionText(expectedQuestionText);
      assertEquals(expectedQuestionText, questionAttempt.getQuestionText());
    }

    /**
     * This method tests the getMediaUrl method of the QuestionAttempt class. It verifies that the
     * method returns the correct media url.
     */
    @Test
    void getMediaUrlReturnsCorrectMediaUrl() {
      String expectedMediaUrl = "https://example.com/image.jpg";
      questionAttempt.setMediaUrl(expectedMediaUrl);
      assertEquals(expectedMediaUrl, questionAttempt.getMediaUrl());
    }

    /**
     * This method tests the getPoints method of the QuestionAttempt class. It verifies that the
     * method returns the correct points.
     */
    @Test
    void getPointsReturnsCorrectPoints() {
      int expectedPoints = 5;
      questionAttempt.setPoints(expectedPoints);
      assertEquals(expectedPoints, questionAttempt.getPoints());
    }

    /**
     * This method tests the getQuizAttempt method of the QuestionAttempt class. It verifies that
     * the method returns the correct quiz attempt.
     */
    @Test
    void getQuizAttemptReturnsCorrectQuizAttempt() {
      QuizAttempt expectedQuizAttempt = new QuizAttempt();
      questionAttempt.setQuizAttempt(expectedQuizAttempt);
      assertEquals(expectedQuizAttempt, questionAttempt.getQuizAttempt());
    }
  }

  /** The SetterTests class is a test class that tests the setters of the QuestionAttempt class. */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the QuestionAttempt class. It verifies that the method
     * sets the correct id.
     */
    @Test
    void setIdSetsIdCorrectly() {
      Long expectedId = 1L;
      questionAttempt.setId(expectedId);
      assertEquals(expectedId, questionAttempt.getId());
    }

    /**
     * This method tests the setQuestionText method of the QuestionAttempt class. It verifies that
     * the method sets the correct question text.
     */
    @Test
    void setQuestionTextSetsQuestionTextCorrectly() {
      String expectedQuestionText = "What is the capital of France?";
      questionAttempt.setQuestionText(expectedQuestionText);
      assertEquals(expectedQuestionText, questionAttempt.getQuestionText());
    }

    /**
     * This method tests the setMediaUrl method of the QuestionAttempt class. It verifies that the
     * method sets the correct media url.
     */
    @Test
    void setMediaUrlSetsMediaUrlCorrectly() {
      String expectedMediaUrl = "https://example.com/image.jpg";
      questionAttempt.setMediaUrl(expectedMediaUrl);
      assertEquals(expectedMediaUrl, questionAttempt.getMediaUrl());
    }

    /**
     * This method tests the setPoints method of the QuestionAttempt class. It verifies that the
     * method sets the correct points.
     */
    @Test
    void setPointsSetsPointsCorrectly() {
      int expectedPoints = 5;
      questionAttempt.setPoints(expectedPoints);
      assertEquals(expectedPoints, questionAttempt.getPoints());
    }

    /**
     * This method tests the setQuizAttempt method of the QuestionAttempt class. It verifies that
     * the method sets the correct quiz attempt.
     */
    @Test
    void setQuizAttemptSetsQuizAttemptCorrectly() {
      QuizAttempt expectedQuizAttempt = new QuizAttempt();
      questionAttempt.setQuizAttempt(expectedQuizAttempt);
      assertEquals(expectedQuizAttempt, questionAttempt.getQuizAttempt());
    }
  }

  /**
   * This method tests the equals method of the QuestionAttempt class. It verifies that the method
   * returns the correct boolean value.
   */
  @Test
  void equalObjectsHashToSameValue() {
    QuestionAttempt q1 = new QuestionAttempt();
    QuestionAttempt q2 = new QuestionAttempt();
    List<QuestionAttempt> both = List.of(q1, q2);
    for (QuestionAttempt q : both) {
      q.setId(1L);
      q.setQuestionText("Test question");
      q.setMediaUrl("Test media url");
      q.setPoints(1);
      QuizAttempt quiz = new QuizAttempt();
      quiz.setId(1L);
      q.setQuizAttempt(quiz);
    }
    assertEquals(q1, q2);
    assertEquals(q1.hashCode(), q2.hashCode());
  }
}
