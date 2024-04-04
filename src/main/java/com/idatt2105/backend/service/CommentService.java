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

  public List<CommentDTO> getAllComments() {
    return commentRepository.findAll().stream().map(CommentDTO::new).toList();
  }

  public CommentDTO getCommentById(Long id) {
    if (id == null) {
      throw new InvalidIdException("Id cannot be null");
    }
    Comment comment = findComment(id);
    return new CommentDTO(comment);
  }

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

  public List<CommentDTO> getCommentsByUserId(Long userId) {
    if (userId == null) {
      throw new IllegalArgumentException("User id cannot be null");
    }

    User user = findUser(userId);
    List<Comment> comments = commentRepository.findByUser(user);
    return comments.stream().map(CommentDTO::new).toList();
  }

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

  private Comment findComment(Long id) {
    return commentRepository
        .findById(id)
        .orElseThrow(() -> new InvalidIdException("Comment not found with id: " + id));
  }

  private Quiz findQuiz(Long id) {
    return quizRepository
        .findById(id)
        .orElseThrow(() -> new InvalidIdException("Quiz not found with id: " + id));
  }

  private User findUser(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new InvalidIdException("User not found with id: " + id));
  }
}
