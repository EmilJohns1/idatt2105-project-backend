package com.idatt2105.backend.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.idatt2105.backend.dto.CommentDTO;
import com.idatt2105.backend.model.Comment;
import com.idatt2105.backend.repository.CommentRepository;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.InvalidIdException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
  }
}
