package com.idatt2105.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idatt2105.backend.model.Tag;

/** Repository for the Tag entity. */
public interface TagRepository extends JpaRepository<Tag, Long> {
  boolean existsByTagName(String tagName);

  Optional<Tag> findByTagName(String tagName);
}
