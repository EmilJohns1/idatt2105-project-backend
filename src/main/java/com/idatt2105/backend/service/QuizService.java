package com.idatt2105.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.Tag;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.TagRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.InvalidIdException;

@Service
public class QuizService {

  private final QuizRepository quizRepository;
  private final UserService userService;
  private final UserRepository userRepository;
  private final TagRepository tagRepository;

  @Autowired
  public QuizService(
      QuizRepository quizRepository,
      UserService userService,
      UserRepository userRepository,
      TagRepository tagRepository) {
    this.quizRepository = quizRepository;
    this.userService = userService;
    this.userRepository = userRepository;
    this.tagRepository = tagRepository;
  }

  public List<QuizDTO> getAllQuizzes() {
    List<Quiz> quizzes = quizRepository.findAll();
    return quizzes.stream().map(QuizDTO::new).toList();
  }

  public QuizDTO getQuizById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Id parameter cannot be null.");
    }
    Quiz quiz = findQuiz(id);
    return new QuizDTO(quiz);
  }

  public QuizDTO save(Quiz quiz) {
    if (quiz == null) {
      throw new IllegalArgumentException("Quiz parameter cannot be null.");
    }
    quiz.setCreationDate(LocalDateTime.now());
    quiz.setLastModifiedDate(LocalDateTime.now());
    Quiz savedQuiz = quizRepository.save(quiz);
    return new QuizDTO(savedQuiz);
  }

  public void deleteQuiz(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Id parameter cannot be null.");
    }
    quizRepository.deleteById(id);
  }

  public void updateQuiz(Long id, QuizDTO updatedQuiz) {
    if (id == null) {
      throw new IllegalArgumentException("Id parameter cannot be null.");
    }
    if (updatedQuiz == null) {
      throw new IllegalArgumentException("Quiz parameter cannot be null.");
    }

    Quiz existingQuiz = findQuiz(id);

    existingQuiz.setTitle(updatedQuiz.getTitle());
    existingQuiz.setDescription(updatedQuiz.getDescription());
    existingQuiz.setLastModifiedDate(LocalDateTime.now());

    quizRepository.save(existingQuiz);
  }

  public void addUserToQuiz(Long userId, Long quizId) {
    if (userId == null) {
      throw new IllegalArgumentException("User id parameter cannot be null.");
    }
    if (quizId == null) {
      throw new IllegalArgumentException("Quiz id parameter cannot be null.");
    }

    Quiz foundQuiz = findQuiz(quizId);
    User user = findUser(userId);
    foundQuiz.getUsers().add(user);
    user.getQuizzes().add(foundQuiz);
    quizRepository.save(foundQuiz);
  }

  public void removeUserFromQuiz(Long userId, Long quizId) {
    if (userId == null) {
      throw new IllegalArgumentException("User id parameter cannot be null.");
    }
    if (quizId == null) {
      throw new IllegalArgumentException("Quiz id parameter cannot be null.");
    }
    Quiz foundQuiz = findQuiz(quizId);
    User user = findUser(userId);
    foundQuiz.getUsers().remove(user);
    user.getQuizzes().remove(foundQuiz);
    quizRepository.save(foundQuiz);
  }

  public QuizDTO getQuizByTitle(String title) {
    if (title == null) {
      throw new IllegalArgumentException("Title parameter cannot be null.");
    }

    Optional<Quiz> quiz = quizRepository.findByTitle(title);
    if (quiz.isEmpty()) {
      throw new InvalidIdException("Quiz with title " + title + " not found");
    } else {
      return new QuizDTO(quiz.get());
    }
  }

  public Set<UserDTO> getUsersByQuizId(Long quizId) {
    if (quizId == null) {
      throw new IllegalArgumentException("Quiz id parameter cannot be null.");
    }

    Quiz quiz = findQuiz(quizId);
    return quiz.getUsers().stream()
        .map(user -> new UserDTO(user.getId(), user.getUsername()))
        .collect(Collectors.toSet());
  }

  public QuizDTO addTags(QuizDTO dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Question parameter cannot be null.");
    }

    Quiz quiz = findQuiz(dto.getId());

    // Save tags if they do not exist, get them if they do
    List<Tag> savedTags = new ArrayList<>();
    dto.getTags().stream()
        .filter(Objects::nonNull)
        .forEach(
            tag -> {
              if (tagRepository.existsByTagName(tag.getTagName())) {
                savedTags.add(tagRepository.findByTagName(tag.getTagName()).get());
              } else {
                tag.setId(null); // Avoiding conflicts with existing tags
                savedTags.add(tagRepository.save(tag));
              }
            });
    quiz.addTags(savedTags);
    Quiz savedQuiz = quizRepository.save(quiz);
    return new QuizDTO(savedQuiz);
  }

  public QuizDTO deleteTags(QuizDTO dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Question parameter cannot be null.");
    }

    Quiz quiz = findQuiz(dto.getId());
    quiz.removeTags(dto.getTags());
    Quiz savedQuiz = quizRepository.save(quiz);
    return new QuizDTO(savedQuiz);
  }

  private Quiz findQuiz(Long id) {
    return quizRepository
        .findById(id)
        .orElseThrow(() -> new InvalidIdException("Quiz with id " + id + " not found"));
  }

  private User findUser(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new InvalidIdException("User with id " + id + " not found"));
  }
}
