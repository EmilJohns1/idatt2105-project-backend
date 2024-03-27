package com.idatt2105.backend.dto;

import com.idatt2105.backend.enumerator.QuestionType;
import com.idatt2105.backend.model.AlternativeRecord;
import com.idatt2105.backend.model.MultipleChoiceQuestionAttempt;
import com.idatt2105.backend.model.TrueOrFalseQuestionAttempt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class QuestionAttemptDTOTests {

  private QuestionAttemptDTO questionAttemptDTO;

  @BeforeEach
  void setUp() {
    questionAttemptDTO = new QuestionAttemptDTO();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetType() {
      QuestionType type = QuestionType.TRUE_OR_FALSE;
      questionAttemptDTO.setType(type);
      assertEquals(type, questionAttemptDTO.getType());
    }

    @Test
    void testGetQuestionText() {
      String questionText = "This is a question.";
      questionAttemptDTO.setQuestionText(questionText);
      assertEquals(questionText, questionAttemptDTO.getQuestionText());
    }

    @Test
    void testGetMediaUrl() {
      String mediaUrl = "http://example.com";
      questionAttemptDTO.setMediaUrl(mediaUrl);
      assertEquals(mediaUrl, questionAttemptDTO.getMediaUrl());
    }

    @Test
    void testGetCategory() {
      String category = "Category";
      questionAttemptDTO.setCategory(category);
      assertEquals(category, questionAttemptDTO.getCategory());
    }

    @Test
    void testGetPoints() {
      int points = 10;
      questionAttemptDTO.setPoints(points);
      assertEquals(points, questionAttemptDTO.getPoints());
    }

    @Test
    void testGetAlternatives() {
      Set<AlternativeRecord> alternatives = new HashSet<>();
      AlternativeRecord alternativeRecord = new AlternativeRecord();
      alternativeRecord.setId(1L);
      alternatives.add(alternativeRecord);
      questionAttemptDTO.setAlternatives(alternatives);
      assertEquals(alternatives, questionAttemptDTO.getAlternatives());
    }

    @Test
    void testGetUserAnswer() {
      Boolean userAnswer = true;
      questionAttemptDTO.setUserAnswer(userAnswer);
      assertEquals(userAnswer, questionAttemptDTO.getUserAnswer());
    }

    @Test
    void testGetCorrectAnswer() {
      Boolean correctAnswer = false;
      questionAttemptDTO.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, questionAttemptDTO.getCorrectAnswer());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetType() {
      QuestionType type = QuestionType.TRUE_OR_FALSE;
      questionAttemptDTO.setType(type);
      assertEquals(type, questionAttemptDTO.getType());
    }

    @Test
    void testSetQuestionText() {
      String questionText = "This is a question.";
      questionAttemptDTO.setQuestionText(questionText);
      assertEquals(questionText, questionAttemptDTO.getQuestionText());
    }

    @Test
    void testSetMediaUrl() {
      String mediaUrl = "http://example.com";
      questionAttemptDTO.setMediaUrl(mediaUrl);
      assertEquals(mediaUrl, questionAttemptDTO.getMediaUrl());
    }

    @Test
    void testSetCategory() {
      String category = "Category";
      questionAttemptDTO.setCategory(category);
      assertEquals(category, questionAttemptDTO.getCategory());
    }

    @Test
    void testSetPoints() {
      int points = 10;
      questionAttemptDTO.setPoints(points);
      assertEquals(points, questionAttemptDTO.getPoints());
    }

    @Test
    void testSetAlternatives() {
      Set<AlternativeRecord> alternatives = new HashSet<>();
      AlternativeRecord alternativeRecord = new AlternativeRecord();
      alternativeRecord.setId(1L);
      alternatives.add(alternativeRecord);
      questionAttemptDTO.setAlternatives(alternatives);
      assertEquals(alternatives, questionAttemptDTO.getAlternatives());
    }

    @Test
    void testSetUserAnswer() {
      Boolean userAnswer = true;
      questionAttemptDTO.setUserAnswer(userAnswer);
      assertEquals(userAnswer, questionAttemptDTO.getUserAnswer());
    }

    @Test
    void testSetCorrectAnswer() {
      Boolean correctAnswer = false;
      questionAttemptDTO.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, questionAttemptDTO.getCorrectAnswer());
    }
  }

  @Test
  void testInstantiateQuestionAttempt() {
    questionAttemptDTO.setType(QuestionType.TRUE_OR_FALSE);
    assertInstanceOf(TrueOrFalseQuestionAttempt.class,
                     questionAttemptDTO.instantiateQuestionAttempt());

    questionAttemptDTO.setType(QuestionType.MULTIPLE_CHOICE);
    assertInstanceOf(MultipleChoiceQuestionAttempt.class,
                     questionAttemptDTO.instantiateQuestionAttempt());
  }
}