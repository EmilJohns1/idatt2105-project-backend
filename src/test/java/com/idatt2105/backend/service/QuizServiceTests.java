package com.idatt2105.backend.service;

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
import com.idatt2105.backend.model.Quiz;
import com.idatt2105.backend.model.Tag;
import com.idatt2105.backend.repository.QuizRepository;
import com.idatt2105.backend.repository.TagRepository;

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
    void addTagsThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.addTags(null));
    }

    @Test
    void deleteTagsThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> quizService.deleteTags(null));
    }
  }
}
