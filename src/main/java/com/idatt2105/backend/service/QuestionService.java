package com.idatt2105.backend.service;

import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.model.QuestionDTO;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.repositories.QuizRepository;
import com.idatt2105.backend.repository.QuestionRepository;
import com.idatt2105.backend.util.QuizNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class QuestionService {
  private QuestionRepository questionRepository;
  private QuizRepository quizRepository;

  @Autowired
  public QuestionService(QuestionRepository questionRepository, QuizRepository quizRepository) {
    this.questionRepository = questionRepository;
    this.quizRepository = quizRepository;
  }

  /**
   * Adds a question to the quiz with the given id.
   *
   * @param questionDTO (QuestionDTO) Data transfer object for the question.
   * @return (Question) The added question.
   * @throws QuizNotFoundException if the quiz with the given id is not found.
   */
  public Question addQuestion(@Validated @NotNull QuestionDTO questionDTO) {
    Quiz quiz = quizRepository.findById(questionDTO.getQuizId())
        .orElseThrow(() -> new QuizNotFoundException("Quiz with id " + questionDTO.getQuizId() + " not found"));
    Question question = questionDTO.instantiateQuestion();
    question.setQuiz(quiz);
    question.setQuestionText(questionDTO.getQuestionText());
    question.setMediaUrl(questionDTO.getMediaUrl());
    question.setCategory(questionDTO.getCategory());
    question.setTags(questionDTO.getTags());
    return questionRepository.save(question);
  }

  public void deleteQuestion(@NotNull Question question) {
    questionRepository.delete(question);
  }
}
