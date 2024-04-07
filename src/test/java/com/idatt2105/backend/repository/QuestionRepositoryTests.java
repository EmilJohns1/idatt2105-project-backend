package com.idatt2105.backend.repository;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.idatt2105.backend.dto.AlternativeDTO;
import com.idatt2105.backend.model.MultipleChoiceQuestion;
import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.TrueOrFalseQuestion;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The QuestionRepositoryTests class is a test class that tests the QuestionRepository class. */
@DataJpaTest
class QuestionRepositoryTests {
  @Autowired private QuestionRepository questionRepository;

  @Autowired private QuizRepository quizRepository;

  private Quiz quiz;
  private MultipleChoiceQuestion multipleChoiceQuestion;

  @BeforeEach
  void setUp() {
    questionRepository.deleteAll();
    quizRepository.deleteAll();
    quiz = new Quiz();
    quiz.setTitle("Test quiz");
    quizRepository.save(quiz);

    multipleChoiceQuestion = new MultipleChoiceQuestion();
    multipleChoiceQuestion.setQuestionText("What is the capital of Norway?");
    multipleChoiceQuestion.setQuiz(quiz);
    AlternativeDTO alternative = new AlternativeDTO();
    alternative.setAlternativeText("Oslo");
    alternative.setCorrect(true);
    multipleChoiceQuestion.addAlternative(alternative);
    questionRepository.save(multipleChoiceQuestion);
  }

  /**
   * This method tests that saved questions can be retrieved by quiz id. It verifies that the method
   * returns the correct question.
   */
  @Test
  void savedQuestionsCanBeRetrievedByQuizId() {
    Optional<Question> retrievedQuestion =
        questionRepository.findById(multipleChoiceQuestion.getId());
    assertTrue(retrievedQuestion.isPresent());
    assertEquals(
        multipleChoiceQuestion.getQuestionText(), retrievedQuestion.get().getQuestionText());
  }

  /**
   * This method tests that multiple choice questions are saved properly. It verifies that the
   * method returns the correct question.
   */
  @Test
  void multipleChoiceQuestionsAreSavedProperly() {
    Optional<Question> retrievedQuestion =
        questionRepository.findById(multipleChoiceQuestion.getId());
    assertTrue(retrievedQuestion.isPresent());
    assertDoesNotThrow(() -> (MultipleChoiceQuestion) retrievedQuestion.get());
    assertEquals(
        "Oslo",
        ((MultipleChoiceQuestion) retrievedQuestion.get())
            .getAlternatives()
            .iterator()
            .next()
            .getAlternativeText());
  }

  /**
   * This method tests that true or false questions are saved properly. It verifies that the method
   * returns the correct question.
   */
  @Test
  void trueOrFalseQuestionsAreSavedProperly() {
    TrueOrFalseQuestion question = new TrueOrFalseQuestion();
    question.setQuestionText("Is the sky blue?");
    question.setQuiz(quiz);
    question.setCorrectAnswer(true);
    questionRepository.save(question);

    Optional<Question> retrievedQuestion = questionRepository.findById(question.getId());
    assertTrue(retrievedQuestion.isPresent());
    assertDoesNotThrow(() -> (TrueOrFalseQuestion) retrievedQuestion.get());
    assertTrue(((TrueOrFalseQuestion) retrievedQuestion.get()).getCorrectAnswer());
  }
}
