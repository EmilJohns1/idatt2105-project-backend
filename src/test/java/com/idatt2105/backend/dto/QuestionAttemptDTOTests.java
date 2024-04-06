package com.idatt2105.backend.dto;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.enumerator.QuestionType;
import com.idatt2105.backend.model.AlternativeRecord;
import com.idatt2105.backend.model.MultipleChoiceQuestionAttempt;
import com.idatt2105.backend.model.TrueOrFalseQuestionAttempt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/** The QuestionAttemptDTOTests class is a test class that tests the QuestionAttemptDTO class. */
class QuestionAttemptDTOTests {

  private QuestionAttemptDTO questionAttemptDTO;

  @BeforeEach
  void setUp() {
    questionAttemptDTO = new QuestionAttemptDTO();
  }

  /**
   * The GetterTests class is a test class that tests the getters of the QuestionAttemptDTO class.
   */
  @Nested
  class GetterTests {
    /**
     * This method tests the getType method of the QuestionAttemptDTO class. It verifies that the
     * method returns the correct type.
     */
    @Test
    void testGetType() {
      QuestionType type = QuestionType.TRUE_OR_FALSE;
      questionAttemptDTO.setType(type);
      assertEquals(type, questionAttemptDTO.getType());
    }

    /**
     * This method tests the getQuestionText method of the QuestionAttemptDTO class. It verifies
     * that the method returns the correct question text.
     */
    @Test
    void testGetQuestionText() {
      String questionText = "This is a question.";
      questionAttemptDTO.setQuestionText(questionText);
      assertEquals(questionText, questionAttemptDTO.getQuestionText());
    }

    /**
     * This method tests the getMediaUrl method of the QuestionAttemptDTO class. It verifies that
     * the method returns the correct media url.
     */
    @Test
    void testGetMediaUrl() {
      String mediaUrl = "http://example.com";
      questionAttemptDTO.setMediaUrl(mediaUrl);
      assertEquals(mediaUrl, questionAttemptDTO.getMediaUrl());
    }

    /**
     * This method tests the getPoints method of the QuestionAttemptDTO class. It verifies that the
     * method returns the correct points.
     */
    @Test
    void testGetPoints() {
      int points = 10;
      questionAttemptDTO.setPoints(points);
      assertEquals(points, questionAttemptDTO.getPoints());
    }

    /**
     * This method tests the getAlternatives method of the QuestionAttemptDTO class. It verifies
     * that the method returns the correct alternatives.
     */
    @Test
    void testGetAlternatives() {
      Set<AlternativeRecord> alternatives = new HashSet<>();
      AlternativeRecord alternativeRecord = new AlternativeRecord();
      alternativeRecord.setId(1L);
      alternatives.add(alternativeRecord);
      questionAttemptDTO.setAlternatives(alternatives);
      assertEquals(alternatives, questionAttemptDTO.getAlternatives());
    }

    /**
     * This method tests the getUserAnswer method of the QuestionAttemptDTO class. It verifies that
     * the method returns the correct user answer.
     */
    @Test
    void testGetUserAnswer() {
      Boolean userAnswer = true;
      questionAttemptDTO.setUserAnswer(userAnswer);
      assertEquals(userAnswer, questionAttemptDTO.getUserAnswer());
    }

    /**
     * This method tests the getCorrectAnswer method of the QuestionAttemptDTO class. It verifies
     * that the method returns the correct answer.
     */
    @Test
    void testGetCorrectAnswer() {
      Boolean correctAnswer = false;
      questionAttemptDTO.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, questionAttemptDTO.getCorrectAnswer());
    }
  }

  /**
   * The SetterTests class is a test class that tests the setters of the QuestionAttemptDTO class.
   */
  @Nested
  class SetterTests {
    /**
     * This method tests the setType method of the QuestionAttemptDTO class. It verifies that the
     * method sets the correct type.
     */
    @Test
    void testSetType() {
      QuestionType type = QuestionType.TRUE_OR_FALSE;
      questionAttemptDTO.setType(type);
      assertEquals(type, questionAttemptDTO.getType());
    }

    /**
     * This method tests the setQuestionText method of the QuestionAttemptDTO class. It verifies
     * that the method sets the correct question text.
     */
    @Test
    void testSetQuestionText() {
      String questionText = "This is a question.";
      questionAttemptDTO.setQuestionText(questionText);
      assertEquals(questionText, questionAttemptDTO.getQuestionText());
    }

    /**
     * This method tests the setMediaUrl method of the QuestionAttemptDTO class. It verifies that
     * the method sets the correct media url.
     */
    @Test
    void testSetMediaUrl() {
      String mediaUrl = "http://example.com";
      questionAttemptDTO.setMediaUrl(mediaUrl);
      assertEquals(mediaUrl, questionAttemptDTO.getMediaUrl());
    }

    /**
     * This method tests the setPoints method of the QuestionAttemptDTO class. It verifies that the
     * method sets the correct points.
     */
    @Test
    void testSetPoints() {
      int points = 10;
      questionAttemptDTO.setPoints(points);
      assertEquals(points, questionAttemptDTO.getPoints());
    }

    /**
     * This method tests the setAlternatives method of the QuestionAttemptDTO class. It verifies
     * that the method sets the correct alternatives.
     */
    @Test
    void testSetAlternatives() {
      Set<AlternativeRecord> alternatives = new HashSet<>();
      AlternativeRecord alternativeRecord = new AlternativeRecord();
      alternativeRecord.setId(1L);
      alternatives.add(alternativeRecord);
      questionAttemptDTO.setAlternatives(alternatives);
      assertEquals(alternatives, questionAttemptDTO.getAlternatives());
    }

    /**
     * This method tests the setUserAnswer method of the QuestionAttemptDTO class. It verifies that
     * the method sets the correct user answer.
     */
    @Test
    void testSetUserAnswer() {
      Boolean userAnswer = true;
      questionAttemptDTO.setUserAnswer(userAnswer);
      assertEquals(userAnswer, questionAttemptDTO.getUserAnswer());
    }

    /**
     * This method tests the setCorrectAnswer method of the QuestionAttemptDTO class. It verifies
     * that the method sets the correct answer.
     */
    @Test
    void testSetCorrectAnswer() {
      Boolean correctAnswer = false;
      questionAttemptDTO.setCorrectAnswer(correctAnswer);
      assertEquals(correctAnswer, questionAttemptDTO.getCorrectAnswer());
    }
  }

  /**
   * This method tests the instantiateQuestionAttempt method of the QuestionAttemptDTO class. It
   * verifies that the method returns the correct QuestionAttempt entity.
   */
  @Test
  void testInstantiateQuestionAttempt() {
    questionAttemptDTO.setType(QuestionType.TRUE_OR_FALSE);
    assertInstanceOf(
        TrueOrFalseQuestionAttempt.class, questionAttemptDTO.instantiateQuestionAttempt());

    questionAttemptDTO.setType(QuestionType.MULTIPLE_CHOICE);
    assertInstanceOf(
        MultipleChoiceQuestionAttempt.class, questionAttemptDTO.instantiateQuestionAttempt());
  }
}
