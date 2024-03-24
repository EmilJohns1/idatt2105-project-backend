package com.idatt2105.backend.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.UserNotFoundException;

@Service
public class QuizService {

  private final QuizRepository quizRepository;
  private final UserService userService;
  private final UserRepository userRepository;

  @Autowired
  public QuizService(
      QuizRepository quizRepository, UserService userService, UserRepository userRepository) {
    this.quizRepository = quizRepository;
    this.userService = userService;
    this.userRepository = userRepository;
  }

  public List<QuizDTO> getAllQuizzes() {
    List<Quiz> quizzes = quizRepository.findAll();
    return quizzes.stream()
        .map(
            quiz -> {
              QuizDTO quizDTO = convertToDTO(quiz);
              quizDTO.setCreationDate(quiz.getCreationDate());
              quizDTO.setLastModifiedDate(quiz.getLastModifiedDate());
              return quizDTO;
            })
        .collect(Collectors.toList());
  }

  public Optional<QuizDTO> getQuizById(Long id) {
    Optional<Quiz> quizOptional = quizRepository.findById(id);
    return quizOptional.map(
        quiz -> {
          QuizDTO quizDTO = convertToDTO(quiz);
          quizDTO.setCreationDate(quiz.getCreationDate());
          quizDTO.setLastModifiedDate(quiz.getLastModifiedDate());
          return quizDTO;
        });
  }

  public QuizDTO save(Quiz quiz) {
    Quiz savedQuiz = quizRepository.save(quiz);
    return convertToDTO(savedQuiz);
  }

  public void deleteQuiz(Long id) {
    quizRepository.deleteById(id);
  }

  public void updateQuiz(Long id, QuizDTO updatedQuiz) {
    Quiz existingQuiz =
        quizRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));

    existingQuiz.setTitle(updatedQuiz.getTitle());
    existingQuiz.setDescription(updatedQuiz.getDescription());
    existingQuiz.setLastModifiedDate(LocalDateTime.now());
    // add questions here

    quizRepository.save(existingQuiz);
  }

  public void addUserToQuiz(Long userId, Long quizId) {
    Optional<Quiz> quizOptional = quizRepository.findById(quizId);
    if (quizOptional.isPresent()) {
      Quiz quiz = quizOptional.get();
      Optional<UserDTO> userOptional = userService.getUserByIdDTO(userId);
      if (userOptional.isPresent()) {
        UserDTO userDTO = userOptional.get();
        User user = userRepository.findByUsername(userDTO.getUsername()).get();
        quiz.getUsers().add(user);
        quizRepository.save(quiz);

        user.getQuizzes().add(quiz);
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
      Optional<UserDTO> userOptional = userService.getUserByIdDTO(userId);
      if (userOptional.isPresent()) {
        UserDTO userDTO = userOptional.get();
        User user = userRepository.findByUsername(userDTO.getUsername()).get();
        quiz.getUsers().remove(user);
        quizRepository.save(quiz);

        user.getQuizzes().remove(quiz);
        userService.save(user);
      } else {
        throw new UserNotFoundException("User with id " + userId + " not found");
      }
    } else {
      throw new IllegalStateException("Quiz with id " + quizId + " not found");
    }
  }

  public Optional<QuizDTO> getQuizByTitle(String title) {
    return quizRepository.findByTitle(title).map(this::convertToDTO);
  }

  private QuizDTO convertToDTO(Quiz quiz) {
    Set<UserDTO> userDTOs =
        quiz.getUsers().stream()
            .map(user -> new UserDTO(user.getId(), user.getUsername(), Collections.emptyList()))
            .collect(Collectors.toSet());
    return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getDescription(), userDTOs);
  }

  public Set<UserDTO> getUsersByQuizId(Long quizId) {
    Quiz quiz =
        quizRepository
            .findById(quizId)
            .orElseThrow(() -> new IllegalStateException("Quiz with id " + quizId + " not found"));
    Set<UserDTO> userDTOs =
        quiz.getUsers().stream()
            .map(user -> new UserDTO(user.getId(), user.getUsername()))
            .collect(Collectors.toSet());
    return userDTOs;
  }
}
