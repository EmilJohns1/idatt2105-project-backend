package com.idatt2105.backend.service;

import com.idatt2105.backend.model.Alternative;
import com.idatt2105.backend.model.AlternativeDTO;
import com.idatt2105.backend.model.MultipleChoiceQuestion;
import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.model.QuestionDTO;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.repositories.QuizRepository;
import com.idatt2105.backend.repository.AlternativeRepository;
import com.idatt2105.backend.repository.QuestionRepository;
import com.idatt2105.backend.util.InvalidIdException;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Service for handling operations related to questions.
 */
@Service
public class QuestionService {
  private QuestionRepository questionRepository;
  private QuizRepository quizRepository;
  private AlternativeRepository alternativeRepository;

  /**
   * Constructor for the QuestionService class.
   *
   * @param questionRepository (QuestionRepository) Repository for handling operations on questions.
   * @param quizRepository (QuizRepository) Repository for handling operations on quizzes.
   */
  @Autowired
  public QuestionService(QuestionRepository questionRepository,
                         QuizRepository quizRepository,
                         AlternativeRepository alternativeRepository) {
    this.questionRepository = questionRepository;
    this.quizRepository = quizRepository;
    this.alternativeRepository = alternativeRepository;
  }

  /**
   * Adds a question to the quiz with the given id.
   *
   * @param questionDTO (QuestionDTO) Data transfer object for the question.
   * @return (Question) The added question.
   * @throws InvalidIdException if the quiz with the given id is not found.
   */
  public Question addQuestion(@Validated @NotNull QuestionDTO questionDTO) {
    Quiz quiz = quizRepository.findById(questionDTO.getQuizId())
        .orElseThrow(() -> new InvalidIdException("Quiz with id " + questionDTO.getQuizId() + " not found"));
    Question question = questionDTO.instantiateQuestion();
    question.setQuiz(quiz);
    question.setQuestionText(questionDTO.getQuestionText());
    question.setMediaUrl(questionDTO.getMediaUrl());
    question.setCategory(questionDTO.getCategory());
    question.setTags(questionDTO.getTags());
    return questionRepository.save(question);
  }

  /**
   * Gets the question with the given id.
   *
   * @param id (Long) The id of the question to get.
   * @return (Question) The question with the given id.
   * @throws InvalidIdException if the question with the given id is not found.
   */
  public Question getQuestionById(@NotNull Long id) {
    return questionRepository.findById(id)
        .orElseThrow(() -> new InvalidIdException("Question with id " + id + " not found"));
  }

  /**
   * Deletes the question with the given id.
   *
   * @param id (Long) The id of the question to delete.
   * @throws InvalidIdException if the question with the given id is not found.
   */
  public void deleteQuestion(@NotNull Long id) {
    Question question = getQuestionById(id);
    questionRepository.delete(question);
  }

  /**
   * Updates the question with the given id.
   *
   * @param id (Long) The id of the question to update.
   * @param questionDTO (QuestionDTO) Data transfer object for the question.
   * @return (Question) The updated question.
   * @throws InvalidIdException if the question with the given id is not found.
   */
  public Question updateQuestion(@NotNull Long id, @Validated @NotNull QuestionDTO questionDTO) {
    Question question = getQuestionById(id);
    question.setQuestionText(questionDTO.getQuestionText());
    question.setMediaUrl(questionDTO.getMediaUrl());
    question.setCategory(questionDTO.getCategory());
    question.setTags(questionDTO.getTags());
    return questionRepository.save(question);
  }

  /**
   * Gets all questions in the quiz with the given id.
   *
   * @param quizId (Long) The id of the quiz to get questions from.
   * @return (List&lt;Question&gt;) All questions in the quiz with the given id.
   */
  public List<Question> getQuestionsByQuizId(@NotNull Long quizId) {
    return questionRepository.findQuestionsByQuizId(quizId);
  }

  /**
   * Adds an alternative to the question with the given id.
   *
   * @param alternativeDTO (AlternativeDTO) Data transfer object for the alternative.
   * @throws InvalidIdException if the question with the given id is not found or is not a multiple choice question.
   */
  public void addAlternative(@Validated @NotNull AlternativeDTO alternativeDTO) {
    Question q = getQuestionById(alternativeDTO.getQuestionId());
    MultipleChoiceQuestion question;
    try {
      question = (MultipleChoiceQuestion) q;
    } catch (ClassCastException e) {
      throw new InvalidIdException("Question with id " + alternativeDTO.getQuestionId() + " is not a multiple choice question");
    }
    question.addAlternative(alternativeDTO);
    questionRepository.save(question);
  }

  /**
   * Deletes the alternative with the given id.
   *
   * @param id (Long) The id of the alternative to delete.
   */
  public void deleteAlternative(@Validated @NotNull Long id) {
    alternativeRepository.deleteById(id);
  }
}