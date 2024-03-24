package com.idatt2105.backend.controller;

import com.idatt2105.backend.dto.CommentDTO;
import com.idatt2105.backend.service.CommentService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The CommentController class handles HTTP requests related to comments.
 */
@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comments", description = "Operations related to comments")
public class CommentController {

  private final CommentService commentService;

  /**
   * Constructs a new CommentController with the specified CommentService.
   *
   * @param commentService the CommentService to be used
   */
  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  /**
   * Retrieves all comments.
   *
   * @return a ResponseEntity containing a list of CommentDTO objects and the HTTP status code
   */
  @GetMapping
  @Operation(summary = "Get all comments")
  public ResponseEntity<List<CommentDTO>> getAllComments() {
    List<CommentDTO> comments = commentService.getAllComments();
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }

  /**
   * Retrieves a comment by its ID.
   *
   * @param id the ID of the comment to retrieve
   * @return a ResponseEntity containing the CommentDTO object and the HTTP status code
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get comment by id")
  public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
    CommentDTO comment = commentService.getCommentById(id);
    return new ResponseEntity<>(comment, HttpStatus.OK);
  }

  /**
   * Creates a new comment.
   *
   * @param commentDTO the CommentDTO object representing the comment to be created
   * @return a ResponseEntity containing the created CommentDTO object and the HTTP status code
   */
  @PostMapping
  @Operation(summary = "Create comment")
  public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
    CommentDTO createdComment = commentService.saveComment(commentDTO);
    return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
  }

  /**
   * Updates an existing comment.
   *
   * @param id          the ID of the comment to update
   * @param commentDTO  the CommentDTO object representing the updated comment
   * @return a ResponseEntity containing the updated CommentDTO object and the HTTP status code
   */
  @PutMapping("/{id}")
  @Operation(summary = "Update comment")
  public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody String content) {
    CommentDTO updatedComment = new CommentDTO(content);
    commentService.updateComment(id, updatedComment);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Deletes a comment by its ID.
   *
   * @param id the ID of the comment to delete
   * @return a ResponseEntity with no content and the HTTP status code
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete comment")
  public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
    commentService.deleteComment(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  /*
   * Gets comments by quiz id
   * 
   * @param quizId (Long) Id of the quiz to get comments for
   * @return List of comments for the quiz
   */
  @GetMapping("/quiz/{quizId}")
  @Operation(summary = "Get comments by quiz id")
  public ResponseEntity<List<CommentDTO>> getCommentsByQuizId(@PathVariable Long quizId) {
    List<CommentDTO> comments = commentService.getCommentsByQuizId(quizId);
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }

  /*
   * Gets comments by user id
   * 
   * @param userId (Long) Id of the user to get comments for
   * @return List of comments for the user
   */
  @GetMapping("/user/{userId}")
  @Operation(summary = "Get comments by user id")
  public ResponseEntity<List<CommentDTO>> getCommentsByUserId(@PathVariable Long userId) {
    List<CommentDTO> comments = commentService.getCommentsByUserId(userId);
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }

}
