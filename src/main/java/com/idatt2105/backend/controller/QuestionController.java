package com.idatt2105.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.idatt2105.backend.dto.AlternativeDTO;
import com.idatt2105.backend.dto.QuestionDTO;
import com.idatt2105.backend.model.Alternative;
import com.idatt2105.backend.model.Question;
import com.idatt2105.backend.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller for handling questions. This controller provides endpoints for CRUD operations on
 * questions.
 */
@RestController
@RequestMapping("/api/question")
@io.swagger.v3.oas.annotations.tags.Tag(
    name = "Questions",
    description = "Operations related to questions")
public class QuestionController {
  private final QuestionService questionService;

  /**
   * Constructor for the QuestionController class. It initializes the questionService which will be
   * used to perform operations on questions.
   *
   * @param questionService (QuestionService) Service for handling questions.
   */
  @Autowired
  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  /**
   * Adds a question to a quiz. This endpoint accepts a QuestionDTO object and adds it to a quiz.
   *
   * @param question (QuestionDTO) The question to add.
   * @return (ResponseEntity < Question >) The added question.
   */
  @PostMapping("/add")
  @Operation(summary = "Add a question to a quiz")
  public ResponseEntity<Question> addQuestion(@RequestBody QuestionDTO question) {
    Question q = questionService.addQuestion(question);
    return new ResponseEntity<>(q, HttpStatus.CREATED);
  }

  /**
   * Gets a question by id. This endpoint returns a question with the given id.
   *
   * @param id (Long) The id of the question to get.
   * @return (ResponseEntity < Question >) The question with the given id.
   */
  @GetMapping("/get/{id}")
  @Operation(summary = "Get a question by id")
  public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
    Question q = questionService.getQuestionById(id);
    return new ResponseEntity<>(q, HttpStatus.OK);
  }

  /**
   * Deletes a question. This endpoint deletes a question with the given id.
   *
   * @param id (Long) The id of the question to delete.
   * @return (ResponseEntity < Void >) Response entity with status OK.
   */
  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Delete a question")
  public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
    questionService.deleteQuestion(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Updates a question. This endpoint updates a question with the given id using the provided
   * QuestionDTO object.
   *
   * @param question (QuestionDTO) The updated question.
   * @return (ResponseEntity < Question >) The updated question.
   */
  @PostMapping("/update")
  @Operation(summary = "Update a question")
  public ResponseEntity<Question> updateQuestion(@RequestBody QuestionDTO question) {
    Question q = questionService.updateQuestion(question);
    return new ResponseEntity<>(q, HttpStatus.OK);
  }

  /**
   * Gets all questions in a quiz. This endpoint returns all questions in a quiz with the given id.
   *
   * @param quizId (Long) The id of the quiz to get questions from.
   * @return (ResponseEntity < List < Question > >) List of questions in the quiz.
   */
  @GetMapping("/get/all/{quizId}")
  @Operation(summary = "Get all questions in a quiz")
  public ResponseEntity<List<Question>> getQuestionsByQuizId(@PathVariable Long quizId) {
    List<Question> questions = questionService.getQuestionsByQuizId(quizId);
    return new ResponseEntity<>(questions, HttpStatus.OK);
  }

  /**
   * Adds an alternative to a question. This endpoint accepts an AlternativeDTO object and adds it
   * as an alternative to a question.
   *
   * @param alternative (AlternativeDTO) The alternative to add.
   * @return (ResponseEntity < Void >) Response entity with status CREATED.
   */
  @PostMapping("/add/alternative")
  @Operation(summary = "Add an alternative to a question")
  public ResponseEntity<Alternative> addAlternative(@RequestBody AlternativeDTO alternative) {
    Alternative alt = questionService.addAlternative(alternative);
    return new ResponseEntity<>(alt, HttpStatus.CREATED);
  }

  /**
   * Deletes the alternative with the given id.
   *
   * @param id (Long) The id of the alternative to delete.
   * @return (ResponseEntity < Void >) Response entity with status OK.
   */
  @DeleteMapping("/delete/alternative/{id}")
  @Operation(summary = "Delete an alternative")
  public ResponseEntity<Void> deleteAlternative(@PathVariable Long id) {
    questionService.deleteAlternative(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{questionId}/alternatives")
  public ResponseEntity<List<Alternative>> updateAlternatives(
    @PathVariable Long questionId,
    @RequestBody List<AlternativeDTO> alternativeDTOs) {
    List<Alternative> updatedAlternatives = questionService.updateAlternatives(questionId, alternativeDTOs);
    return new ResponseEntity<>(updatedAlternatives, HttpStatus.OK);
  }

  /**
   * Updates the correct answer for a true or false question.
   *
   * @param questionId (Long) The id of the question to update.
   * @param isCorrect (Boolean) The correct answer for the question.
   * @return (ResponseEntity < Question >) The updated question.
   */
  @PatchMapping("/update/true-or-false/{questionId}&&{isCorrect}")
  @Operation(summary = "Set the correct answer for true or false question")
  public ResponseEntity<Question> updateTrueOrFalseQuestion(
      @PathVariable Long questionId, @PathVariable Boolean isCorrect) {
    QuestionDTO question = new QuestionDTO();
    question.setQuestionId(questionId);
    question.isCorrect(isCorrect);
    Question q = questionService.updateTrueOrFalseQuestion(question);
    return new ResponseEntity<>(q, HttpStatus.OK);
  }
}
