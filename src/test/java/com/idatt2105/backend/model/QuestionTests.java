package com.idatt2105.backend.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTests {

  @Nested
  class GetterTests {
    @Test
    void getIdReturnsCorrectId() {
      Question question = new Question();
      question.setId(1L);
      assertEquals(1L, question.getId());
    }

    @Test
    void getQuestionTextReturnsCorrectText() {
      Question question = new Question();
      question.setQuestionText("What is the capital of Norway?");
      assertEquals("What is the capital of Norway?", question.getQuestionText());
    }

    @Test
    void getMediaUrlReturnsCorrectUrl() {
      Question question = new Question();
      question.setMediaUrl("image.jpg");
      assertEquals("image.jpg", question.getMediaUrl());
    }

    @Test
    void getPointsReturnsCorrectPoints() {
      Question question = new Question();
      question.setPoints(10);
      assertEquals(10, question.getPoints());
    }

    @Test
    void getQuizReturnsCorrectQuiz() {
      Question question = new Question();
      Quiz quiz = new Quiz();
      question.setQuiz(quiz);
      assertEquals(quiz, question.getQuiz());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void setIdSetsCorrectId() {
      Question question = new Question();
      question.setId(1L);
      assertEquals(1L, question.getId());
    }

    @Test
    void setQuestionTextSetsCorrectText() {
      Question question = new Question();
      question.setQuestionText("What is the capital of Norway?");
      assertEquals("What is the capital of Norway?", question.getQuestionText());
    }

    @Test
    void setMediaUrlSetsCorrectUrl() {
      Question question = new Question();
      question.setMediaUrl("image.jpg");
      assertEquals("image.jpg", question.getMediaUrl());
    }

    @Test
    void setPointsSetsCorrectPoints() {
      Question question = new Question();
      question.setPoints(10);
      assertEquals(10, question.getPoints());
    }

    @Test
    void setQuizSetsCorrectQuiz() {
      Question question = new Question();
      Quiz quiz = new Quiz();
      question.setQuiz(quiz);
      assertEquals(quiz, question.getQuiz());
    }
  }
}
