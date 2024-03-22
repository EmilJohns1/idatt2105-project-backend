package com.idatt2105.backend.repositories;

import com.idatt2105.backend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByQuizId(Long quizId);
    List<Comment> findByUserId(Long userId);
}
