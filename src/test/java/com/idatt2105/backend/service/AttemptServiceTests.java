package com.idatt2105.backend.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.idatt2105.backend.dto.QuestionAttemptDTO;
import com.idatt2105.backend.dto.QuizAttemptDTO;
import com.idatt2105.backend.enumerator.QuestionType;
import com.idatt2105.backend.model.AlternativeRecord;
import com.idatt2105.backend.model.MultipleChoiceQuestionAttempt;
import com.idatt2105.backend.model.QuestionAttempt;
import com.idatt2105.backend.model.QuizAttempt;
import com.idatt2105.backend.model.TrueOrFalseQuestionAttempt;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.QuizAttemptRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.InvalidIdException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AttemptServiceTests {
  @Mock private QuizAttemptRepository quizAttemptRepository;

  @Mock private UserRepository userRepository;

  @InjectMocks private AttemptService attemptService;

  private User user;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setId(1L);
  }

  @Test
  void addQuizAttempt() {
    QuizAttemptDTO input = new QuizAttemptDTO();
    input.setQuizId(1L);
    input.setScore(5);
    input.setUserId(1L);

    QuizAttempt expected = new QuizAttempt();
    expected.setQuizId(1L);
    expected.setScore(5);
    expected.setUser(user);

    when(quizAttemptRepository.save(expected)).thenReturn(expected);
    when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

    QuizAttempt actual = attemptService.addQuizAttempt(input);
    assertEquals(expected, actual);
  }

  @Test
  void addQuizAttemptWithInvalidUserIdThrowsException() {
    QuizAttemptDTO input = new QuizAttemptDTO();
    input.setQuizId(1L);
    input.setScore(5);
    input.setUserId(1L);

    when(userRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(InvalidIdException.class, () -> attemptService.addQuizAttempt(input));
  }

  @Test
  void getAllAttemptsForUser() {
    QuizAttempt quizAttempt = new QuizAttempt();
    quizAttempt.setId(1L);
    User user = new User();
    user.setId(1L);
    when(quizAttemptRepository.findByUserId(1L)).thenReturn(List.of(quizAttempt));
    when(userRepository.existsById(1L)).thenReturn(true);

    Collection<QuizAttempt> actual = attemptService.getAllAttemptsForUser(1L);
    assertEquals(List.of(quizAttempt), actual);
  }

  @Test
  void getAllAttemptsForUserWithInvalidUserIdThrowsException() {
    when(userRepository.findById(1L)).thenReturn(Optional.empty());
    assertThrows(InvalidIdException.class, () -> attemptService.getAllAttemptsForUser(1L));
  }

  @Test
  void addQuizAttemptWithNullAsParameterThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> attemptService.addQuizAttempt(null));
  }

  @Test
  void getAllAttemptsForUserWithNullAsParameterThrowsException() {
    assertThrows(InvalidIdException.class, () -> attemptService.getAllAttemptsForUser(null));
  }

  @Nested
  class AttemptParsingTests {
    private QuizAttemptDTO input;

    @BeforeEach
    void setUp() {
      input = new QuizAttemptDTO();
      input.setQuizId(1L);
      input.setScore(5);
      input.setUserId(1L);

      when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
      when(quizAttemptRepository.save(any(QuizAttempt.class))).thenAnswer(returnsFirstArg());
    }

    @Test
    void multipleChoiceAttemptsAreParsedCorrectly() {
      QuestionAttemptDTO questionAttempt = new QuestionAttemptDTO();
      questionAttempt.setType(QuestionType.MULTIPLE_CHOICE);
      input.setQuestionAttempts(Set.of(questionAttempt));

      QuizAttempt actual = attemptService.addQuizAttempt(input);
      QuestionAttempt actualQuestionAttempt = actual.getQuestionAttempts().iterator().next();
      assertDoesNotThrow(() -> (MultipleChoiceQuestionAttempt) actualQuestionAttempt);
    }

    @Test
    void multipleChoiceAlternativesAreParsedCorrectly() {
      QuestionAttemptDTO questionAttempt = new QuestionAttemptDTO();
      questionAttempt.setType(QuestionType.MULTIPLE_CHOICE);
      AlternativeRecord alternative = new AlternativeRecord();
      alternative.wasCorrect(true);
      alternative.wasSelected(true);
      questionAttempt.setAlternatives(Set.of(alternative));
      input.setQuestionAttempts(Set.of(questionAttempt));

      QuizAttempt actual = attemptService.addQuizAttempt(input);
      MultipleChoiceQuestionAttempt actualQuestionAttempt =
          (MultipleChoiceQuestionAttempt) actual.getQuestionAttempts().iterator().next();
      assertEquals(alternative, actualQuestionAttempt.getAlternatives().iterator().next());
    }

    @Test
    void trueOrFalseAttemptsAreParsedCorrectly() {
      QuestionAttemptDTO questionAttempt = new QuestionAttemptDTO();
      questionAttempt.setType(QuestionType.TRUE_OR_FALSE);
      input.setQuestionAttempts(Set.of(questionAttempt));

      QuizAttempt actual = attemptService.addQuizAttempt(input);
      QuestionAttempt actualQuestionAttempt = actual.getQuestionAttempts().iterator().next();
      assertDoesNotThrow(() -> (TrueOrFalseQuestionAttempt) actualQuestionAttempt);
    }
  }
}
