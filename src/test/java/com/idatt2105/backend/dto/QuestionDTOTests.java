package com.idatt2105.backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.enumerator.QuestionType;
import com.idatt2105.backend.model.MultipleChoiceQuestion;
import com.idatt2105.backend.model.TrueOrFalseQuestion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The QuestionDTOTests class is a test class that tests the QuestionDTO class. */
class QuestionDTOTests {

  /** The Getters class is a test class that tests the getters of the QuestionDTO class. */
  @Nested
  class Getters {
    private QuestionDTO question;

    @BeforeEach
    void setUp() {
      question = new QuestionDTO();
      question.setQuizId(1L);
      question.setQuestionText("Test question");
      question.setType(QuestionType.MULTIPLE_CHOICE);
      question.setQuestionId(2L);
      question.setMediaUrl("test.com");
      question.isCorrect(true);
    }

    /**
     * This method tests the getQuizId method of the QuestionDTO class. It verifies that the method
     * returns the correct quiz id.
     */
    @Test
    void getQuizIdReturnsQuizId() {
      assertEquals(1L, question.getQuizId());
    }

    /**
     * This method tests the getQuestionText method of the QuestionDTO class. It verifies that the
     * method returns the correct question text.
     */
    @Test
    void getQuestionTextReturnsQuestionText() {
      assertEquals("Test question", question.getQuestionText());
    }

    /**
     * This method tests the getType method of the QuestionDTO class. It verifies that the method
     * returns the correct type.
     */
    @Test
    void getTypeReturnsType() {
      assertEquals(QuestionType.MULTIPLE_CHOICE, question.getType());
    }

    /**
     * This method tests the getQuestionId method of the QuestionDTO class. It verifies that the
     * method returns the correct question id.
     */
    @Test
    void getQuestionIdReturnsQuestionId() {
      assertEquals(2L, question.getQuestionId());
    }

    /**
     * This method tests the getMediaUrl method of the QuestionDTO class. It verifies that the
     * method returns the correct media url.
     */
    @Test
    void getMediaUrlReturnsMediaUrl() {
      assertEquals("test.com", question.getMediaUrl());
    }

    /**
     * This method tests the isCorrect method of the QuestionDTO class. It verifies that the method
     * returns the correct boolean value.
     */
    @Test
    void isCorrectReturnsCorrect() {
      assertTrue(question.isCorrect());
    }

    /**
     * This method tests the instantiateQuestion method of the QuestionDTO class with a TrueOrFalse
     * question. It verifies that the method returns the correct question type.
     */
    @Test
    void instantiateQuestionReturnsTrueOrFalseQuestion() {
      QuestionDTO questionDTO = new QuestionDTO();
      questionDTO.setType(QuestionType.TRUE_OR_FALSE);
      assertInstanceOf(TrueOrFalseQuestion.class, questionDTO.instantiateQuestion());
    }

    /**
     * This method tests the instantiateQuestion method of the QuestionDTO class when the type is
     * multiple choice. It verifies that the method returns the correct question type.
     */
    @Test
    void instantiateQuestionReturnsMultipleChoiceQuestion() {
      QuestionDTO questionDTO = new QuestionDTO();
      questionDTO.setType(QuestionType.MULTIPLE_CHOICE);
      assertInstanceOf(MultipleChoiceQuestion.class, questionDTO.instantiateQuestion());
    }
  }

  /** The Setters class is a test class that tests the setters of the QuestionDTO class. */
  @Nested
  class Setters {
    private QuestionDTO question;

    @BeforeEach
    void setUp() {
      question = new QuestionDTO();
    }

    /**
     * This method tests the setQuizId method of the QuestionDTO class. It verifies that the method
     * sets the correct quiz id.
     */
    @Test
    void setQuizIdSetsQuizId() {
      question.setQuizId(1L);
      assertEquals(1L, question.getQuizId());
    }

    /**
     * This method tests the setQuestionText method of the QuestionDTO class. It verifies that the
     * method sets the correct question text.
     */
    @Test
    void setQuestionTextSetsQuestionText() {
      question.setQuestionText("Test question");
      assertEquals("Test question", question.getQuestionText());
    }

    /**
     * This method tests the setType method of the QuestionDTO class. It verifies that the method
     * sets the correct type.
     */
    @Test
    void setTypeSetsType() {
      question.setType(QuestionType.MULTIPLE_CHOICE);
      assertEquals(QuestionType.MULTIPLE_CHOICE, question.getType());
    }

    /**
     * This method tests the setQuestionId method of the QuestionDTO class. It verifies that the
     * method sets the correct question id.
     */
    @Test
    void setQuestionIdSetsQuestionId() {
      question.setQuestionId(2L);
      assertEquals(2L, question.getQuestionId());
    }

    /**
     * This method tests the setMediaUrl method of the QuestionDTO class. It verifies that the
     * method sets the correct media url.
     */
    @Test
    void setMediaUrlSetsMediaUrl() {
      question.setMediaUrl("test.com");
      assertEquals("test.com", question.getMediaUrl());
    }

    /**
     * This method tests the isCorrect method of the QuestionDTO class. It verifies that the method
     * sets the correct boolean value.
     */
    @Test
    void setCorrectSetsCorrect() {
      question.isCorrect(true);
      assertTrue(question.isCorrect());
    }
  }

  /**
   * The toStringHashCodeEqualsTests class is a test class that tests the toString, hashCode, and
   * equals methods of the QuestionDTO class.
   */
  @Nested
  class toStringHashCodeEqualsTests {
    /**
     * This method tests the equals method of the QuestionDTO class. It verifies that the method
     * returns true when the objects are equal and false when the objects are not equal.
     */
    @Test
    void testEquals() {
      QuestionDTO question1 = new QuestionDTO();
      question1.setQuizId(1L);
      question1.setQuestionText("Test question");
      question1.setType(QuestionType.MULTIPLE_CHOICE);
      question1.setQuestionId(2L);
      question1.setMediaUrl("test.com");
      question1.isCorrect(true);

      QuestionDTO question2 = new QuestionDTO();
      question2.setQuizId(1L);
      question2.setQuestionText("Test question");
      question2.setType(QuestionType.MULTIPLE_CHOICE);
      question2.setQuestionId(2L);
      question2.setMediaUrl("test.com");
      question2.isCorrect(true);

      QuestionDTO question3 = new QuestionDTO();
      question3.setQuizId(2L);
      question3.setQuestionText("Different question");
      question3.setType(QuestionType.TRUE_OR_FALSE);
      question3.setQuestionId(3L);
      question3.setMediaUrl("different.com");
      question3.isCorrect(false);

      assertEquals(question1, question2);
      assertEquals(question1.hashCode(), question2.hashCode());
      assertEquals(question1, question1);
      assertEquals(question1.hashCode(), question1.hashCode());
      assertTrue(question1.canEqual(question3));
    }

    /**
     * This method tests the hashCode method of the QuestionDTO class. It verifies that the method
     * returns the correct hash code and that the hash code is the same for equal objects.
     */
    @Test
    void testHashCode() {
      QuestionDTO question1 = new QuestionDTO();
      question1.setQuizId(1L);
      question1.setQuestionText("Test question");
      question1.setType(QuestionType.MULTIPLE_CHOICE);
      question1.setQuestionId(2L);
      question1.setMediaUrl("test.com");
      question1.isCorrect(true);

      QuestionDTO question2 = new QuestionDTO();
      question2.setQuizId(1L);
      question2.setQuestionText("Test question");
      question2.setType(QuestionType.MULTIPLE_CHOICE);
      question2.setQuestionId(2L);
      question2.setMediaUrl("test.com");
      question2.isCorrect(true);

      assertEquals(question1.hashCode(), question2.hashCode());
    }

    /**
     * This method tests the toString method of the QuestionDTO class. It verifies that the method
     * returns the correct string representation of the object.
     */
    @Test
    void testToString() {
      QuestionDTO question = new QuestionDTO();
      question.setQuizId(1L);
      question.setQuestionText("Test question");
      question.setType(QuestionType.MULTIPLE_CHOICE);
      question.setQuestionId(2L);
      question.setMediaUrl("test.com");
      question.isCorrect(true);

      String expected =
          "QuestionDTO(questionId=2, questionText=Test question, mediaUrl=test.com, points=0, quizId=1, type=multiple_choice, isCorrect=true)";
      assertEquals(expected, question.toString());
    }
  }
}
