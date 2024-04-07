package com.idatt2105.backend.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** The QuestionTests class is a test class that tests the Question class. */
class QuestionTests {
  /** The GetterTests class is a test class that tests the getters of the Question class. */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the Question class. It verifies that the method returns
     * the correct id.
     */
    @Test
    void getIdReturnsCorrectId() {
      Question question = new Question();
      question.setId(1L);
      assertEquals(1L, question.getId());
    }

    /**
     * This method tests the getQuestionText method of the Question class. It verifies that the
     * method returns the correct question text.
     */
    @Test
    void getQuestionTextReturnsCorrectText() {
      Question question = new Question();
      question.setQuestionText("What is the capital of Norway?");
      assertEquals("What is the capital of Norway?", question.getQuestionText());
    }

    /**
     * This method tests the getMediaUrl method of the Question class. It verifies that the method
     * returns the correct media url.
     */
    @Test
    void getMediaUrlReturnsCorrectUrl() {
      Question question = new Question();
      question.setMediaUrl("image.jpg");
      assertEquals("image.jpg", question.getMediaUrl());
    }

    /**
     * This method tests the getPoints method of the Question class. It verifies that the method
     * returns the correct points.
     */
    @Test
    void getPointsReturnsCorrectPoints() {
      Question question = new Question();
      question.setPoints(10);
      assertEquals(10, question.getPoints());
    }

    /**
     * This method tests the getQuiz method of the Question class. It verifies that the method
     * returns the correct quiz.
     */
    @Test
    void getQuizReturnsCorrectQuiz() {
      Question question = new Question();
      Quiz quiz = new Quiz();
      question.setQuiz(quiz);
      assertEquals(quiz, question.getQuiz());
    }
  }

  /** The SetterTests class is a test class that tests the setters of the Question class. */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the Question class. It verifies that the method sets
     * the correct id.
     */
    @Test
    void setIdSetsCorrectId() {
      Question question = new Question();
      question.setId(1L);
      assertEquals(1L, question.getId());
    }

    /**
     * This method tests the setQuestionText method of the Question class. It verifies that the
     * method sets the correct question text.
     */
    @Test
    void setQuestionTextSetsCorrectText() {
      Question question = new Question();
      question.setQuestionText("What is the capital of Norway?");
      assertEquals("What is the capital of Norway?", question.getQuestionText());
    }

    /**
     * This method tests the setMediaUrl method of the Question class. It verifies that the method
     * sets the correct media url.
     */
    @Test
    void setMediaUrlSetsCorrectUrl() {
      Question question = new Question();
      question.setMediaUrl("image.jpg");
      assertEquals("image.jpg", question.getMediaUrl());
    }

    /**
     * This method tests the setPoints method of the Question class. It verifies that the method
     * sets the correct points.
     */
    @Test
    void setPointsSetsCorrectPoints() {
      Question question = new Question();
      question.setPoints(10);
      assertEquals(10, question.getPoints());
    }

    /**
     * This method tests the setQuiz method of the Question class. It verifies that the method sets
     * the correct quiz.
     */
    @Test
    void setQuizSetsCorrectQuiz() {
      Question question = new Question();
      Quiz quiz = new Quiz();
      question.setQuiz(quiz);
      assertEquals(quiz, question.getQuiz());
    }
  }
}
