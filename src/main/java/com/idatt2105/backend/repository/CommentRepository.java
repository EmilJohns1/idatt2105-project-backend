package com.idatt2105.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idatt2105.backend.dto.CommentDTO;
import com.idatt2105.backend.model.Comment;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByQuiz(Quiz quiz);

  List<Comment> findByUser(User user);

  Page<CommentDTO> getCommentsByQuizId(Long quizId, Pageable pageable);

  Page<Comment> findByQuizId(Long quizId, Pageable pageable);
}
