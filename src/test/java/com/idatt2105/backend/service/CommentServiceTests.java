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

/** The CommentServiceTests class is a test class that tests the CommentService class. */
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

  /**
   * The BasicFunctionality class is a test class that tests the basic functionality of the
   * CommentService class.
   */
  @Nested
  class BasicFunctionality {
    /**
     * The getAllComments method tests the getAllComments method of the CommentService class. It
     * verifies that the method returns all comments.
     */
    @Test
    void getAllComments() {
      List<CommentDTO> dtos = List.of(new CommentDTO());
      assertEquals(dtos, commentService.getAllComments());
    }

    /**
     * The getCommentById method tests the getCommentById method of the CommentService class. It
     * verifies that the method returns the correct comment.
     */
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

    /**
     * The getCommentsByUserId method tests the getCommentsByUserId method of the CommentService
     * class. It verifies that the method returns the correct comments by user id.
     */
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

    /**
     * The saveComment method tests the saveComment method of the CommentService class. It verifies
     * that the method saves a comment to the database.
     */
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

    /**
     * The updateComment method tests the updateComment method of the CommentService class. It
     * verifies that the method updates a comment in the database.
     */
    @Test
    void updateComment() {
      CommentDTO dto = new CommentDTO();
      dto.setContent("Content");
      when(commentRepository.findById(1L)).thenReturn(Optional.of(new Comment()));
      commentService.updateComment(1L, dto);
      verify(commentRepository).save(any(Comment.class));
    }

    /**
     * The deleteComment method tests the deleteComment method of the CommentService class. It
     * verifies that the method deletes a comment from the database.
     */
    @Test
    void deleteComment() {
      when(commentRepository.existsById(1L)).thenReturn(true);
      commentService.deleteComment(1L);
      verify(commentRepository).deleteById(1L);
    }
  }

  /**
   * The InvalidParameters class is a test class that tests the invalid parameters of the
   * CommentService class.
   */
  @Nested
  class InvalidParameters {
    /**
     * The getCommentByIdThrowsExceptionWithNullId method tests the getCommentById method of the
     * CommentService class. It verifies that the method throws an InvalidIdException when the id is
     * null.
     *
     * @throws InvalidIdException if the id is null
     */
    @Test
    void getCommentByIdThrowsExceptionWithNullId() {
      assertThrows(InvalidIdException.class, () -> commentService.getCommentById(null));
    }

    /**
     * The getCommentByIdThrowsExceptionWithInvalidId method tests the getCommentById method of the
     * CommentService class. It verifies that the method throws an InvalidIdException when the id is
     * invalid.
     *
     * @throws InvalidIdException if the id is invalid
     */
    @Test
    void getCommentByIdThrowsExceptionWithInvalidId() {
      assertThrows(InvalidIdException.class, () -> commentService.getCommentById(5L));
    }

    /**
     * The getCommentsByQuizIdThrowsExceptionWhenGivenNullAsParameter method tests the
     * getCommentsByQuizId method of the CommentService class. It verifies that the method throws an
     * IllegalArgumentException when the parameter is null.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void getCommentsByQuizIdThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> commentService.getCommentsByQuizId(null));
    }

    /**
     * The getCommentsByQuizIdThrowsExceptionWhenGivenInvalidId method tests the getCommentsByQuizId
     * method of the CommentService class. It verifies that the method throws an InvalidIdException
     * when the id is invalid.
     *
     * @throws InvalidIdException if the id is invalid
     */
    @Test
    void getCommentsByQuizIdThrowsExceptionWhenGivenInvalidId() {
      assertThrows(InvalidIdException.class, () -> commentService.getCommentsByQuizId(5L));
    }

    /**
     * The getCommentsByUserThrowsExceptionWhenGivenNullAsParameter method tests the
     * getCommentsByUserId method of the CommentService class. It verifies that the method throws an
     * IllegalArgumentException when the parameter is null.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void getCommentsByUserThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> commentService.getCommentsByUserId(null));
    }

    /**
     * The getCommentsByUserThrowsExceptionWhenGivenInvalidId method tests the getCommentsByUserId
     * method of the CommentService class. It verifies that the method throws an InvalidIdException
     * when the id is invalid.
     *
     * @throws InvalidIdException if the id is invalid
     */
    @Test
    void getCommentsByUserThrowsExceptionWhenGivenInvalidId() {
      assertThrows(InvalidIdException.class, () -> commentService.getCommentsByUserId(5L));
    }

    /**
     * The saveCommentThrowsExceptionWhenGivenNullAsParameter method tests the saveComment method of
     * the CommentService class. It verifies that the method throws an IllegalArgumentException when
     * the parameter is null.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void saveCommentThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> commentService.saveComment(null));
    }

    /**
     * The updateCommentThrowsExceptionWhenGivenNullAsId method tests the updateComment method of
     * the CommentService class. It verifies that the method throws an IllegalArgumentException when
     * the id is null.
     *
     * @throws InvalidIdException if the comment id is invalid
     */
    @Test
    void updateCommentThrowsExceptionWhenGivenNullAsId() {
      CommentDTO dto = new CommentDTO();
      assertThrows(IllegalArgumentException.class, () -> commentService.updateComment(null, dto));
    }

    /**
     * The updateCommentThrowsExceptionWhenGivenNullAsDTO method tests the updateComment method of
     * the CommentService class. It verifies that the method throws an IllegalArgumentException when
     * the dto is null.
     *
     * @throws IllegalArgumentException if the dto is null
     */
    @Test
    void updateCommentThrowsExceptionWhenGivenNullAsDTO() {
      assertThrows(IllegalArgumentException.class, () -> commentService.updateComment(5L, null));
    }

    /**
     * The deleteCommentThrowsExceptionWhenGivenNullAsId method tests the deleteComment method of
     * the CommentService class. It verifies that the method throws an InvalidIdException when the
     * id is invalid.
     *
     * @throws InvalidIdException if the comment id is invalid
     */
    @Test
    void deleteCommentThrowsExceptionWhenGivenNullAsId() {
      assertThrows(IllegalArgumentException.class, () -> commentService.deleteComment(null));
    }

    /**
     * The deleteCommentThrowsExceptionWithInvalidId method tests the deleteComment method of the
     * CommentService class. It verifies that the method throws an InvalidIdException when the id is
     * invalid.
     *
     * @throws InvalidIdException if the comment id is invalid
     */
    @Test
    void deleteCommentThrowsExceptionWithInvalidId() {
      assertThrows(InvalidIdException.class, () -> commentService.deleteComment(5L));
    }
  }
}
