package com.idatt2105.backend.repository;

import com.idatt2105.backend.model.QuizAttempt;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
  List<QuizAttempt> findByUserId(Long userId);
}
