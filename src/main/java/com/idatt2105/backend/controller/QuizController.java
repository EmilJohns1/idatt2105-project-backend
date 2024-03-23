// package com.idatt2105.backend.controller;

// import com.idatt2105.backend.model.Quiz;
// import com.idatt2105.backend.service.QuizService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/quizzes")
// @Tag(name = "Quizzes", description = "Operations related to quizzes")
// public class QuizController {

//     private final QuizService quizService;

//     @Autowired
//     public QuizController(QuizService quizService) {
//         this.quizService = quizService;
//     }

//     @GetMapping
//     @Operation(summary = "Get all quizzes")
//     public ResponseEntity<List<Quiz>> getAllQuizzes() {
//         List<Quiz> quizzes = quizService.getAllQuizzes();
//         return new ResponseEntity<>(quizzes, HttpStatus.OK);
//     }

//     @GetMapping("/{id}")
//     @Operation(summary = "Get quiz by id")
//     public ResponseEntity<Quiz> getQuizById(@PathVariable("id") Long id) {
//         Optional<Quiz> quiz = quizService.getQuizById(id);
//         return quiz.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//     }

//     @PostMapping
//     @Operation(summary = "Create quiz")
//     public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
//         Quiz createdQuiz = quizService.save(quiz);
//         return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
//     }

//     @DeleteMapping("/{id}")
//     @Operation(summary = "Delete quiz")
//     public ResponseEntity<Void> deleteQuiz(@PathVariable("id") Long id) {
//         quizService.deleteQuiz(id);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }

//     @PostMapping("/{quizId}/users/{userId}")
//     @Operation(summary = "Add user to quiz")
//     public ResponseEntity<Void> addUserToQuiz(@PathVariable("quizId") Long quizId, @PathVariable("userId") Long userId) {
//         quizService.addUserToQuiz(userId, quizId);
//         return new ResponseEntity<>(HttpStatus.CREATED);
//     }

//     @DeleteMapping("/{quizId}/users/{userId}")
//     @Operation(summary = "Remove user from quiz")
//     public ResponseEntity<Void> removeUserFromQuiz(@PathVariable("quizId") Long quizId, @PathVariable("userId") Long userId) {
//         quizService.removeUserFromQuiz(userId, quizId);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }

//     @PutMapping("/{id}")
//     @Operation(summary = "Update quiz")
//     public ResponseEntity<Quiz> updateQuiz(@PathVariable("id") Long id, @RequestBody Quiz quiz) {
//         quizService.updateQuiz(id, quiz);
//         return new ResponseEntity<>(HttpStatus.OK);
//     }

//     @PutMapping("/{quizId}/users/{userId}")
//     @Operation(summary = "Edit users in quiz")
//     public ResponseEntity<Void> editUsersInQuiz(@PathVariable("quizId") Long quizId, @PathVariable("userId") Long userId) {
//         quizService.editUsersInQuiz(userId, quizId);
//         return new ResponseEntity<>(HttpStatus.OK);
//     }

// }
