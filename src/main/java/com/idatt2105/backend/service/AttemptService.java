package com.idatt2105.backend.service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.dto.QuestionAttemptDTO;
import com.idatt2105.backend.dto.QuizAttemptDTO;
import com.idatt2105.backend.model.MultipleChoiceQuestionAttempt;
import com.idatt2105.backend.model.QuestionAttempt;
import com.idatt2105.backend.model.QuizAttempt;
import com.idatt2105.backend.model.TrueOrFalseQuestionAttempt;
import com.idatt2105.backend.repository.QuizAttemptRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.InvalidIdException;
import com.idatt2105.backend.util.InvalidQuestionTypeException;

/** Service for handling quiz attempts. */
@Service
public class AttemptService {
  private final UserRepository userRepository;
  private final QuizAttemptRepository quizAttemptRepository;

  @Autowired
  public AttemptService(
      UserRepository userRepository, QuizAttemptRepository quizAttemptRepository) {
    this.userRepository = userRepository;
    this.quizAttemptRepository = quizAttemptRepository;
  }

  /**
   * Adds a quiz attempt to the database.
   *
   * @param quizAttemptDTO DTO containing the quiz attempt information.
   * @throws IllegalArgumentException If the quiz attempt is null.
   * @return The created quiz attempt.
   */
  public QuizAttempt addQuizAttempt(QuizAttemptDTO quizAttemptDTO) {
    if (quizAttemptDTO == null) {
      throw new IllegalArgumentException("Quiz attempt cannot be null");
    }
    QuizAttempt quizAttempt = parseQuizAttemptDTO(quizAttemptDTO);
    return quizAttemptRepository.save(quizAttempt);
  }

  /**
   * Gets all quiz attempts for a user.
   *
   * @param userId The id of the user.
   * @throws InvalidIdException If the user id is invalid.
   * @return A collection of all quiz attempts for the user.
   */
  public Page<QuizAttempt> getAllAttemptsForUser(Long userId, Pageable pageable) {
    if (userId == null) {
      throw new InvalidIdException("User id cannot be null");
    }
    if (!userRepository.existsById(userId)) {
      throw new InvalidIdException("User with id " + userId + " not found");
    }
    return quizAttemptRepository.findByUserId(userId, pageable);
  }

  /**
   * Gets a quiz attempt by its id.
   *
   * @param id The id of the quiz attempt.
   * @throws InvalidIdException If the id is invalid.
   * @return The quiz attempt with the given id.
   */
  public QuizAttempt getAttemptById(Long id) {
    if (id == null) {
      throw new InvalidIdException("Attempt id cannot be null");
    }
    return quizAttemptRepository
        .findById(id)
        .orElseThrow(() -> new InvalidIdException("Attempt with id " + id + " not found"));
  }

  /**
   * Parses a QuizAttemptDTO to a QuizAttempt.
   *
   * @param quizAttemptDTO DTO to parse.
   * @throws InvalidIdException If the user id is invalid.
   * @return The parsed QuizAttempt.
   */
  private QuizAttempt parseQuizAttemptDTO(QuizAttemptDTO quizAttemptDTO) {
    QuizAttempt quizAttempt = new QuizAttempt();
    quizAttempt.setTitle(quizAttemptDTO.getTitle());
    quizAttempt.setAttemptTime(LocalDateTime.now());
    quizAttempt.setScore(quizAttemptDTO.getScore());
    quizAttempt.setUser(
        userRepository
            .findById(quizAttemptDTO.getUserId())
            .orElseThrow(
                () ->
                    new InvalidIdException(
                        "User with id " + quizAttemptDTO.getUserId() + " not found")));
    quizAttempt.setQuizId(quizAttemptDTO.getQuizId());
    quizAttempt.setQuestionAttempts(
        quizAttemptDTO.getQuestionAttempts().stream()
            .map(this::parseQuestionAttemptDTO)
            .map(
                questionAttempt -> {
                  questionAttempt.setQuizAttempt(quizAttempt);
                  return questionAttempt;
                })
            .collect(Collectors.toSet()));
    return quizAttempt;
  }

  /**
   * Parses a QuestionAttemptDTO to a QuestionAttempt.
   *
   * @param questionAttemptDTO DTO to parse.
   * @throws InvalidQuestionTypeException If the question type is invalid.
   * @return The parsed QuestionAttempt.
   */
  private QuestionAttempt parseQuestionAttemptDTO(QuestionAttemptDTO questionAttemptDTO) {
    QuestionAttempt questionAttempt = questionAttemptDTO.instantiateQuestionAttempt();
    questionAttempt.extractFromDTO(questionAttemptDTO);

    // Different information is stored based on the question type
    switch (questionAttemptDTO.getType()) {
      case MULTIPLE_CHOICE -> {
        MultipleChoiceQuestionAttempt multipleChoiceQuestionAttempt =
            (MultipleChoiceQuestionAttempt) questionAttempt;
        questionAttemptDTO.getAlternatives().forEach(multipleChoiceQuestionAttempt::addAlternative);
        return multipleChoiceQuestionAttempt;
      }
      case TRUE_OR_FALSE -> {
        TrueOrFalseQuestionAttempt trueOrFalseQuestionAttempt =
            (TrueOrFalseQuestionAttempt) questionAttempt;
        trueOrFalseQuestionAttempt.setUserAnswer(questionAttemptDTO.getUserAnswer());
        trueOrFalseQuestionAttempt.setCorrectAnswer(questionAttemptDTO.getCorrectAnswer());
        return trueOrFalseQuestionAttempt;
      }
      default ->
          throw new InvalidQuestionTypeException(
              "Invalid question type: " + questionAttemptDTO.getType());
    }
  }
}
