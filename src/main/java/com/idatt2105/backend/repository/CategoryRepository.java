package com.idatt2105.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idatt2105.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByName(String name);

  boolean existsByName(String name);
}
