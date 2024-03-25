package com.idatt2105.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idatt2105.backend.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
  List<Question> findQuestionsByCategory(String category);

  List<Question> findQuestionsByQuizId(Long quizId);
}
