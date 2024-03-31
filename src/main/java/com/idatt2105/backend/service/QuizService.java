package com.idatt2105.backend.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.Category;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.Tag;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.CategoryRepository;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.TagRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.InvalidIdException;

@Service
public class QuizService {

  private final QuizRepository quizRepository;
  private final UserRepository userRepository;
  private final TagRepository tagRepository;
  private final CategoryRepository categoryRepository;

  @Autowired
  public QuizService(
      QuizRepository quizRepository,
      UserRepository userRepository,
      TagRepository tagRepository,
      CategoryRepository categoryRepository) {
    this.quizRepository = quizRepository;
    this.userRepository = userRepository;
    this.tagRepository = tagRepository;
    this.categoryRepository = categoryRepository;
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

  public QuizDTO save(QuizDTO quizDTO) {
    if (quizDTO == null) {
      throw new IllegalArgumentException("Quiz parameter cannot be null.");
    }
    Category category = findCategoryByName(quizDTO.getCategoryName());
    Quiz quiz = quizDTO.toEntity();
    quiz.setCreationDate(LocalDateTime.now());
    quiz.setLastModifiedDate(LocalDateTime.now());
    quiz.setCategory(category);
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

    // Only update fields that are included in the request
    Optional.ofNullable(updatedQuiz.getTitle()).ifPresent(existingQuiz::setTitle);
    Optional.ofNullable(updatedQuiz.getDescription()).ifPresent(existingQuiz::setDescription);
    Optional.ofNullable(updatedQuiz.getQuizPictureUrl()).ifPresent(existingQuiz::setQuizPictureUrl);
    Optional.ofNullable(updatedQuiz.getCategoryName())
        .ifPresent(
            categoryName -> {
              Category category = findCategoryByName(categoryName);
              existingQuiz.setCategory(category);
            });
    Optional.ofNullable(updatedQuiz.isPublic()).ifPresent(existingQuiz::setPublic);
    Optional.ofNullable(updatedQuiz.isRandomizedOrder())
        .ifPresent(existingQuiz::setRandomizedOrder);

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

    if (foundQuiz.getAuthorId() == null) {
      foundQuiz.setAuthorId(user.getId());
    }

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

  public QuizDTO updateTags(Long quizId, List<Tag> updatedTags) {
    if (quizId == null) {
      throw new IllegalArgumentException("Quiz id parameter cannot be null.");
    }
    if (updatedTags == null) {
      throw new IllegalArgumentException("Updated tags parameter cannot be null.");
    }

    Quiz quiz = findQuiz(quizId);

    // Find tags to add
    List<Tag> tagsToAdd =
        updatedTags.stream()
            .filter(tag -> !quiz.getTags().contains(tag))
            .collect(Collectors.toList());

    // Find tags to remove
    List<Tag> tagsToRemove =
        quiz.getTags().stream()
            .filter(tag -> !updatedTags.contains(tag))
            .collect(Collectors.toList());

    // Save new tags if they don't exist, and add them to the quiz
    List<Tag> savedTags = new ArrayList<>();
    tagsToAdd.forEach(
        tag -> {
          if (tagRepository.existsByTagName(tag.getTagName())) {
            savedTags.add(tagRepository.findByTagName(tag.getTagName()).get());
          } else {
            tag.setId(null); // Avoiding conflicts with existing tags
            savedTags.add(tagRepository.save(tag));
          }
        });
    quiz.addTags(savedTags);

    // Remove tags not present in the updatedTags list
    quiz.removeTags(tagsToRemove);

    Quiz savedQuiz = quizRepository.save(quiz);
    return new QuizDTO(savedQuiz);
  }

  public List<QuizDTO> getQuizzesByTag(String tag) {
    if (tag == null || tag.isEmpty()) {
      throw new IllegalArgumentException("Tag parameter cannot be null or empty.");
    }
    Optional<Tag> foundTag = tagRepository.findByTagName(tag);
    if (foundTag.isEmpty()) {
      return new ArrayList<>();
    }
    List<Quiz> quizzes = quizRepository.findByTagsContains(foundTag.get());
    return quizzes.stream().map(QuizDTO::new).toList();
  }

  public List<Tag> getAllTags() {
    return tagRepository.findAll();
  }

  public Category createCategory(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category parameter cannot be null.");
    }
    if (categoryRepository.existsByName(category.getName())) {
      throw new IllegalArgumentException(
          "Category with name " + category.getName() + " already exists.");
    }
    category.setId(null); // Avoids conflicts with existing categories

    return categoryRepository.save(category);
  }

  public List<QuizDTO> getQuizzesByCategory(String categoryName) {
    if (categoryName == null || categoryName.isEmpty()) {
      throw new IllegalArgumentException("Category parameter cannot be null or empty.");
    }

    Category foundCategory = findCategoryByName(categoryName);
    if (foundCategory == null) {
      return Collections.emptyList();
    }

    List<Quiz> quizzes = quizRepository.findByCategory(foundCategory);
    return quizzes.stream().map(QuizDTO::new).collect(Collectors.toList());
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
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

  private Category findCategoryByName(String name) {
    return categoryRepository
        .findByName(name)
        .orElseThrow(() -> new InvalidIdException("Category with name " + name + " not found"));
  }
}
