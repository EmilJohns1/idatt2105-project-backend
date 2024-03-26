package com.idatt2105.backend.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

  public QuizAttempt addQuizAttempt(QuizAttemptDTO quizAttemptDTO) {
    if (quizAttemptDTO == null) {
      throw new IllegalArgumentException("Quiz attempt cannot be null");
    }
    QuizAttempt quizAttempt = parseQuizAttemptDTO(quizAttemptDTO);
    return quizAttemptRepository.save(quizAttempt);
  }

  public Collection<QuizAttempt> getAllAttemptsForUser(Long userId) {
    if (userId == null) {
      throw new InvalidIdException("User id cannot be null");
    }
    if (!userRepository.existsById(userId)) {
      throw new InvalidIdException("User with id " + userId + " not found");
    }
    return quizAttemptRepository.findByUserId(userId);
  }

  private QuizAttempt parseQuizAttemptDTO(QuizAttemptDTO quizAttemptDTO) {
    QuizAttempt quizAttempt = new QuizAttempt();
    quizAttempt.setAttemptTime(quizAttemptDTO.getAttemptTime());
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

  private QuestionAttempt parseQuestionAttemptDTO(QuestionAttemptDTO questionAttemptDTO) {
    QuestionAttempt questionAttempt = questionAttemptDTO.instantiateQuestionAttempt();
    questionAttempt.setQuestionText(questionAttemptDTO.getQuestionText());
    questionAttempt.setMediaUrl(questionAttemptDTO.getMediaUrl());
    questionAttempt.setCategory(questionAttemptDTO.getCategory());

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
