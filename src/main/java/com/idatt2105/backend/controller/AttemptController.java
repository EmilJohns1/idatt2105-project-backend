package com.idatt2105.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idatt2105.backend.dto.QuizAttemptDTO;
import com.idatt2105.backend.model.QuizAttempt;
import com.idatt2105.backend.service.AttemptService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/** The AttemptController class handles HTTP requests related to attempts. */
@RestController
@Tag(name = "Attempts", description = "Operations related to attempts")
@RequestMapping("/api/attempts")
public class AttemptController {
  private final AttemptService attemptService;

  @Autowired
  public AttemptController(AttemptService attemptService) {
    this.attemptService = attemptService;
  }

  /**
   * Add a quiz attempt
   *
   * @param quizAttemptDTO The DTO containing the quiz attempt data
   * @return The created QuizAttempt
   */
  @PostMapping("/add")
  @Operation(summary = "Add a quiz attempt")
  public ResponseEntity<QuizAttempt> addQuizAttempt(@RequestBody QuizAttemptDTO quizAttemptDTO) {
    QuizAttempt quizAttempt = attemptService.addQuizAttempt(quizAttemptDTO);
    return new ResponseEntity<>(quizAttempt, HttpStatus.CREATED);
  }

  /**
   * Get all attempts for a user
   *
   * @param userId The ID of the user
   * @return A collection of QuizAttempts
   */
  @GetMapping("/all/{userId}")
  @Operation(summary = "Get all attempts for a user")
  public ResponseEntity<Page<QuizAttempt>> getAllAttemptsForUser(
      @PathVariable Long userId, Pageable pageable) {
    Page<QuizAttempt> quizAttempts = attemptService.getAllAttemptsForUser(userId, pageable);
    return new ResponseEntity<>(quizAttempts, HttpStatus.OK);
  }

  /**
   * Get an attempt by id
   *
   * @param id The ID of the attempt
   * @return The QuizAttempt
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get an attempt by id")
  public ResponseEntity<QuizAttempt> getAttemptById(@PathVariable Long id) {
    QuizAttempt quizAttempt = attemptService.getAttemptById(id);
    return new ResponseEntity<>(quizAttempt, HttpStatus.OK);
  }
}
