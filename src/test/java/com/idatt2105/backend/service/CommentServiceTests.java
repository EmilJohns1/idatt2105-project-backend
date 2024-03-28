package com.idatt2105.backend.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.idatt2105.backend.dto.CommentDTO;
import com.idatt2105.backend.model.Comment;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.CommentRepository;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.InvalidIdException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CommentServiceTests {
  @Mock private CommentRepository commentRepository;
  @Mock private QuizRepository quizRepository;
  @Mock private UserRepository userRepository;
  @InjectMocks private CommentService commentService;

  @BeforeEach
  void setUp() {
    when(commentRepository.findAll()).thenReturn(List.of(new Comment()));
  }

  @Nested
  class BasicFunctionality {
    @Test
    void getAllComments() {
      List<CommentDTO> dtos = List.of(new CommentDTO());
      assertEquals(dtos, commentService.getAllComments());
    }

    @Test
    void getCommentsByQuizID() {
      when(quizRepository.findById(any(Long.class))).thenReturn(Optional.of(new Quiz()));
      Comment comment = new Comment();
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      comment.setQuiz(quiz);
      when(commentRepository.findByQuiz(any(Quiz.class))).thenReturn(List.of(comment));
      assertEquals(new CommentDTO(comment), commentService.getCommentsByQuizId(1L).get(0));
    }

    @Test
    void getCommentsByUserId() {
      when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(new User()));
      Comment comment = new Comment();
      User user = new User();
      user.setId(1L);
      comment.setUser(user);
      when(commentRepository.findByUser(any(User.class))).thenReturn(List.of(comment));
      assertEquals(new CommentDTO(comment), commentService.getCommentsByUserId(1L).get(0));
    }

    @Test
    void saveComment() {
      CommentDTO dto = new CommentDTO();
      dto.setContent("Content");
      dto.setQuizId(1L);
      dto.setUserId(1L);
      when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(new User()));
      when(quizRepository.findById(any(Long.class))).thenReturn(Optional.of(new Quiz()));
      when(commentRepository.save(any(Comment.class))).thenAnswer(returnsFirstArg());
      CommentDTO saved = commentService.saveComment(dto);
      assertEquals(dto.getContent(), saved.getContent());
    }

    @Test
    void updateComment() {
      CommentDTO dto = new CommentDTO();
      dto.setContent("Content");
      when(commentRepository.findById(1L)).thenReturn(Optional.of(new Comment()));
      commentService.updateComment(1L, dto);
      verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void deleteComment() {
      when(commentRepository.existsById(1L)).thenReturn(true);
      commentService.deleteComment(1L);
      verify(commentRepository).deleteById(1L);
    }
  }

  @Nested
  class InvalidParameters {
    @Test
    void getCommentByIdThrowsExceptionWithNullId() {
      assertThrows(InvalidIdException.class, () -> commentService.getCommentById(null));
    }

    @Test
    void getCommentByIdThrowsExceptionWithInvalidId() {
      assertThrows(InvalidIdException.class, () -> commentService.getCommentById(5L));
    }

    @Test
    void getCommentsByQuizIdThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> commentService.getCommentsByQuizId(null));
    }

    @Test
    void getCommentsByQuizIdThrowsExceptionWhenGivenInvalidId() {
      assertThrows(InvalidIdException.class, () -> commentService.getCommentsByQuizId(5L));
    }

    @Test
    void getCommentsByUserThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> commentService.getCommentsByUserId(null));
    }

    @Test
    void getCommentsByUserThrowsExceptionWhenGivenInvalidId() {
      assertThrows(InvalidIdException.class, () -> commentService.getCommentsByUserId(5L));
    }

    @Test
    void saveCommentThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> commentService.saveComment(null));
    }

    @Test
    void updateCommentThrowsExceptionWhenGivenNullAsId() {
      CommentDTO dto = new CommentDTO();
      assertThrows(IllegalArgumentException.class, () -> commentService.updateComment(null, dto));
    }

    @Test
    void updateCommentThrowsExceptionWhenGivenNullAsDTO() {
      assertThrows(IllegalArgumentException.class, () -> commentService.updateComment(5L, null));
    }

    @Test
    void deleteCommentThrowsExceptionWhenGivenNullAsId() {
      assertThrows(IllegalArgumentException.class, () -> commentService.deleteComment(null));
    }

    @Test
    void deleteCommentThrowsExceptionWithInvalidId() {
      assertThrows(InvalidIdException.class, () -> commentService.deleteComment(5L));
    }
  }
}
