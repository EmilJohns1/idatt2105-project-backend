package com.idatt2105.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idatt2105.backend.model.Feedback;

/** Repository for the Feedback entity. */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
  List<Feedback> findByUserId(Long userId);
}
