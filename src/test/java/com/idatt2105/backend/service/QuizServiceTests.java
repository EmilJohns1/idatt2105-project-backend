package com.idatt2105.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.Tag;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.TagRepository;
import com.idatt2105.backend.repository.UserRepository;
import com.idatt2105.backend.util.InvalidIdException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuizServiceTests {
  @InjectMocks private QuizService quizService;

  @Mock private TagRepository tagRepository;
  @Mock private QuizRepository quizRepository;
  @Mock private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    when(tagRepository.existsByTagName(any())).thenReturn(false);
    when(tagRepository.save(any(Tag.class))).thenAnswer(returnsFirstArg());
    when(quizRepository.findById(1L)).thenReturn(Optional.of(new Quiz()));
    when(quizRepository.save(any(Quiz.class))).thenAnswer(returnsFirstArg());
  }

  @Nested
  class BasicFunctionality {
    @Test
    void getAllQuizzes() {
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      quiz.setTitle("Quiz");
      when(quizRepository.findAll()).thenReturn(List.of(quiz));
      List<QuizDTO> actual = quizService.getAllQuizzes();
      assertEquals(new QuizDTO(quiz), actual.get(0));
    }

    @Test
    void getQuizById() {
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      quiz.setTitle("Quiz");
      when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));
      QuizDTO actual = quizService.getQuizById(1L);
      assertEquals(new QuizDTO(quiz), actual);
    }

    @Test
    void saveStoresEntityInRepository() {
      Quiz input = new Quiz();
      input.setId(1L);
      input.setTitle("Quiz");
      QuizDTO actual = quizService.save(input);
      assertEquals(new QuizDTO(input), actual);
      verify(quizRepository).save(input);
    }

    @Test
    void deleteQuizCallsDeleteByIdOnRepository() {
      quizService.deleteQuiz(1L);
      verify(quizRepository).deleteById(1L);
    }

    @Test
    void updateQuizCallsRepository() {
      QuizDTO dto = new QuizDTO();
      dto.setId(1L);
      dto.setTitle("New Title");
      dto.setDescription("New Description");
      quizService.updateQuiz(1L, dto);
      verify(quizRepository).save(any(Quiz.class));
    }

    @Test
    void addUserToQuizCallsSaveOnQuizRepository() {
      Quiz quiz = new Quiz();
      User user = new User();
      user.setQuizzes(new HashSet<>());
      quiz.setUsers(new HashSet<>());
      when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));
      when(userRepository.findById(1L)).thenReturn(Optional.of(user));
      quizService.addUserToQuiz(1L, 1L);
      verify(quizRepository).save(quiz);
    }

    @Test
    void deleteUserFromQuizCallsSaveOnQuizRepository() {
      Quiz quiz = new Quiz();
      User user = new User();
      user.setQuizzes(new HashSet<>());
      quiz.setUsers(new HashSet<>());
      when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));
      when(userRepository.findById(1L)).thenReturn(Optional.of(user));
      quizService.addUserToQuiz(1L, 1L);
      verify(quizRepository).save(quiz);
    }

    @Test
    void getQuizByTitle() {
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      quiz.setTitle("Quiz");
      when(quizRepository.findByTitle("Quiz")).thenReturn(Optional.of(quiz));
      QuizDTO actual = quizService.getQuizByTitle("Quiz");
      assertEquals(new QuizDTO(quiz), actual);
    }

    @Test
    void getUsersByQuizId() {
      User user = new User();
      user.setId(1L);
      user.setUsername("User");
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      quiz.setUsers(new HashSet<>(List.of(user)));
      when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));

      Set<UserDTO> actual = quizService.getUsersByQuizId(1L);
      assertEquals(new UserDTO(user), actual.iterator().next());
    }

    @Test
    void addTagsAddsNonExistingTagsToTagRepository() {
      QuizDTO input = new QuizDTO();
      input.setId(1L);
      Tag tag = new Tag();
      tag.setTagName("Test");
      input.setTags(Set.of(tag));
      quizService.addTags(input);
      verify(tagRepository).save(any());
    }

    @Test
    void addTagsDoesNotAddExistingTagsToRepository() {
      QuizDTO input = new QuizDTO();
      input.setId(1L);
      Tag tag = new Tag();
      tag.setTagName("Test");
      when(tagRepository.existsByTagName("Test")).thenReturn(true);
      when(tagRepository.findByTagName("Test")).thenReturn(Optional.of(tag));
      input.setTags(Set.of(tag));
      quizService.addTags(input);
      verify(tagRepository, never()).save(any());
    }

    @Test
    void addTagsAddsTagsToQuiz() {
      QuizDTO input = new QuizDTO();
      input.setId(1L);
      Tag tag = new Tag();
      tag.setTagName("Test");
      input.setTags(Set.of(tag));
      QuizDTO actual = quizService.addTags(input);
      assertEquals(actual.getTags().iterator().next().getTagName(), tag.getTagName());
    }

    @Test
    void deleteTagsDeletesSpecifiedTagsFromQuiz() {
      Quiz quiz = new Quiz();

      // Add tags tag1 and tag2 to quiz
      Tag tag1 = new Tag();
      Tag tag2 = new Tag();
      tag1.setTagName("Test1");
      tag2.setTagName("Test2");
      List<Tag> tags = List.of(tag1, tag2);

      quiz.setId(3L);
      quiz.addTags(tags);
      when(quizRepository.findById(3L)).thenReturn(Optional.of(quiz));

      // Remove tag1 from quiz
      QuizDTO input = new QuizDTO();
      input.setId(3L);
      input.setTags(Set.of(tag1));

      QuizDTO actual = quizService.deleteTags(input);

      // Quiz contains tag2 but not tag1
      assertEquals(1, actual.getTags().size());
      assertFalse(actual.getTags().contains(tag1));
      assertTrue(actual.getTags().contains(tag2));
    }
  }

  @Nested
  class InvalidParameters {
    @Test
    void getQuizByIdThrowsExceptionWhenGivenInvalidId() {
      assertThrows(InvalidIdException.class, () -> quizService.getQuizById(2L));
    }

    @Test
    void getQuizByIdThrowsExceptionWhenGivenNull() {
      assertThrows(IllegalArgumentException.class, () -> quizService.getQuizById(null));
    }

    @Test
    void saveThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.save(null));
    }

    @Test
    void deleteQuizThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.deleteQuiz(null));
    }

    @Test
    void updateQuizThrowsExceptionWhenGivenNullAsIdParameter() {
      QuizDTO dto = new QuizDTO();
      assertThrows(IllegalArgumentException.class, () -> quizService.updateQuiz(null, dto));
    }

    @Test
    void updateQuizThrowsExceptionWhenGivenNullAsDtoParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.updateQuiz(1L, null));
    }

    @Test
    void addUserToQuizThrowsExceptionWhenUserIdIsNull() {
      assertThrows(IllegalArgumentException.class, () -> quizService.addUserToQuiz(null, 1L));
    }

    @Test
    void addUserToQuizThrowsExceptionWhenQuizIdIsNull() {
      assertThrows(IllegalArgumentException.class, () -> quizService.addUserToQuiz(1L, null));
    }

    @Test
    void addUserToQuizThrowsExceptionWhenUserIsNotFound() {
      Quiz quiz = new Quiz();
      quiz.setUsers(new HashSet<>());
      when(quizRepository.findById(2L)).thenReturn(Optional.of(quiz));
      assertThrows(InvalidIdException.class, () -> quizService.addUserToQuiz(1L, 1L));
    }

    @Test
    void deleteUserFromQuizThrowsExceptionWhenUserIdIsNull() {
      assertThrows(IllegalArgumentException.class, () -> quizService.removeUserFromQuiz(null, 1L));
    }

    @Test
    void deleteUserFromQuizThrowsExceptionWhenQuizIdIsNull() {
      assertThrows(IllegalArgumentException.class, () -> quizService.removeUserFromQuiz(1L, null));
    }

    @Test
    void getQuizByTitleThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.getQuizByTitle(null));
    }

    @Test
    void getUsersByQuizIdThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.getUsersByQuizId(null));
    }

    @Test
    void addTagsThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.addTags(null));
    }

    @Test
    void deleteTagsThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.deleteTags(null));
    }
  }
}
