package com.idatt2105.backend.service;

import com.idatt2105.backend.dto.QuizAttemptDTO;
import com.idatt2105.backend.model.QuizAttempt;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttemptService {
  private final UserRepository userRepository;
  private final QuizRepository quizRepository;

  @Autowired
  public AttemptService(UserRepository userRepository, QuizRepository quizRepository) {
      this.userRepository = userRepository;
      this.quizRepository = quizRepository;
  }

  public QuizAttempt addQuizAttempt(QuizAttemptDTO quizAttemptDTO) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
