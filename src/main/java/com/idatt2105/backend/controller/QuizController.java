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

/** The QuizController class handles HTTP requests related to quiz operations. */
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

  /**
   * Get all quizzes
   *
   * @param pageable (Pageable) Pageable object for pagination
   * @return (ResponseEntity <Page<QuizDTO>>) Page of quizzes
   */
  @GetMapping
  @Operation(summary = "Get all quizzes")
  public ResponseEntity<Page<QuizDTO>> getAllQuizzes(Pageable pageable) {
    Page<QuizDTO> quizzes = quizService.getAllQuizzes(pageable);
    return new ResponseEntity<>(quizzes, HttpStatus.OK);
  }

  /**
   * Get quiz by id
   *
   * @param id (Long) The id of the quiz to get
   * @return (ResponseEntity <QuizDTO>) The quiz with the given id
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get quiz by id")
  public ResponseEntity<QuizDTO> getQuizById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(quizService.getQuizById(id), HttpStatus.OK);
  }

  /**
   * Create quiz
   *
   * @param quizDTO (QuizDTO) The quiz to create
   * @return (ResponseEntity <QuizDTO>) The created quiz
   */
  @PostMapping
  @Operation(summary = "Create quiz")
  public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO) {
    QuizDTO createdQuiz = quizService.save(quizDTO);
    return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
  }

  /**
   * Delete quiz
   *
   * @param id (Long) The id of the quiz to delete
   * @return (ResponseEntity <Void>) ResponseEntity with no content
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete quiz")
  public ResponseEntity<Void> deleteQuiz(@PathVariable("id") Long id) {
    quizService.deleteQuiz(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Add user to quiz
   *
   * @param quizId (Long) The id of the quiz
   * @param userId (Long) The id of the user
   * @return (ResponseEntity <Void>) ResponseEntity with no content
   */
  @PostMapping("/{quizId}/users/{userId}")
  @Operation(summary = "Add user to quiz")
  public ResponseEntity<Void> addUserToQuiz(
      @PathVariable("quizId") Long quizId, @PathVariable("userId") Long userId) {
    quizService.addUserToQuiz(userId, quizId);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Remove user from quiz
   *
   * @param quizId (Long) The id of the quiz
   * @param userId (Long) The id of the user
   * @return (ResponseEntity <Void>) ResponseEntity with no content
   */
  @DeleteMapping("/{quizId}/users/{userId}")
  @Operation(summary = "Remove user from quiz")
  public ResponseEntity<Void> removeUserFromQuiz(
      @PathVariable("quizId") Long quizId, @PathVariable("userId") Long userId) {
    quizService.removeUserFromQuiz(userId, quizId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Update quiz
   *
   * @param id (Long) The id of the quiz to update
   * @param requestDTO (QuizDTO) The updated quiz
   * @return (ResponseEntity <Void>) ResponseEntity with no content
   */
  @PutMapping("/{id}")
  @Operation(summary = "Update quiz")
  public ResponseEntity<Void> updateQuiz(
      @PathVariable("id") Long id, @RequestBody QuizDTO requestDTO) {
    quizService.updateQuiz(id, requestDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Get users by quiz id
   *
   * @param quizId (Long) The id of the quiz
   * @return (ResponseEntity <Set<UserDTO>>) Set of users
   */
  @GetMapping("/users/{quizId}")
  @Operation(summary = "Get users by quiz id")
  public ResponseEntity<Set<UserDTO>> getUsersByQuizId(@PathVariable("quizId") Long quizId) {
    Set<UserDTO> users = quizService.getUsersByQuizId(quizId);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  /**
   * Add tags to quiz
   *
   * @param quizId (Long) The id of the quiz
   * @param tags (List<Tag>) The tags to add
   * @return (ResponseEntity <QuizDTO>) The updated quiz
   */
  @PatchMapping("/add/tags/{quizId}")
  @Operation(summary = "Adds one or more tags to a quiz")
  public ResponseEntity<QuizDTO> addTags(@PathVariable Long quizId, @RequestBody List<Tag> tags) {
    QuizDTO dto = new QuizDTO();
    dto.setId(quizId);
    dto.addAllTags(tags);
    QuizDTO q = quizService.addTags(dto);
    return new ResponseEntity<>(q, HttpStatus.OK);
  }

  /**
   * Update tags of a quiz
   *
   * @param quizId (Long) The id of the quiz
   * @param updatedTags (List<Tag>) The updated tags
   * @return (ResponseEntity <QuizDTO>) The updated quiz
   */
  @PatchMapping("/{quizId}/tags")
  @Operation(summary = "Update tags of a quiz")
  public ResponseEntity<QuizDTO> updateTags(
      @PathVariable Long quizId, @RequestBody List<Tag> updatedTags) {
    QuizDTO dto = quizService.updateTags(quizId, updatedTags);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  /**
   * Delete tags from quiz
   *
   * @param quizId (Long) The id of the quiz
   * @param tags (List<Tag>) The tags to delete
   * @return (ResponseEntity <QuizDTO>) The updated quiz
   */
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

  /**
   * Get all quizzes with a specific tag
   *
   * @param tag (String) The tag to filter by
   * @param pageable (Pageable) Pageable object for pagination
   * @return (ResponseEntity <Page<QuizDTO>>) Page of quizzes
   */
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

  /**
   * Get all tags currently in use
   *
   * @return (ResponseEntity <List<Tag>>) List of tags
   */
  @GetMapping("/all/tags")
  @Operation(summary = "Get all tags currently in use")
  public ResponseEntity<List<Tag>> getAllTags() {
    List<Tag> tags = quizService.getAllTags();
    return new ResponseEntity<>(tags, HttpStatus.OK);
  }

  /**
   * Get all quizzes with the specified tags
   *
   * @param tags (List<String>) The tags to filter by
   * @param pageable (Pageable) Pageable object for pagination
   * @return (ResponseEntity <Page<QuizDTO>>) Page of quizzes
   */
  @PostMapping("/filter-by-tags")
  @Operation(summary = "Get all quizzes with the specified tags")
  public ResponseEntity<Page<QuizDTO>> filterQuizzesByTags(
      @RequestBody List<String> tags, Pageable pageable) {
    Page<QuizDTO> quizzes = quizService.getQuizzesByTags(tags, pageable);
    return new ResponseEntity<>(quizzes, HttpStatus.OK);
  }

  /**
   * Create a new category
   *
   * @param category (Category) The category to create
   * @return (ResponseEntity <Category>) The created category
   */
  @PostMapping("/create/category")
  @Operation(summary = "Create a new category")
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    Category createdCategory = quizService.createCategory(category);
    return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
  }

  /**
   * Get all quizzes with a specific category
   *
   * @param category (String) The category to filter by
   * @param pageable (Pageable) Pageable object for pagination
   * @return (ResponseEntity <Page<QuizDTO>>) Page of quizzes
   */
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

  /**
   * Get all categories
   *
   * @return (ResponseEntity <List<Category>>) List of categories
   */
  @GetMapping("/categories")
  @Operation(summary = "Get all categories")
  public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = quizService.getAllCategories();
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }

  @GetMapping("/all/public")
  @Operation(summary = "Get all public quizzes")
  public ResponseEntity<Page<QuizDTO>> getAllPublicQuizzes(Pageable pageable) {
    return new ResponseEntity<>(quizService.getAllPublicQuizzes(pageable), HttpStatus.OK);
  }
}
