package com.idatt2105.backend.controller;

import com.idatt2105.backend.model.AlternativeDTO;
import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.model.QuestionDTO;
import com.idatt2105.backend.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/get/{id}")
  @Operation(summary = "Get a question by id")
  public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
    Question q = questionService.getQuestionById(id);
    return new ResponseEntity<>(q, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Delete a question")
  public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
    questionService.deleteQuestion(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/update/{id}")
  @Operation(summary = "Update a question")
  public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO question) {
    Question q = questionService.updateQuestion(id, question);
    return new ResponseEntity<>(q, HttpStatus.OK);
  }

  @GetMapping("/get/all/{quizId}")
  @Operation(summary = "Get all questions in a quiz")
  public ResponseEntity<List<Question>> getQuestionsByQuizId(@PathVariable Long quizId) {
    List<Question> questions = questionService.getQuestionsByQuizId(quizId);
    return new ResponseEntity<>(questions, HttpStatus.OK);
  }

  @PostMapping("/add/alternative")
  @Operation(summary = "Add an alternative to a question")
  public ResponseEntity<Void> addAlternative(@RequestBody @NotNull AlternativeDTO alternative) {
    questionService.addAlternative(alternative);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
