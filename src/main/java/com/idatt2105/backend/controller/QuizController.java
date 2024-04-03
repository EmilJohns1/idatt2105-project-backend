package com.idatt2105.backend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.Category;
import com.idatt2105.backend.model.Tag;
import com.idatt2105.backend.service.QuizService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/quizzes")
@io.swagger.v3.oas.annotations.tags.Tag(
    name = "Quizzes",
    description = "Operations related to quizzes")
public class QuizController {

  private final QuizService quizService;

  @Autowired
  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  @GetMapping
  @Operation(summary = "Get all quizzes")
  public ResponseEntity<Page<QuizDTO>> getAllQuizzes(Pageable pageable) {
    Page<QuizDTO> quizzes = quizService.getAllQuizzes(pageable);
    return new ResponseEntity<>(quizzes, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get quiz by id")
  public ResponseEntity<QuizDTO> getQuizById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(quizService.getQuizById(id), HttpStatus.OK);
  }

  @PostMapping
  @Operation(summary = "Create quiz")
  public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO) {
    QuizDTO createdQuiz = quizService.save(quizDTO);
    return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete quiz")
  public ResponseEntity<Void> deleteQuiz(@PathVariable("id") Long id) {
    quizService.deleteQuiz(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/{quizId}/users/{userId}")
  @Operation(summary = "Add user to quiz")
  public ResponseEntity<Void> addUserToQuiz(
      @PathVariable("quizId") Long quizId, @PathVariable("userId") Long userId) {
    quizService.addUserToQuiz(userId, quizId);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping("/{quizId}/users/{userId}")
  @Operation(summary = "Remove user from quiz")
  public ResponseEntity<Void> removeUserFromQuiz(
      @PathVariable("quizId") Long quizId, @PathVariable("userId") Long userId) {
    quizService.removeUserFromQuiz(userId, quizId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update quiz")
  public ResponseEntity<Void> updateQuiz(
      @PathVariable("id") Long id, @RequestBody QuizDTO requestDTO) {
    quizService.updateQuiz(id, requestDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/users/{quizId}")
  @Operation(summary = "Get users by quiz id")
  public ResponseEntity<Set<UserDTO>> getUsersByQuizId(@PathVariable("quizId") Long quizId) {
    Set<UserDTO> users = quizService.getUsersByQuizId(quizId);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @PatchMapping("/add/tags/{quizId}")
  @Operation(summary = "Adds one or more tags to a quiz")
  public ResponseEntity<QuizDTO> addTags(@PathVariable Long quizId, @RequestBody List<Tag> tags) {
    QuizDTO dto = new QuizDTO();
    dto.setId(quizId);
    dto.addAllTags(tags);
    QuizDTO q = quizService.addTags(dto);
    return new ResponseEntity<>(q, HttpStatus.OK);
  }

  @PatchMapping("/{quizId}/tags")
  @Operation(summary = "Update tags of a quiz")
  public ResponseEntity<QuizDTO> updateTags(
      @PathVariable Long quizId, @RequestBody List<Tag> updatedTags) {
    QuizDTO dto = quizService.updateTags(quizId, updatedTags);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @DeleteMapping("/delete/tags/{quizId}")
  @Operation(summary = "Deletes the given tags from a quiz")
  public ResponseEntity<QuizDTO> deleteTags(
      @PathVariable Long quizId, @RequestBody List<Tag> tags) {
    QuizDTO dto = new QuizDTO();
    dto.setId(quizId);
    dto.addAllTags(tags);
    QuizDTO q = quizService.deleteTags(dto);
    return new ResponseEntity<>(q, HttpStatus.OK);
  }

  @GetMapping("/tag")
  @Operation(summary = "Get all quizzes with a specific tag")
  public ResponseEntity<Page<QuizDTO>> getQuizzesByTag(
      @RequestParam String tag, Pageable pageable) {
    if (tag == null || tag.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }

    Page<QuizDTO> quizzes = quizService.getQuizzesByTag(tag, pageable);

    return ResponseEntity.ok(quizzes);
  }

  @GetMapping("/all/tags")
  @Operation(summary = "Get all tags currently in use")
  public ResponseEntity<List<Tag>> getAllTags() {
    List<Tag> tags = quizService.getAllTags();
    return new ResponseEntity<>(tags, HttpStatus.OK);
  }

  @PostMapping("/filter-by-tags")
  @Operation(summary = "Filter quizzes by tags")
  public ResponseEntity<Page<QuizDTO>> filterQuizzesByTags(
      @RequestBody List<String> tags, Pageable pageable) {
    Page<QuizDTO> quizzes = quizService.getQuizzesByTags(tags, pageable);
    return new ResponseEntity<>(quizzes, HttpStatus.OK);
  }

  @PostMapping("/create/category")
  @Operation(summary = "Create a new category")
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    Category createdCategory = quizService.createCategory(category);
    return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
  }

  @GetMapping("/category")
  @Operation(summary = "Get all quizzes with a specific category")
  public ResponseEntity<Page<QuizDTO>> getQuizzesByCategory(
      @RequestParam String category, Pageable pageable) {
    if (category == null || category.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }

    Page<QuizDTO> quizzes = quizService.getQuizzesByCategory(category, pageable);
    return ResponseEntity.ok(quizzes);
  }

  @GetMapping("/categories")
  @Operation(summary = "Get all categories")
  public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = quizService.getAllCategories();
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }
}
