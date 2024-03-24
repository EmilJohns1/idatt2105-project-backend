package com.idatt2105.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idatt2105.backend.dto.CommentDTO;
import com.idatt2105.backend.model.Comment;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.CommentRepository;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.CommentNotFoundException;

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
    return commentRepository.findAll().stream().map(CommentDTO::new).collect(Collectors.toList());
  }

  public CommentDTO getCommentById(Long id) {
    Comment comment =
        commentRepository
            .findById(id)
            .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
    return new CommentDTO(comment);
  }

  public List<CommentDTO> getCommentsByQuizId(Long quizId) {
    Quiz quiz =
        quizRepository
            .findById(quizId)
            .orElseThrow(() -> new IllegalStateException("Quiz with id " + quizId + " not found"));
    List<Comment> comments = commentRepository.findByQuiz(quiz);
    List<CommentDTO> commentDTOs =
        comments.stream().map(CommentDTO::new).collect(Collectors.toList());
    return commentDTOs;
  }

  public List<CommentDTO> getCommentsByUserId(Long userId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new IllegalStateException("User with id " + userId + " not found"));
    List<Comment> comments = commentRepository.findByUser(user);
    List<CommentDTO> commentDTOs =
        comments.stream().map(CommentDTO::new).collect(Collectors.toList());
    return commentDTOs;
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
    Comment comment =
        commentRepository
            .findById(id)
            .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
    comment.setContent(updatedComment.getContent());
    comment.setLastModifiedDate(LocalDateTime.now());

    commentRepository.save(comment);
  }

  public void deleteComment(Long id) {
    if (commentRepository.existsById(id)) {
      commentRepository.deleteById(id);
    } else {
      throw new CommentNotFoundException("Comment not found with id: " + id);
    }
  }
}
