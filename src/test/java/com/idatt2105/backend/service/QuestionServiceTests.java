package com.idatt2105.backend.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.idatt2105.backend.dto.AlternativeDTO;
import com.idatt2105.backend.dto.QuestionDTO;
import com.idatt2105.backend.enumerator.QuestionType;
import com.idatt2105.backend.model.Alternative;
import com.idatt2105.backend.model.MultipleChoiceQuestion;
import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.TrueOrFalseQuestion;
import com.idatt2105.backend.repository.AlternativeRepository;
import com.idatt2105.backend.repository.QuestionRepository;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.util.InvalidIdException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** The QuestionServiceTests class is a test class that tests the QuestionService class. */
@SpringBootTest
class QuestionServiceTests {
  @InjectMocks private QuestionService questionService;
  @Mock private QuestionRepository questionRepository;
  @Mock private QuizRepository quizRepository;
  @Mock private AlternativeRepository alternativeRepository;

  /**
   * The BasicFunctionality class is a test class that tests the basic functionality of the
   * QuestionService class.
   */
  @Nested
  class BasicFunctionality {
    @BeforeEach
    void setUp() {
      when(quizRepository.findById(1L)).thenReturn(Optional.of(new Quiz()));
      when(questionRepository.save(any(Question.class))).thenAnswer(returnsFirstArg());
      when(questionRepository.findById(1L)).thenReturn(Optional.of(new MultipleChoiceQuestion()));
      when(questionRepository.findById(2L)).thenReturn(Optional.of(new TrueOrFalseQuestion()));
    }

    /**
     * The addQuestionCreatesMultipleChoiceQuestionFromDTO method tests the addQuestion method of
     * the QuestionService class. It verifies that the method creates a multiple choice question
     * from a DTO.
     */
    @Test
    void addQuestionCreatesMultipleChoiceQuestionFromDTO() {
      QuestionDTO input = new QuestionDTO();
      input.setQuizId(1L);
      input.setType(QuestionType.MULTIPLE_CHOICE);
      input.setQuestionText("What is the capital of Norway?");

      Question actual = questionService.addQuestion(input);
      assertDoesNotThrow(() -> (MultipleChoiceQuestion) actual);
    }

    /**
     * The addQuestionCreatesTrueOrFalseQuestionFromDTO method tests the addQuestion method of the
     * QuestionService class. It verifies that the method creates a true or false question from a
     * DTO.
     */
    @Test
    void addQuestionCreatesTrueOrFalseQuestionFromDTO() {
      QuestionDTO input = new QuestionDTO();
      input.setQuizId(1L);
      input.setType(QuestionType.TRUE_OR_FALSE);
      input.setQuestionText("Is the capital of Norway Oslo?");

      Question actual = questionService.addQuestion(input);
      assertDoesNotThrow(() -> (TrueOrFalseQuestion) actual);
    }

    /**
     * The getQuestionByIdReturnsQuestion method tests the getQuestionById method of the
     * QuestionService class. It verifies that the method returns a question.
     */
    @Test
    void getQuestionByIdReturnsQuestion() {
      Question actual = questionService.getQuestionById(1L);
      assertDoesNotThrow(() -> (MultipleChoiceQuestion) actual);
    }

    /**
     * The deleteQuestionDeletesQuestionById method tests the deleteQuestion method of the
     * QuestionService class. It verifies that the method deletes a question by id.
     */
    @Test
    void deleteQuestionDeletesQuestionById() {
      questionService.deleteQuestion(1L);
      verify(questionRepository).delete(any());
    }

    /**
     * The updateQuestionUpdatesQuestionFromDTO method tests the updateQuestion method of the
     * QuestionService class. It verifies that the method updates a question from a DTO.
     */
    @Test
    void updateQuestionUpdatesQuestionFromDTO() {
      QuestionDTO input = new QuestionDTO();
      input.setQuestionId(1L);
      input.setQuestionText("Updated question text");

      Question actual = questionService.updateQuestion(input);

      assertEquals("Updated question text", actual.getQuestionText());
    }

    /**
     * The updateTrueOrFalseQuestionUpdatesQuestion method tests the updateTrueOrFalseQuestion
     * method of the QuestionService class. It verifies that the method updates a true or false
     * question.
     */
    @Test
    void updateTrueOrFalseQuestionUpdatesQuestion() {
      QuestionDTO input = new QuestionDTO();
      input.setQuestionId(2L);
      input.isCorrect(true);
      assertTrue(questionService.updateTrueOrFalseQuestion(input).getCorrectAnswer());
    }

    /**
     * The getQuestionsByQuizIdReturnsListOfQuestions method tests the getQuestionsByQuizId method
     * of the QuestionService class. It verifies that the method returns a list of questions.
     */
    @Test
    void getQuestionsByQuizIdReturnsListOfQuestions() {
      questionService.getQuestionsByQuizId(1L);
      verify(questionRepository).findQuestionsByQuizId(1L);
    }

    /**
     * The addAlternativeCorrectlyParsesDTO method tests the addAlternative method of the
     * QuestionService class. It verifies that the method correctly parses a DTO.
     */
    @Test
    void addAlternativeCorrectlyParsesDTO() {
      AlternativeDTO expected = new AlternativeDTO();
      expected.setQuestionId(1L);
      expected.setAlternativeText("Alternative text");

      Alternative actual = questionService.addAlternative(expected);
      assertEquals(expected.getAlternativeText(), actual.getAlternativeText());
      assertInstanceOf(MultipleChoiceQuestion.class, actual.getQuestion());
    }

    /**
     * The deleteAlternativeDeletesAlternative method tests the deleteAlternative method of the
     * QuestionService class. It verifies that the method deletes an alternative.
     */
    @Test
    void deleteAlternativeDeletesAlternative() {
      questionService.deleteAlternative(1L);
      verify(alternativeRepository).deleteById(1L);
    }

    /**
     * The addQuestion_ValidQuestionDTO_ReturnsQuestion method tests the addQuestion method of the
     * QuestionService class. It verifies that the method returns a question.
     */
    @Test
    void addQuestion_ValidQuestionDTO_ReturnsQuestion() {
      // Arrange
      QuestionDTO questionDTO = new QuestionDTO();
      questionDTO.setQuizId(1L);
      questionDTO.setType(QuestionType.MULTIPLE_CHOICE);
      questionDTO.setQuestionText("What is the capital of Norway?");

      // Act
      Question question = questionService.addQuestion(questionDTO);

      // Assert
      assertInstanceOf(MultipleChoiceQuestion.class, question);
    }
  }

  /**
   * The InvalidParameterTests class is a test class that tests the invalid parameters of the
   * QuestionService class.
   */
  @Nested
  class InvalidParameterTests {
    /**
     * The addQuestion_NullQuestionDTO_ThrowsIllegalArgumentException method tests the addQuestion
     * method of the QuestionService class. It verifies that the method throws an
     * IllegalArgumentException when the parameter is null.
     */
    @Test
    void addQuestion_NullQuestionDTO_ThrowsIllegalArgumentException() {
      // Act & Assert
      assertThrows(IllegalArgumentException.class, () -> questionService.addQuestion(null));
    }

    /**
     * The addQuestionThrowsExceptionWhenGivenNullAsParameter method tests the addQuestion method of
     * the QuestionService class. It verifies that the method throws an IllegalArgumentException
     * when the parameter is null.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void addQuestionThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> questionService.addQuestion(null));
    }

    /**
     * The addQuestionThrowsExceptionWhenDTOIdIsNull method tests the addQuestion method of the
     * QuestionService class. It verifies that the method throws an InvalidIdException when the DTO
     * id is null.
     *
     * @throws InvalidIdException if the DTO id is null
     */
    @Test
    void addQuestionThrowsExceptionWhenDTOIdIsNull() {
      QuestionDTO test = new QuestionDTO();
      test.setQuizId(null);

      assertThrows(InvalidIdException.class, () -> questionService.addQuestion(test));
    }

    /**
     * The addQuestionThrowsExceptionWhenQuizIsNotFound method tests the addQuestion method of the
     * QuestionService class. It verifies that the method throws an InvalidIdException when the quiz
     * is not found.
     *
     * @throws InvalidIdException if the quiz is not found
     */
    @Test
    void addQuestionThrowsExceptionWhenQuizIsNotFound() {
      QuestionDTO test = new QuestionDTO();
      test.setQuizId(5L);
      when(quizRepository.findById(anyLong())).thenReturn(Optional.empty());

      assertThrows(InvalidIdException.class, () -> questionService.addQuestion(test));
    }

    /**
     * The getQuestionByIdThrowsExceptionWhenParameterIsNull method tests the getQuestionById method
     * of the QuestionService class. It verifies that the method throws an IllegalArgumentException
     * when the parameter is null.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void getQuestionByIdThrowsExceptionWhenParameterIsNull() {
      assertThrows(IllegalArgumentException.class, () -> questionService.getQuestionById(null));
    }

    /**
     * The getQuestionByIdThrowsExceptionWhenQuestionIsNotFound method tests the getQuestionById
     * method of the QuestionService class. It verifies that the method throws an InvalidIdException
     * when the question is not found.
     *
     * @throws InvalidIdException if the question is not found
     */
    @Test
    void getQuestionByIdThrowsExceptionWhenQuestionIsNotFound() {
      when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());

      assertThrows(InvalidIdException.class, () -> questionService.getQuestionById(1L));
    }

    /**
     * The deleteQuestionThrowsExceptionWhenGivenNullAsParameter method tests the deleteQuestion
     * method of the QuestionService class. It verifies that the method throws an
     * IllegalArgumentException when the parameter is null.
     */
    @Test
    void deleteQuestionThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> questionService.deleteQuestion(null));
    }

    /**
     * The updateQuestionThrowsExceptionWhenGivenNullAsParameter method tests the updateQuestion
     * method of the QuestionService class. It verifies that the method throws an
     * IllegalArgumentException when the parameter is null.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void updateQuestionThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> questionService.updateQuestion(null));
    }

    /**
     * The updateTrueOrFalseQuestionThrowsExceptionWhenGivenNullAsParameter method tests the
     * updateTrueOrFalseQuestion method of the QuestionService class. It verifies that the method
     * throws an IllegalArgumentException when the parameter is null.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void updateTrueOrFalseQuestionThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(
          IllegalArgumentException.class, () -> questionService.updateTrueOrFalseQuestion(null));
    }

    /**
     * The updateTrueOrFalseQuestionThrowsExceptionWhenIdIsDifferentType method tests the
     * updateTrueOrFalseQuestion method of the QuestionService class. It verifies that the method
     * throws an InvalidIdException when the id is of a different type.
     *
     * @throws InvalidIdException if the id is of a different type
     */
    @Test
    void updateTrueOrFalseQuestionThrowsExceptionWhenIdIsDifferentType() {
      when(questionRepository.findById(1L)).thenReturn(Optional.of(new MultipleChoiceQuestion()));
      QuestionDTO test = new QuestionDTO();
      test.setQuestionId(1L);
      test.isCorrect(true);
      assertThrows(InvalidIdException.class, () -> questionService.updateTrueOrFalseQuestion(test));
    }

    /**
     * The updateTrueOrFalseQuestionThrowsExceptionWhenIsCorrectFieldIsNull method tests the
     * updateTrueOrFalseQuestion method of the QuestionService class. It verifies that the method
     * throws an IllegalArgumentException when the isCorrect field is null.
     *
     * @throws IllegalArgumentException if the isCorrect field is null
     */
    @Test
    void updateTrueOrFalseQuestionThrowsExceptionWhenIsCorrectFieldIsNull() {
      QuestionDTO input = new QuestionDTO();
      input.isCorrect(null);
      assertThrows(
          IllegalArgumentException.class, () -> questionService.updateTrueOrFalseQuestion(input));
    }

    /**
     * The getQuestionsByQuizIdThrowsExceptionWhenParameterIsNull method tests the
     * getQuestionsByQuizId method of the QuestionService class. It verifies that the method throws
     * an IllegalArgumentException when the parameter is null.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void getQuestionsByQuizIdThrowsExceptionWhenParameterIsNull() {
      assertThrows(
          IllegalArgumentException.class, () -> questionService.getQuestionsByQuizId(null));
    }

    /**
     * The addAlternativeThrowsExceptionWhenGivenNullAsParameter method tests the addAlternative
     * method of the QuestionService class. It verifies that the method throws an
     * IllegalArgumentException when the parameter is null.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void addAlternativeThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> questionService.addAlternative(null));
    }

    /**
     * The addAlternativeThrowsExceptionWhenQuestionTypeIsNotMultipleChoice method tests the
     * addAlternative method of the QuestionService class. It verifies that the method throws an
     * InvalidIdException when the question type is not multiple choice.
     *
     * @throws InvalidIdException if the question type is not multiple choice
     */
    @Test
    void addAlternativeThrowsExceptionWhenQuestionTypeIsNotMultipleChoice() {
      when(questionRepository.findById(1L)).thenReturn(Optional.of(new TrueOrFalseQuestion()));
      AlternativeDTO input = new AlternativeDTO();
      input.setQuestionId(1L);

      assertThrows(InvalidIdException.class, () -> questionService.addAlternative(input));
    }

    /**
     * The deleteAlternativeThrowsExceptionWhenGivenNullAsParameter method tests the
     * deleteAlternative method of the QuestionService class. It verifies that the method throws an
     * IllegalArgumentException when the parameter is null.
     */
    @Test
    void deleteAlternativeThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> questionService.deleteAlternative(null));
    }
  }
}
