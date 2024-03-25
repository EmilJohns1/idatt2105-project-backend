package com.idatt2105.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idatt2105.backend.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {}
