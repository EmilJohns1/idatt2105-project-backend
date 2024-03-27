package com.idatt2105.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.dto.CommentDTO;
import com.idatt2105.backend.model.Comment;
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
    if (!quizRepository.existsById(quizId)) {
      throw new InvalidIdException("Quiz not found with id: " + quizId);
    }

    List<Comment> comments = commentRepository.findByQuiz(quizRepository.findById(quizId).get());
    return comments.stream().map(CommentDTO::new).toList();
  }

  public List<CommentDTO> getCommentsByUserId(Long userId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new InvalidIdException("User with id " + userId + " not found"));
    List<Comment> comments = commentRepository.findByUser(user);
    return comments.stream().map(CommentDTO::new).toList();
  }

  public CommentDTO saveComment(CommentDTO commentDTO) {
    Comment comment = new Comment();
    comment.setContent(commentDTO.getContent());
    comment.setUserId(commentDTO.getUserId());
    comment.setQuizId(commentDTO.getQuizId());
    comment.setCreationDate(LocalDateTime.now());
    comment.setLastModifiedDate(LocalDateTime.now());

    Comment savedComment = commentRepository.save(comment);
    return new CommentDTO(savedComment);
  }

  public void updateComment(Long id, CommentDTO updatedComment) {
    Comment comment = findComment(id);
    comment.setContent(updatedComment.getContent());
    comment.setLastModifiedDate(LocalDateTime.now());

    commentRepository.save(comment);
  }

  public void deleteComment(Long id) {
    if (commentRepository.existsById(id)) {
      commentRepository.deleteById(id);
    } else {
      throw new InvalidIdException("Comment not found with id: " + id);
    }
  }

  private Comment findComment(Long id) {
    return commentRepository
        .findById(id)
        .orElseThrow(() -> new InvalidIdException("Comment not found with id: " + id));
  }
}
