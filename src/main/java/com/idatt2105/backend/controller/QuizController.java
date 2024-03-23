package com.idatt2105.backend.controller;

import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/quizzes")
@Tag(name = "Quizzes", description = "Operations related to quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    @Operation(summary = "Get all quizzes")
    public ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        List<QuizDTO> quizzes = quizService.getAllQuizzes();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get quiz by id")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable("id") Long id) {
        Optional<QuizDTO> quiz = quizService.getQuizById(id);
        return quiz.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Create quiz")
    public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO) {
        QuizDTO createdQuiz = quizService.save(quizDTO.toEntity());
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
    public ResponseEntity<Void> addUserToQuiz(@PathVariable("quizId") Long quizId, @PathVariable("userId") Long userId) {
        quizService.addUserToQuiz(userId, quizId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{quizId}/users/{userId}")
    @Operation(summary = "Remove user from quiz")
    public ResponseEntity<Void> removeUserFromQuiz(@PathVariable("quizId") Long quizId, @PathVariable("userId") Long userId) {
        quizService.removeUserFromQuiz(userId, quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update quiz")
    public ResponseEntity<Void> updateQuiz(@PathVariable("id") Long id, @RequestBody QuizDTO quizDTO) {
        quizService.updateQuiz(id, quizDTO.toEntity());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{quizId}")
    @Operation(summary = "Get users by quiz id")
    public ResponseEntity<Set<UserDTO>> getUsersByQuizId(@PathVariable("quizId") Long quizId) {
        Set<UserDTO> users = quizService.getUsersByQuizId(quizId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
  }
