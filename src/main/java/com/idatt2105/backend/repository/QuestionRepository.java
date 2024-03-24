package com.idatt2105.backend.repository;

import com.idatt2105.backend.model.Alternative;
import com.idatt2105.backend.model.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
  List<Question> findQuestionsByCategory(String category);
  List<Question> findQuestionsByQuizId(Long quizId);
}
