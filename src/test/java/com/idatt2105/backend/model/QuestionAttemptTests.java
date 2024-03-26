package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionAttemptTests {
  private QuestionAttempt questionAttempt;

  @BeforeEach
  public void setup() {
    questionAttempt = new QuestionAttempt();
  }

  @Nested
  class GetterTests {
    @Test
    void getIdReturnsCorrectId() {
      Long expectedId = 1L;
      questionAttempt.setId(expectedId);
      assertEquals(expectedId, questionAttempt.getId());
    }

    @Test
    void getQuestionTextReturnsCorrectQuestionText() {
      String expectedQuestionText = "What is the capital of France?";
      questionAttempt.setQuestionText(expectedQuestionText);
      assertEquals(expectedQuestionText, questionAttempt.getQuestionText());
    }

    @Test
    void getMediaUrlReturnsCorrectMediaUrl() {
      String expectedMediaUrl = "https://example.com/image.jpg";
      questionAttempt.setMediaUrl(expectedMediaUrl);
      assertEquals(expectedMediaUrl, questionAttempt.getMediaUrl());
    }

    @Test
    void getCategoryReturnsCorrectCategory() {
      String expectedCategory = "Geography";
      questionAttempt.setCategory(expectedCategory);
      assertEquals(expectedCategory, questionAttempt.getCategory());
    }

    @Test
    void getPointsReturnsCorrectPoints() {
      int expectedPoints = 5;
      questionAttempt.setPoints(expectedPoints);
      assertEquals(expectedPoints, questionAttempt.getPoints());
    }

    @Test
    void getQuizAttemptReturnsCorrectQuizAttempt() {
      QuizAttempt expectedQuizAttempt = new QuizAttempt();
      questionAttempt.setQuizAttempt(expectedQuizAttempt);
      assertEquals(expectedQuizAttempt, questionAttempt.getQuizAttempt());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void setIdSetsIdCorrectly() {
      Long expectedId = 1L;
      questionAttempt.setId(expectedId);
      assertEquals(expectedId, questionAttempt.getId());
    }

    @Test
    void setQuestionTextSetsQuestionTextCorrectly() {
      String expectedQuestionText = "What is the capital of France?";
      questionAttempt.setQuestionText(expectedQuestionText);
      assertEquals(expectedQuestionText, questionAttempt.getQuestionText());
    }

    @Test
    void setMediaUrlSetsMediaUrlCorrectly() {
      String expectedMediaUrl = "https://example.com/image.jpg";
      questionAttempt.setMediaUrl(expectedMediaUrl);
      assertEquals(expectedMediaUrl, questionAttempt.getMediaUrl());
    }

    @Test
    void setCategorySetsCategoryCorrectly() {
      String expectedCategory = "Geography";
      questionAttempt.setCategory(expectedCategory);
      assertEquals(expectedCategory, questionAttempt.getCategory());
    }

    @Test
    void setPointsSetsPointsCorrectly() {
      int expectedPoints = 5;
      questionAttempt.setPoints(expectedPoints);
      assertEquals(expectedPoints, questionAttempt.getPoints());
    }

    @Test
    void setQuizAttemptSetsQuizAttemptCorrectly() {
      QuizAttempt expectedQuizAttempt = new QuizAttempt();
      questionAttempt.setQuizAttempt(expectedQuizAttempt);
      assertEquals(expectedQuizAttempt, questionAttempt.getQuizAttempt());
    }
  }
}
