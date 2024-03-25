package com.idatt2105.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idatt2105.backend.model.Comment;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByQuiz(Quiz quiz);

  List<Comment> findByUser(User user);
}
