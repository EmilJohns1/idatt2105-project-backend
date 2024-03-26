package com.idatt2105.backend.service;

import java.util.List;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.idatt2105.backend.dto.AlternativeDTO;
import com.idatt2105.backend.dto.QuestionDTO;
import com.idatt2105.backend.model.Alternative;
import com.idatt2105.backend.model.MultipleChoiceQuestion;
import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.Tag;
import com.idatt2105.backend.model.TrueOrFalseQuestion;
import com.idatt2105.backend.repository.AlternativeRepository;
import com.idatt2105.backend.repository.QuestionRepository;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.TagRepository;
import com.idatt2105.backend.util.InvalidIdException;

import jakarta.validation.constraints.NotNull;

/** Service for handling operations related to questions. */
@Service
public class QuestionService {
  private final QuestionRepository questionRepository;
  private final QuizRepository quizRepository;
  private final AlternativeRepository alternativeRepository;
  private final TagRepository tagRepository;

  /**
   * Constructor for the QuestionService class.
   *
   * @param questionRepository (QuestionRepository) Repository for handling operations on questions.
   * @param quizRepository (QuizRepository) Repository for handling operations on quizzes.
   */
  @Autowired
  public QuestionService(
      QuestionRepository questionRepository,
      QuizRepository quizRepository,
      AlternativeRepository alternativeRepository,
      TagRepository tagRepository) {
    this.questionRepository = questionRepository;
    this.quizRepository = quizRepository;
    this.alternativeRepository = alternativeRepository;
    this.tagRepository = tagRepository;
  }

  /**
   * Adds a question to the quiz with the given id.
   *
   * @param questionDTO (QuestionDTO) Data transfer object for the question.
   * @return (Question) The added question.
   * @throws InvalidIdException if the quiz with the given id is not found.
   */
  public Question addQuestion(QuestionDTO questionDTO) {
    if (questionDTO == null) {
      throw new IllegalArgumentException("Question parameter cannot be null.");
    }
    if (questionDTO.getQuizId() == null) {
      throw new InvalidIdException("Quiz id cannot be null.");
    }

    Quiz quiz =
        quizRepository
            .findById(questionDTO.getQuizId())
            .orElseThrow(
                () ->
                    new InvalidIdException(
                        "Quiz with id " + questionDTO.getQuizId() + " not found"));
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
  public Question getQuestionById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Id parameter cannot be null.");
    }

    return questionRepository
        .findById(id)
        .orElseThrow(() -> new InvalidIdException("Question with id " + id + " not found"));
  }

  /**
   * Deletes the question with the given id.
   *
   * @param id (Long) The id of the question to delete.
   * @throws InvalidIdException if the question with the given id is not found.
   */
  public void deleteQuestion(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Id parameter cannot be null.");
    }

    Question question = getQuestionById(id);
    questionRepository.delete(question);
  }

  /**
   * Updates the question with the given id.
   *
   * @param questionDTO (QuestionDTO) Data transfer object for the question.
   * @return (Question) The updated question.
   * @throws InvalidIdException if the question with the given id is not found.
   */
  public Question updateQuestion(QuestionDTO questionDTO) {
    if (questionDTO == null) {
      throw new IllegalArgumentException("Question parameter cannot be null.");
    }

    Question question = getQuestionById(questionDTO.getQuestionId());
    question.setQuestionText(questionDTO.getQuestionText());
    question.setMediaUrl(questionDTO.getMediaUrl());
    question.setCategory(questionDTO.getCategory());
    question.setTags(questionDTO.getTags());
    return questionRepository.save(question);
  }

  /**
   * Updates a true or false question.
   *
   * @param questionDTO (QuestionDTO) Data transfer object for the question.
   * @return (TrueOrFalseQuestion) The updated true or false question.
   */
  public TrueOrFalseQuestion updateTrueOrFalseQuestion(QuestionDTO questionDTO) {
    if (questionDTO == null) {
      throw new IllegalArgumentException("Question parameter cannot be null.");
    }
    if (questionDTO.isCorrect() == null) {
      throw new IllegalArgumentException("IsCorrect field must have a value.");
    }

    Question question = getQuestionById(questionDTO.getQuestionId());
    TrueOrFalseQuestion trueOrFalseQuestion;
    try {
      trueOrFalseQuestion = (TrueOrFalseQuestion) question;
    } catch (ClassCastException e) {
      throw new InvalidIdException(
          "Question with id " + questionDTO.getQuestionId() + " is not a true or false question");
    }
    trueOrFalseQuestion.setCorrectAnswer(questionDTO.isCorrect());
    return questionRepository.save(trueOrFalseQuestion);
  }

  /**
   * Gets all questions in the quiz with the given id.
   *
   * @param quizId (Long) The id of the quiz to get questions from.
   * @return (List&lt;Question&gt;) All questions in the quiz with the given id.
   */
  public List<Question> getQuestionsByQuizId(Long quizId) {
    if (quizId == null) {
      throw new IllegalArgumentException("Quiz id parameter cannot be null.");
    }
    return questionRepository.findQuestionsByQuizId(quizId);
  }

  /**
   * Adds an alternative to the question with the given id.
   *
   * @param alternativeDTO (AlternativeDTO) Data transfer object for the alternative.
   * @throws InvalidIdException if the question with the given id is not found or is not a multiple
   *     choice question.
   */
  public Alternative addAlternative(AlternativeDTO alternativeDTO) {
    if (alternativeDTO == null) {
      throw new IllegalArgumentException("Alternative parameter cannot be null.");
    }
    Question q = getQuestionById(alternativeDTO.getQuestionId());
    MultipleChoiceQuestion question;
    try {
      question = (MultipleChoiceQuestion) q;
    } catch (ClassCastException e) {
      throw new InvalidIdException(
          "Question with id "
              + alternativeDTO.getQuestionId()
              + " is not a multiple choice question");
    }
    Alternative alt = question.addAlternative(alternativeDTO);
    questionRepository.save(question);
    return alt;
  }

  /**
   * Deletes the alternative with the given id.
   *
   * @param id (Long) The id of the alternative to delete.
   */
  public void deleteAlternative(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Id parameter cannot be null.");
    }
    alternativeRepository.deleteById(id);
  }

  public Question addTags(QuestionDTO dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Question parameter cannot be null.");
    }

    Question question = getQuestionById(dto.getQuestionId());
    dto.getTags().stream().filter(Objects::nonNull).forEach(tag -> {
      tag.setId(null);
      if (Boolean.FALSE.equals(tagRepository.existsByTagName(tag.getTagName()))) {
        tagRepository.save(tag);
      }
    });
    question.addTags(dto.getTags());
    return questionRepository.save(question);
  }

  public Question deleteTags(QuestionDTO dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Question parameter cannot be null.");
    }

    Question question = getQuestionById(dto.getQuestionId());
    question.removeTags(dto.getTags());
    return questionRepository.save(question);
  }
}
