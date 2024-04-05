package com.idatt2105.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idatt2105.backend.model.QuizAttempt;

/** Repository for the QuizAttempt entity. */
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
  List<QuizAttempt> findByUserId(Long userId);
}
