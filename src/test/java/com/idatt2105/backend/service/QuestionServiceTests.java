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

@SpringBootTest
class QuestionServiceTests {
  @InjectMocks private QuestionService questionService;
  @Mock private QuestionRepository questionRepository;
  @Mock private QuizRepository quizRepository;
  @Mock private AlternativeRepository alternativeRepository;

  @Nested
  class BasicFunctionality {
    @BeforeEach
    void setUp() {
      when(quizRepository.findById(1L)).thenReturn(Optional.of(new Quiz()));
      when(questionRepository.save(any(Question.class))).thenAnswer(returnsFirstArg());
      when(questionRepository.findById(1L)).thenReturn(Optional.of(new MultipleChoiceQuestion()));
      when(questionRepository.findById(2L)).thenReturn(Optional.of(new TrueOrFalseQuestion()));
    }

    @Test
    void addQuestionCreatesMultipleChoiceQuestionFromDTO() {
      QuestionDTO input = new QuestionDTO();
      input.setQuizId(1L);
      input.setType(QuestionType.MULTIPLE_CHOICE);
      input.setQuestionText("What is the capital of Norway?");

      Question actual = questionService.addQuestion(input);
      assertDoesNotThrow(() -> (MultipleChoiceQuestion) actual);
    }

    @Test
    void addQuestionCreatesTrueOrFalseQuestionFromDTO() {
      QuestionDTO input = new QuestionDTO();
      input.setQuizId(1L);
      input.setType(QuestionType.TRUE_OR_FALSE);
      input.setQuestionText("Is the capital of Norway Oslo?");

      Question actual = questionService.addQuestion(input);
      assertDoesNotThrow(() -> (TrueOrFalseQuestion) actual);
    }

    @Test
    void getQuestionByIdReturnsQuestion() {
      Question actual = questionService.getQuestionById(1L);
      assertDoesNotThrow(() -> (MultipleChoiceQuestion) actual);
    }

    @Test
    void deleteQuestionDeletesQuestionById() {
      questionService.deleteQuestion(1L);
      verify(questionRepository).delete(any());
    }

    @Test
    void updateQuestionUpdatesQuestionFromDTO() {
      QuestionDTO input = new QuestionDTO();
      input.setQuestionId(1L);
      input.setQuestionText("Updated question text");

      Question actual = questionService.updateQuestion(input);

      assertEquals("Updated question text", actual.getQuestionText());
    }

    @Test
    void updateTrueOrFalseQuestionUpdatesQuestion() {
      QuestionDTO input = new QuestionDTO();
      input.setQuestionId(2L);
      input.isCorrect(true);
      assertTrue(questionService.updateTrueOrFalseQuestion(input).getCorrectAnswer());
    }

    @Test
    void getQuestionsByQuizIdReturnsListOfQuestions() {
      questionService.getQuestionsByQuizId(1L);
      verify(questionRepository).findQuestionsByQuizId(1L);
    }

    @Test
    void addAlternativeCorrectlyParsesDTO() {
      AlternativeDTO expected = new AlternativeDTO();
      expected.setQuestionId(1L);
      expected.setAlternativeText("Alternative text");

      Alternative actual = questionService.addAlternative(expected);
      assertEquals(expected.getAlternativeText(), actual.getAlternativeText());
      assertInstanceOf(MultipleChoiceQuestion.class, actual.getQuestion());
    }

    @Test
    void deleteAlternativeDeletesAlternative() {
      questionService.deleteAlternative(1L);
      verify(alternativeRepository).deleteById(1L);
    }
  }

  @Nested
  class InvalidParameterTests {
    @Test
    void addQuestionThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> questionService.addQuestion(null));
    }

    @Test
    void addQuestionThrowsExceptionWhenDTOIdIsNull() {
      QuestionDTO test = new QuestionDTO();
      test.setQuizId(null);

      assertThrows(InvalidIdException.class, () -> questionService.addQuestion(test));
    }

    @Test
    void addQuestionThrowsExceptionWhenQuizIsNotFound() {
      QuestionDTO test = new QuestionDTO();
      test.setQuizId(5L);
      when(quizRepository.findById(anyLong())).thenReturn(Optional.empty());

      assertThrows(InvalidIdException.class, () -> questionService.addQuestion(test));
    }

    @Test
    void getQuestionByIdThrowsExceptionWhenParameterIsNull() {
      assertThrows(IllegalArgumentException.class, () -> questionService.getQuestionById(null));
    }

    @Test
    void getQuestionByIdThrowsExceptionWhenQuestionIsNotFound() {
      when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());

      assertThrows(InvalidIdException.class, () -> questionService.getQuestionById(1L));
    }

    @Test
    void deleteQuestionThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> questionService.deleteQuestion(null));
    }

    @Test
    void updateQuestionThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> questionService.updateQuestion(null));
    }

    @Test
    void updateTrueOrFalseQuestionThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(
          IllegalArgumentException.class, () -> questionService.updateTrueOrFalseQuestion(null));
    }

    @Test
    void updateTrueOrFalseQuestionThrowsExceptionWhenIdIsDifferentType() {
      when(questionRepository.findById(1L)).thenReturn(Optional.of(new MultipleChoiceQuestion()));
      QuestionDTO test = new QuestionDTO();
      test.setQuestionId(1L);
      test.isCorrect(true);
      assertThrows(InvalidIdException.class, () -> questionService.updateTrueOrFalseQuestion(test));
    }

    @Test
    void updateTrueOrFalseQuestionThrowsExceptionWhenIsCorrectFieldIsNull() {
      QuestionDTO input = new QuestionDTO();
      input.isCorrect(null);
      assertThrows(
          IllegalArgumentException.class, () -> questionService.updateTrueOrFalseQuestion(input));
    }

    @Test
    void getQuestionsByQuizIdThrowsExceptionWhenParameterIsNull() {
      assertThrows(
          IllegalArgumentException.class, () -> questionService.getQuestionsByQuizId(null));
    }

    @Test
    void addAlternativeThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> questionService.addAlternative(null));
    }

    @Test
    void addAlternativeThrowsExceptionWhenQuestionTypeIsNotMultipleChoice() {
      when(questionRepository.findById(1L)).thenReturn(Optional.of(new TrueOrFalseQuestion()));
      AlternativeDTO input = new AlternativeDTO();
      input.setQuestionId(1L);

      assertThrows(InvalidIdException.class, () -> questionService.addAlternative(input));
    }

    @Test
    void deleteAlternativeThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> questionService.deleteAlternative(null));
    }
  }
}
