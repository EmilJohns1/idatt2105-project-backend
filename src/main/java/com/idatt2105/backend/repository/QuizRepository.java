package com.idatt2105.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idatt2105.backend.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
  Optional<Quiz> findByTitle(String title);
}
