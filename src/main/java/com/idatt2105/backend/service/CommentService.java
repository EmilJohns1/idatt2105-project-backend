package com.idatt2105.backend.service;

import com.idatt2105.backend.model.Comment;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public void updateComment(Long id, Comment comment) {
      Comment existingComment = commentRepository.findById(id)
              .orElseThrow(() -> new IllegalStateException("Comment with id " + id + " not found"));
  
      if (comment.getContent() != null) {
          existingComment.setContent(comment.getContent());
          commentRepository.save(existingComment);
      } else {
          throw new IllegalStateException("Comment content cannot be null");
      }
    }

    public List<Comment> getCommentsByQuizId(Long quizId) {
        return commentRepository.findByQuizId(quizId);
    }

    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    public Comment addUserToComment(Long commentId, User user) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setUser(user);
            return commentRepository.save(comment);
        }
        return null;
    }

    public Comment addQuizToComment(Long commentId, Quiz quiz) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setQuiz(quiz);
            return commentRepository.save(comment);
        }
        return null;
    }
}
