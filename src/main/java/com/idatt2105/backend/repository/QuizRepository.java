package com.idatt2105.backend.repository;

import com.idatt2105.backend.model.Quiz;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
  Optional <Quiz> findByTitle(String title);

}