package com.idatt2105.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idatt2105.backend.model.Question;

/** Repository for Question entities. */
public interface QuestionRepository extends JpaRepository<Question, Long> {
  List<Question> findQuestionsByQuizId(Long quizId);
}
