package com.idatt2105.backend.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.idatt2105.backend.model.MultipleChoiceQuestion;
import com.idatt2105.backend.model.QuestionType;
import com.idatt2105.backend.model.TrueOrFalseQuestion;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.model.Tag;

class QuestionDTOTests {
  private List<Tag> tags;
  @BeforeEach
  void setUp() {
    tags = new ArrayList<>();
    Tag tag1 = new Tag();
    Tag tag2 = new Tag();
    tag1.setId(1L);
    tag2.setId(2L);
    tags.add(tag1);
    tags.add(null);
    tags.add(tag2);
  }

  @Nested
  class Getters {
    private QuestionDTO question;

    @BeforeEach
    void setUp() {
      question = new QuestionDTO();
      question.setQuizId(1L);
      question.setQuestionText("Test question");
      question.setType(QuestionType.MULTIPLE_CHOICE);
      question.setQuestionId(2L);
      question.setMediaUrl("test.com");
      question.setCategory("Test category");
      question.addAllTags(tags);
      question.isCorrect(true);
    }

    @Test
    void getQuizIdReturnsQuizId() {
      assertEquals(1L, question.getQuizId());
    }

    @Test
    void getQuestionTextReturnsQuestionText() {
      assertEquals("Test question", question.getQuestionText());
    }

    @Test
    void getTypeReturnsType() {
      assertEquals(QuestionType.MULTIPLE_CHOICE, question.getType());
    }

    @Test
    void getQuestionIdReturnsQuestionId() {
      assertEquals(2L, question.getQuestionId());
    }

    @Test
    void getMediaUrlReturnsMediaUrl() {
      assertEquals("test.com", question.getMediaUrl());
    }

    @Test
    void getCategoryReturnsCategory() {
      assertEquals("Test category", question.getCategory());
    }

    @Test
    void getTagsReturnsTags() {
      assertEquals(2, question.getTags().size());
      assertTrue(question.getTags().contains(tags.get(0)));
      assertTrue(question.getTags().contains(tags.get(2)));
    }

    @Test
    void isCorrectReturnsCorrect() {
      assertTrue(question.isCorrect());
    }

    @Test
    void getAllTagIdsReturnsAllTagIds() {
      QuestionDTO questionDTO = new QuestionDTO();
      questionDTO.addAllTags(tags);
      List<Long> tagIds = questionDTO.getAllTagIds();
      assertEquals(2, tagIds.size());
      assertTrue(tagIds.contains(1L));
      assertTrue(tagIds.contains(2L));
    }

    @Test
    void instantiateQuestionReturnsTrueOrFalseQuestion() {
      QuestionDTO questionDTO = new QuestionDTO();
      questionDTO.setType(QuestionType.TRUE_OR_FALSE);
      assertInstanceOf(TrueOrFalseQuestion.class, questionDTO.instantiateQuestion());
    }

    @Test
    void instantiateQuestionReturnsMultipleChoiceQuestion() {
      QuestionDTO questionDTO = new QuestionDTO();
      questionDTO.setType(QuestionType.MULTIPLE_CHOICE);
      assertInstanceOf(MultipleChoiceQuestion.class, questionDTO.instantiateQuestion());
    }
  }

  @Nested
  class Setters {
    private QuestionDTO question;

    @BeforeEach
    void setUp() {
      question = new QuestionDTO();
    }

    @Test
    void setQuizIdSetsQuizId() {
      question.setQuizId(1L);
      assertEquals(1L, question.getQuizId());
    }

    @Test
    void setQuestionTextSetsQuestionText() {
      question.setQuestionText("Test question");
      assertEquals("Test question", question.getQuestionText());
    }

    @Test
    void setTypeSetsType() {
      question.setType(QuestionType.MULTIPLE_CHOICE);
      assertEquals(QuestionType.MULTIPLE_CHOICE, question.getType());
    }

    @Test
    void setQuestionIdSetsQuestionId() {
      question.setQuestionId(2L);
      assertEquals(2L, question.getQuestionId());
    }

    @Test
    void setMediaUrlSetsMediaUrl() {
      question.setMediaUrl("test.com");
      assertEquals("test.com", question.getMediaUrl());
    }

    @Test
    void setCategorySetsCategory() {
      question.setCategory("Test category");
      assertEquals("Test category", question.getCategory());
    }

    @Test
    void addAllTagsAddsAllTags() {
      QuestionDTO questionDTO = new QuestionDTO();
      questionDTO.addAllTags(tags);
      assertEquals(2, questionDTO.getTags().size());
      assertTrue(questionDTO.getTags().contains(tags.get(0)));
      assertTrue(questionDTO.getTags().contains(tags.get(2)));
    }

    @Test
    void setCorrectSetsCorrect() {
      question.isCorrect(true);
      assertTrue(question.isCorrect());
    }

    @Test
    void addAllTagsIgnoresNullValues() {
      QuestionDTO questionDTO = new QuestionDTO();
      questionDTO.addAllTags(tags);
      assertEquals(2, questionDTO.getTags().size());
      assertFalse(questionDTO.getTags().contains(null));
    }
  }
}
