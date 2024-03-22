package com.idatt2105.backend.service;

import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserService userService;

    @Autowired
    public QuizService(QuizRepository quizRepository, UserService userService) {
        this.quizRepository = quizRepository;
        this.userService = userService;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public void updateQuiz(Long id, Quiz updatedQuiz) {
      Quiz existingQuiz = quizRepository.findById(id)
              .orElseThrow(() -> new IllegalStateException("Quiz with id " + id + " not found"));
  
      existingQuiz.setTitle(updatedQuiz.getTitle());
      existingQuiz.setDescription(updatedQuiz.getDescription());
      existingQuiz.setUsers(updatedQuiz.getUsers());
      // add questions here

      quizRepository.save(existingQuiz);
    }

    public void addUserToQuiz(Long userId, Long quizId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            User user = userService.getUserById(userId);
            if (user != null) {
                quiz.getUsers().add(user);
                quizRepository.save(quiz);
            } else {
                // Handle user not found
            }
        } else {
            // Handle quiz not found
        }
    }

    public void removeUserFromQuiz(Long userId, Long quizId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            User user = userService.getUserById(userId);
            if (user != null) {
                quiz.getUsers().remove(user);
                quizRepository.save(quiz);
            } else {
                // Handle user not found
            }
        } else {
            // Handle quiz not found
        }
    }

    public void editUsersInQuiz(Long userId, Long quizId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            User user = userService.getUserById(userId);
            if (user != null) {
                if (quiz.getUsers().contains(user)) {
                    quiz.getUsers().remove(user);
                } else {
                    quiz.getUsers().add(user);
                }
                quizRepository.save(quiz);
            } else {
                // Handle user not found
            }
        } else {
            // Handle quiz not found
        }
    }

    public Optional<Quiz> getQuizByTitle(String title) {
        return quizRepository.findByTitle(title);
    }
}
