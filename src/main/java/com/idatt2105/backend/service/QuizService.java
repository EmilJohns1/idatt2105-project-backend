package com.idatt2105.backend.service;

import com.idatt2105.backend.util.UserNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserService userService;

    @Autowired
    public QuizService(QuizRepository quizRepository, UserService userService) {
        this.quizRepository = quizRepository;
        this.userService = userService;
    }

    public List<QuizDTO> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<QuizDTO> getQuizById(Long id) {
        return quizRepository.findById(id)
                .map(this::convertToDTO);
    }

    public QuizDTO save(Quiz quiz) {
        Quiz savedQuiz = quizRepository.save(quiz);
        return convertToDTO(savedQuiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public void updateQuiz(Long id, Quiz updatedQuiz) {
        Quiz existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));

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
          Optional<UserDTO> userOptional = userService.getUserById(userId);
          if (userOptional.isPresent()) {
              UserDTO userDTO = userOptional.get();
              User user = new User();
              user.setId(userDTO.getId());
              user.setUsername(userDTO.getUsername());
              quiz.getUsers().add(user);
              quizRepository.save(quiz);
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
            Optional<UserDTO> userOptional = userService.getUserById(userId);
            if (userOptional.isPresent()) {
                UserDTO userDTO = userOptional.get();
                User user = new User();
                user.setId(userDTO.getId());
                user.setUsername(userDTO.getUsername());
                quiz.getUsers().remove(user);
                quizRepository.save(quiz);
            } else {
                throw new UserNotFoundException("User with id " + userId + " not found");
            }
        } else {
            throw new IllegalStateException("Quiz with id " + quizId + " not found");
        }
    }

    public Optional<QuizDTO> getQuizByTitle(String title) {
        return quizRepository.findByTitle(title)
                .map(this::convertToDTO);
    }

    private QuizDTO convertToDTO(Quiz quiz) {
        Set<UserDTO> userDTOs = quiz.getUsers().stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), Collections.emptyList()))
                .collect(Collectors.toSet());
        return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getDescription(), userDTOs);
    }
}
