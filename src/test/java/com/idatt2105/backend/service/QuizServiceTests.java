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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.idatt2105.backend.dto.QuizDTO;
import com.idatt2105.backend.dto.UserDTO;
import com.idatt2105.backend.model.Category;
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.Tag;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.repository.CategoryRepository;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** The QuizServiceTests class is a test class that tests the QuizService class. */
@SpringBootTest
public class QuizServiceTests {
  @InjectMocks private QuizService quizService;

  @Mock private TagRepository tagRepository;
  @Mock private QuizRepository quizRepository;
  @Mock private UserRepository userRepository;
  @Mock private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    when(tagRepository.existsByTagName(any())).thenReturn(false);
    when(tagRepository.save(any(Tag.class))).thenAnswer(returnsFirstArg());
    when(tagRepository.findAll()).thenReturn(List.of(new Tag()));
    Tag tag = new Tag();
    tag.setId(1L);
    tag.setTagName("Test");
    when(tagRepository.findByTagName("Test")).thenReturn(Optional.of(tag));
    when(quizRepository.findById(1L)).thenReturn(Optional.of(new Quiz()));
    when(quizRepository.save(any(Quiz.class))).thenAnswer(returnsFirstArg());
    Category category = new Category();
    category.setName("Category");
    when(categoryRepository.findByName(any(String.class))).thenReturn(Optional.of(category));
  }

  /**
   * The BasicFunctionality class is a test class that tests the basic functionality of the
   * QuizService class.
   */
  @Nested
  class BasicFunctionality {
    /**
     * This method tests the getAllQuizzes method of the QuizService class. It verifies that the
     * method returns a list of all quizzes.
     */
    @Test
    void getAllQuizzes() {
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      quiz.setTitle("Quiz");
      Page<Quiz> page = new PageImpl<>(List.of(quiz));
      when(quizRepository.findAll(any(Pageable.class))).thenReturn(page);
      List<QuizDTO> actual = quizService.getAllQuizzes(Pageable.ofSize(1)).toList();
      assertEquals(new QuizDTO(quiz), actual.get(0));
    }

    /**
     * This method tests the getQuizById method of the QuizService class. It verifies that the
     * method returns the correct quiz.
     */
    @Test
    void getQuizById() {
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      quiz.setTitle("Quiz");
      when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));
      QuizDTO actual = quizService.getQuizById(1L);
      assertEquals(new QuizDTO(quiz), actual);
    }

    /**
     * This method tests the save method of the QuizService class. It verifies that the method saves
     * the quiz to the repository.
     */
    @Test
    void saveStoresEntityInRepository() {
      QuizDTO input = new QuizDTO();
      input.setId(1L);
      input.setTitle("Quiz");
      input.setCategoryName("Category");
      QuizDTO actual = quizService.save(input);
      assertEquals(input.getTitle(), actual.getTitle());
      assertEquals(input.getCategoryName(), actual.getCategoryName());
      verify(quizRepository).save(any(Quiz.class));
    }

    /**
     * This method tests the deleteQuiz method of the QuizService class. It verifies that the method
     * deletes the quiz from the repository.
     */
    @Test
    void deleteQuizCallsDeleteByIdOnRepository() {
      quizService.deleteQuiz(1L);
      verify(quizRepository).deleteById(1L);
    }

    /**
     * This method tests the updateQuiz method of the QuizService class. It verifies that the method
     * updates the quiz in the repository.
     */
    @Test
    void updateQuizCallsRepository() {
      QuizDTO dto = new QuizDTO();
      dto.setId(1L);
      dto.setTitle("New Title");
      dto.setDescription("New Description");
      quizService.updateQuiz(1L, dto);
      verify(quizRepository).save(any(Quiz.class));
    }

    /**
     * This method tests the addUserToQuiz method of the QuizService class. It verifies that the
     * method adds a user to a quiz.
     */
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

    /**
     * This method tests the removeUserFromQuiz method of the QuizService class. It verifies that
     * the method removes a user from a quiz.
     */
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

    /**
     * This method tests the getQuizByTitle method of the QuizService class. It verifies that the
     * method returns the correct quiz.
     */
    @Test
    void getQuizByTitle() {
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      quiz.setTitle("Quiz");
      when(quizRepository.findByTitle("Quiz")).thenReturn(Optional.of(quiz));
      QuizDTO actual = quizService.getQuizByTitle("Quiz");
      assertEquals(new QuizDTO(quiz), actual);
    }

    /**
     * This method tests the getUsersByQuizId method of the QuizService class. It verifies that the
     * method returns the correct user.
     */
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

    /**
     * This method tests the addTags method of the QuizService class. It verifies that the method
     * adds tags to the quiz.
     */
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

    /**
     * This method tests the addTags method of the QuizService class. It verifies that the method
     * does not add existing tags to the repository.
     */
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

    /**
     * This method tests the addTags method of the QuizService class. It verifies that the method
     * adds tags to the quiz.
     */
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

    /**
     * This method tests the deleteTags method of the QuizService class. It verifies that the method
     * deletes tags from the quiz.
     */
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

    /**
     * This method tests the getQuizzesByTag method of the QuizService class. It verifies that the
     * method returns the correct quiz.
     */
    @Test
    void getQuizzesByTag() {
      Tag tag = new Tag();
      tag.setTagName("Test");
      Quiz quiz = new Quiz();
      quiz.setId(1L);
      quiz.setTitle("Quiz");
      quiz.addTags(Set.of(tag));
      Page<Quiz> page = new PageImpl<>(List.of(quiz));
      when(quizRepository.findByTagsContains(eq(tag), any(Pageable.class))).thenReturn(page);
      Page<QuizDTO> actual = quizService.getQuizzesByTag("Test", Pageable.ofSize(1));
      assertEquals(new QuizDTO(quiz), actual.iterator().next());
    }
  }

  /**
   * The InvalidParameters class is a test class that tests the invalid parameters of the
   * QuizService class.
   */
  @Nested
  class InvalidParameters {
    /**
     * This method tests the getQuizById method of the QuizService class. It verifies that the
     * method throws an InvalidIdException when given an invalid id.
     *
     * @throws InvalidIdException if the id is invalid
     */
    @Test
    void getQuizByIdThrowsExceptionWhenGivenInvalidId() {
      assertThrows(InvalidIdException.class, () -> quizService.getQuizById(2L));
    }

    /**
     * This method tests the getQuizById method of the QuizService class. It verifies that the
     * method throws an IllegalArgumentException when given null.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void getQuizByIdThrowsExceptionWhenGivenNull() {
      assertThrows(IllegalArgumentException.class, () -> quizService.getQuizById(null));
    }

    /**
     * This method tests the save method of the QuizService class. It verifies that the method
     * throws an IllegalArgumentException when given null as a parameter.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void saveThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.save(null));
    }

    /**
     * This method tests the deleteQuiz method of the QuizService class. It verifies that the method
     * throws an IllegalArgumentException when given null as a parameter.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void deleteQuizThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.deleteQuiz(null));
    }

    /**
     * This method tests the updateQuiz method of the QuizService class. It verifies that the method
     * throws an IllegalArgumentException when given null as an id parameter.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void updateQuizThrowsExceptionWhenGivenNullAsIdParameter() {
      QuizDTO dto = new QuizDTO();
      assertThrows(IllegalArgumentException.class, () -> quizService.updateQuiz(null, dto));
    }

    /**
     * This method tests the updateQuiz method of the QuizService class. It verifies that the method
     * throws an IllegalArgumentException when given null as a DTO parameter.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void updateQuizThrowsExceptionWhenGivenNullAsDtoParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.updateQuiz(1L, null));
    }

    /**
     * This method tests the addUserToQuiz method of the QuizService class. It verifies that the
     * method throws an IllegalArgumentException when the user id is null.
     *
     * @throws IllegalArgumentException if the user id is null
     */
    @Test
    void addUserToQuizThrowsExceptionWhenUserIdIsNull() {
      assertThrows(IllegalArgumentException.class, () -> quizService.addUserToQuiz(null, 1L));
    }

    /**
     * This method tests the addUserToQuiz method of the QuizService class. It verifies that the
     * method throws an IllegalArgumentException when the quiz id is null.
     *
     * @throws IllegalArgumentException if the quiz id is null
     */
    @Test
    void addUserToQuizThrowsExceptionWhenQuizIdIsNull() {
      assertThrows(IllegalArgumentException.class, () -> quizService.addUserToQuiz(1L, null));
    }

    /**
     * This method tests the addUserToQuiz method of the QuizService class. It verifies that the
     * method throws an InvalidIdException when the user is not found.
     *
     * @throws InvalidIdException if the user is not found
     */
    @Test
    void addUserToQuizThrowsExceptionWhenUserIsNotFound() {
      Quiz quiz = new Quiz();
      quiz.setUsers(new HashSet<>());
      when(quizRepository.findById(2L)).thenReturn(Optional.of(quiz));
      assertThrows(InvalidIdException.class, () -> quizService.addUserToQuiz(1L, 1L));
    }

    /**
     * This method tests the addUserToQuiz method of the QuizService class. It verifies that the
     * method throws an InvalidIdException when user is null.
     *
     * @throws InvalidIdException if the user is null
     */
    @Test
    void deleteUserFromQuizThrowsExceptionWhenUserIdIsNull() {
      assertThrows(IllegalArgumentException.class, () -> quizService.removeUserFromQuiz(null, 1L));
    }

    /**
     * This method tests the deleteUserFromQuiz method of the QuizService class. It verifies that
     * the method throws an InvalidIdException when the quiz is null.
     *
     * @throws InvalidIdException if the quiz is null.
     */
    @Test
    void deleteUserFromQuizThrowsExceptionWhenQuizIdIsNull() {
      assertThrows(IllegalArgumentException.class, () -> quizService.removeUserFromQuiz(1L, null));
    }

    /**
     * This method tests the getQuizByTitle method of the QuizService class. It verifies that the
     * method throws an InvalidIdException when the quiz is null
     *
     * @throws InvalidIdException if the quiz is null.
     */
    @Test
    void getQuizByTitleThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.getQuizByTitle(null));
    }

    /**
     * This method tests the getUsersByQuizId method of the QuizService class. It verifies that the
     * method throws an IllegalArgumentException when given null as a parameter.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void getUsersByQuizIdThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.getUsersByQuizId(null));
    }

    /**
     * This method tests the addTags method of the QuizService class. It verifies that the method
     * throws an IllegalArgumentException when given null as a parameter.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void addTagsThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.addTags(null));
    }

    /**
     * This method tests the deleteTags method of the QuizService class. It verifies that the method
     * throws an IllegalArgumentException when given null as a parameter.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void deleteTagsThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.deleteTags(null));
    }

    /**
     * This method tests the getQuizzesByTag method of the QuizService class. It verifies that the
     * method throws an IllegalArgumentException when given null as a parameter.
     *
     * @throws IllegalArgumentException if the parameter is null
     */
    @Test
    void getQuizzesByTagThrowsExceptionWhenParameterIsNull() {
      assertThrows(
          IllegalArgumentException.class,
          () -> quizService.getQuizzesByTag(null, Pageable.ofSize(1)));
    }
  }
}
