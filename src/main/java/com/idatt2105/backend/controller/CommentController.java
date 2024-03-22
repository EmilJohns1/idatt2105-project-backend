package com.idatt2105.backend.controller;

import com.idatt2105.backend.model.Comment;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comments", description = "Operations related to comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    @Operation(summary = "Get all comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get comment by id")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Create comment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.saveComment(comment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update comment")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        commentService.updateComment(id, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete comment")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{commentId}/users/{userId}")
    @Operation(summary = "Set user to comment")
    public ResponseEntity<Comment> setUserToComment(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId) {
        User user = new User();
        user.setId(userId);
        Comment updatedComment = commentService.addUserToComment(commentId, user);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @PutMapping("/{commentId}/quizzes/{quizId}")
    @Operation(summary = "Set quiz to comment")
    public ResponseEntity<Comment> setQuizToComment(@PathVariable("commentId") Long commentId, @PathVariable("quizId") Long quizId) {
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        Comment updatedComment = commentService.addQuizToComment(commentId, quiz);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // Add other endpoints as needed
}
