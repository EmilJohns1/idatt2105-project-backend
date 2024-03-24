package com.idatt2105.backend.controller;

import com.idatt2105.backend.dto.QuizAttemptDTO;
import com.idatt2105.backend.model.QuizAttempt;
import com.idatt2105.backend.service.AttemptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Attempts", description = "Operations related to attempts")
@RequestMapping("/api/attempts")
public class AttemptController {
  private final AttemptService attemptService;

  @Autowired
  public AttemptController(AttemptService attemptService) {
      this.attemptService = attemptService;
  }

  @PostMapping("/add")
  public ResponseEntity<QuizAttempt> addQuizAttempt(@RequestBody QuizAttemptDTO quizAttemptDTO) {
    QuizAttempt quizAttempt = attemptService.addQuizAttempt(quizAttemptDTO);
    return new ResponseEntity<>(quizAttempt, HttpStatus.CREATED);
  }
}
