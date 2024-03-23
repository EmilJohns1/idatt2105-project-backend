package com.idatt2105.backend.service;

import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.util.UserNotFoundException;

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

    public Quiz save(Quiz quiz) {
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
          Optional<User> userOptional = userService.getUserById(userId);
          if (userOptional.isPresent()) {
              User user = userOptional.get();
              quiz.getUsers().add(user);
              user.getQuizzes().add(quiz);
              quizRepository.save(quiz);
              userService.save(user);
          } else {
              throw new UserNotFoundException("User with id " + userId + " not found");
          }
      } else {
          throw new IllegalStateException("Quiz with id " + quizId + " not found");
      }
    }

    public void removeUserFromQuiz(Long userId, Long quizId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            Optional<User> userOptional = userService.getUserById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                quiz.getUsers().remove(user);
                user.getQuizzes().remove(quiz);
                quizRepository.save(quiz);
                userService.save(user);
            } else {
                throw new UserNotFoundException("User with id " + userId + " not found");
            }
        } else {
            throw new IllegalStateException("Quiz with id " + quizId + " not found");
        }
    }

    public void editUsersInQuiz(Long userId, Long quizId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            Optional<User> userOptional = userService.getUserById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (quiz.getUsers().contains(user)) {
                    quiz.getUsers().remove(user);
                    user.getQuizzes().remove(quiz);
                } else {
                    quiz.getUsers().add(user);
                    user.getQuizzes().add(quiz);
                }
                quizRepository.save(quiz);
                userService.save(user);
            } else {
                throw new UserNotFoundException("User with id " + userId + " not found");
            }
        } else {
            throw new IllegalStateException("Quiz with id " + quizId + " not found");
        }
    }

    public Optional<Quiz> getQuizByTitle(String title) {
        return quizRepository.findByTitle(title);
    }
}
