package com.idatt2105.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.dto.CommentDTO;
import com.idatt2105.backend.model.Comment;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.CommentRepository;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.InvalidIdException;

/** Service for handling comments. */
@Service
public class CommentService {

  private final CommentRepository commentRepository;
  private final QuizRepository quizRepository;
  private final UserRepository userRepository;

  @Autowired
  public CommentService(
      CommentRepository commentRepository,
      QuizRepository quizRepository,
      UserRepository userRepository) {
    this.commentRepository = commentRepository;
    this.quizRepository = quizRepository;
    this.userRepository = userRepository;
  }

  /**
   * Get all comments.
   *
   * @return List<CommentDTO> containing all comments.
   */
  public List<CommentDTO> getAllComments() {
    return commentRepository.findAll().stream().map(CommentDTO::new).toList();
  }

  /**
   * Get a comment by id.
   *
   * @param id The id of the comment.
   * @throws InvalidIdException If the id is null.
   * @return CommentDTO containing the comment.
   */
  public CommentDTO getCommentById(Long id) {
    if (id == null) {
      throw new InvalidIdException("Id cannot be null");
    }
    Comment comment = findComment(id);
    return new CommentDTO(comment);
  }

  /**
   * Get all comments for a quiz.
   *
   * @param quizId The id of the quiz.
   * @throws IllegalArgumentException If the quiz id is null.
   * @return List<CommentDTO> containing all comments for the quiz.
   */
  public List<CommentDTO> getCommentsByQuizId(Long quizId) {
    if (quizId == null) {
      throw new IllegalArgumentException("Quiz id cannot be null");
    }
    Optional<Quiz> quiz = quizRepository.findById(quizId);
    if (quiz.isEmpty()) {
      throw new InvalidIdException("Quiz not found with id: " + quizId);
    }

    List<Comment> comments = commentRepository.findByQuiz(quiz.get());
    return comments.stream().map(CommentDTO::new).toList();
  }

  /**
   * Get all comments for a user.
   *
   * @param userId The id of the user.
   * @throws IllegalArgumentException If the user id is null.
   * @return List<CommentDTO> containing all comments for the user.
   */
  public List<CommentDTO> getCommentsByUserId(Long userId) {
    if (userId == null) {
      throw new IllegalArgumentException("User id cannot be null");
    }

    User user = findUser(userId);
    List<Comment> comments = commentRepository.findByUser(user);
    return comments.stream().map(CommentDTO::new).toList();
  }

  /**
   * Save a comment.
   *
   * @param commentDTO The comment to save.
   * @throws IllegalArgumentException If the comment is null.
   * @return CommentDTO containing the saved comment.
   */
  public CommentDTO saveComment(CommentDTO commentDTO) {
    if (commentDTO == null) {
      throw new IllegalArgumentException("CommentDTO cannot be null");
    }
    Quiz quiz = findQuiz(commentDTO.getQuizId());
    User user = findUser(commentDTO.getUserId());

    Comment comment = new Comment();
    comment.setContent(commentDTO.getContent());
    comment.setUser(user);
    comment.setQuiz(quiz);
    comment.setCreationDate(LocalDateTime.now());
    comment.setLastModifiedDate(LocalDateTime.now());

    Comment savedComment = commentRepository.save(comment);
    return new CommentDTO(savedComment);
  }

  /**
   * Update a comment.
   *
   * @param id The id of the comment.
   * @param updatedComment The updated comment.
   * @throws InvalidIdException If the comment is not found.
   */
  public void updateComment(Long id, CommentDTO updatedComment) {
    if (id == null) {
      throw new IllegalArgumentException("Id cannot be null");
    }
    if (updatedComment == null) {
      throw new IllegalArgumentException("CommentDTO cannot be null");
    }
    Comment comment = findComment(id);
    comment.setContent(updatedComment.getContent());
    comment.setLastModifiedDate(LocalDateTime.now());

    commentRepository.save(comment);
  }

  /**
   * Delete a comment.
   *
   * @param id The id of the comment.
   * @throws InvalidIdException If the comment is not found.
   */
  public void deleteComment(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Id cannot be null");
    }
    if (commentRepository.existsById(id)) {
      commentRepository.deleteById(id);
    } else {
      throw new InvalidIdException("Comment not found with id: " + id);
    }
  }

  /**
   * Get comments by quiz id.
   *
   * @param quizId The id of the quiz.
   * @param pageable The pageable object.
   * @return Page<CommentDTO> containing the comments.
   */
  public Page<CommentDTO> getCommentsByQuizId(Long quizId, Pageable pageable) {
    Page<Comment> comments = commentRepository.findByQuizId(quizId, pageable);
    Page<CommentDTO> commentDTOPage =
        comments.map(CommentDTO::new); // Convert Page<Comment> to Page<CommentDTO>

    // Calculate total pages
    long totalElements = comments.getTotalElements();
    int pageSize = pageable.getPageSize();
    int totalPages = (int) Math.ceil((double) totalElements / pageSize);

    return new PageImpl<>(commentDTOPage.getContent(), pageable, totalPages);
  }

  /**
   * Get comments by user id.
   *
   * @param id The id of the user.
   * @throws InvalidIdException If the user is not found.
   * @return List<CommentDTO> containing the comments.
   */
  private Comment findComment(Long id) {
    return commentRepository
        .findById(id)
        .orElseThrow(() -> new InvalidIdException("Comment not found with id: " + id));
  }

  /**
   * Find a quiz by id.
   *
   * @param id The id of the quiz.
   * @throws InvalidIdException If the quiz is not found.
   * @return The quiz.
   */
  private Quiz findQuiz(Long id) {
    return quizRepository
        .findById(id)
        .orElseThrow(() -> new InvalidIdException("Quiz not found with id: " + id));
  }

  /**
   * Find a user by id.
   *
   * @param id The id of the user.
   * @throws InvalidIdException If the user is not found.
   * @return The user.
   */
  private User findUser(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new InvalidIdException("User not found with id: " + id));
  }
}
