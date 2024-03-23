package com.idatt2105.backend.controller;

import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.model.QuestionDTO;
import com.idatt2105.backend.service.QuestionService;
import com.idatt2105.backend.util.QuizNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
@Tag(name = "Questions", description = "Operations related to questions")
public class QuestionController {
  private final QuestionService questionService;

  @Autowired
  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  @PostMapping("/add")
  @Operation(summary = "Add a question to a quiz")
  public ResponseEntity<Question> addQuestion(@RequestBody @NotNull QuestionDTO question) {
    Question q = questionService.addQuestion(question);
    return new ResponseEntity<>(q, HttpStatus.CREATED);
  }
}
