package com.idatt2105.backend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idatt2105.backend.model.Category;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.Tag;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
  Optional<Quiz> findByTitle(String title);

  Page<Quiz> findByTagsContains(Tag tag, Pageable pageable);

  Page<Quiz> findByCategory(Category category, Pageable pageable);

  Page<Quiz> findByIsPublicIsTrue(Pageable pageable);
}
