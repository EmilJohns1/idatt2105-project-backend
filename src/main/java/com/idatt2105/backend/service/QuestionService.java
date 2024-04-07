package com.idatt2105.backend.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.dto.AlternativeDTO;
import com.idatt2105.backend.dto.QuestionDTO;
import com.idatt2105.backend.model.Alternative;
import com.idatt2105.backend.model.MultipleChoiceQuestion;
import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.TrueOrFalseQuestion;
import com.idatt2105.backend.repository.AlternativeRepository;
import com.idatt2105.backend.repository.QuestionRepository;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.util.InvalidIdException;

import jakarta.transaction.Transactional;

/** Service for handling operations related to questions. */
@Service
public class QuestionService {
  private final QuestionRepository questionRepository;
  private final QuizRepository quizRepository;
  private final AlternativeRepository alternativeRepository;

  @Autowired
  public QuestionService(
      QuestionRepository questionRepository,
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
    question.extractFromDTO(questionDTO);
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
    question.extractFromDTO(questionDTO);
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

  /**
   * Updates the alternatives for a multiple choice question.
   *
   * @param questionId (Long) The id of the question to update alternatives for.
   * @param alternativeDTOs (List<AlternativeDTO>) The list of alternative DTOs containing the new
   *     alternatives.
   * @return (List<AlternativeDTO>) The updated list of alternatives.
   * @throws InvalidIdException if the question with the given id is not found or is not a multiple
   *     choice question.
   */
  @Transactional
  public Set<Alternative> updateAlternatives(
      Long questionId, List<AlternativeDTO> alternativeDTOs) {
    if (questionId == null) {
      throw new IllegalArgumentException("Question ID cannot be null.");
    }
    if (alternativeDTOs == null) {
      throw new IllegalArgumentException("Alternative DTOs cannot be null.");
    }

    // Get the question by ID
    Question question =
        questionRepository
            .findById(questionId)
            .orElseThrow(
                () -> new InvalidIdException("Question with id " + questionId + " not found."));

    // Check if the question is a multiple choice question
    if (!(question instanceof MultipleChoiceQuestion)) {
      throw new InvalidIdException(
          "Question with id " + questionId + " is not a multiple choice question.");
    }

    // Cast the question to a MultipleChoiceQuestion
    MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;

    // Update or add alternatives
    for (AlternativeDTO alternativeDTO : alternativeDTOs) {
      if (alternativeDTO.getId() != null) {
        // Update existing alternative
        Alternative existingAlternative =
            mcQuestion.getAlternatives().stream()
                .filter(alt -> alt.getId().equals(alternativeDTO.getId()))
                .findFirst()
                .orElseThrow(
                    () ->
                        new InvalidIdException(
                            "Alternative with id " + alternativeDTO.getId() + " not found."));
        existingAlternative.setAlternativeText(alternativeDTO.getAlternativeText());
        existingAlternative.setCorrect(alternativeDTO.isCorrect());
      } else {
        // Add new alternative
        Alternative newAlternative = new Alternative();
        newAlternative.setAlternativeText(alternativeDTO.getAlternativeText());
        newAlternative.setCorrect(alternativeDTO.isCorrect());
        newAlternative.setQuestion(mcQuestion); // Set the question association
        mcQuestion
            .getAlternatives()
            .add(newAlternative); // Add the new alternative to the question's set of alternatives
        // Save the new alternative explicitly
        alternativeRepository.save(newAlternative);
      }
    }

    // Remove alternatives not present in the list
    mcQuestion
        .getAlternatives()
        .removeIf(
            existingAlternative -> {
              Long existingAltId = existingAlternative.getId();
              return alternativeDTOs.stream()
                  .noneMatch(
                      alternativeDTO ->
                          alternativeDTO.getId() != null
                              && existingAltId != null
                              && alternativeDTO.getId().equals(existingAltId));
            });

    // Save the question with updated alternatives
    return questionRepository.save(mcQuestion).getAlternatives();
  }
}
