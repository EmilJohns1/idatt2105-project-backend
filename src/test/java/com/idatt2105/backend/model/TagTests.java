package com.idatt2105.backend.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagTests {

  @Test
  void tagsWithSameTagNameAreConsideredEqual() {
    Tag tag1 = new Tag();
    tag1.setTagName("tag1");
    tag1.setId(1L);
    Tag tag2 = new Tag();
    tag2.setTagName("tag1");
    tag2.setId(2L);
    assertEquals(tag1, tag2);
  }

  @Test
  void constructorSetsTagNameCorrectly() {
    Tag tag = new Tag("tag1");
    assertEquals("tag1", tag.getTagName());
  }

  @Nested
  class GetterTests {
    @Test
    void getIdReturnsCorrectId() {
      Tag tag = new Tag();
      tag.setId(1L);
      assertEquals(1L, tag.getId());
    }

    @Test
    void getTagNameReturnsCorrectName() {
      Tag tag = new Tag();
      tag.setTagName("tag1");
      assertEquals("tag1", tag.getTagName());
    }

    @Test
    void getQuizzesReturnsCorrectQuizzes() {
      Tag tag = new Tag();
      Set<Quiz> quizzes = new HashSet<>();
      quizzes.add(new Quiz());
      tag.setQuizzes(quizzes);
      assertEquals(quizzes, tag.getQuizzes());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void setIdSetsCorrectId() {
      Tag tag = new Tag();
      tag.setId(1L);
      assertEquals(1L, tag.getId());
    }

    @Test
    void setTagNameSetsCorrectName() {
      Tag tag = new Tag();
      tag.setTagName("tag1");
      assertEquals("tag1", tag.getTagName());
    }

    @Test
    void setQuizzesSetsCorrectQuizzes() {
      Tag tag = new Tag();
      Set<Quiz> quizzes = new HashSet<>();
      quizzes.add(new Quiz());
      tag.setQuizzes(quizzes);
      assertEquals(quizzes, tag.getQuizzes());
    }
  }

  @Test
  void equalTagsGenerateTheSameHashcode() {
    Tag tag1 = new Tag();
    tag1.setTagName("tag1");
    tag1.setId(1L);
    Tag tag2 = new Tag();
    tag2.setTagName("tag1");
    tag2.setId(2L);
    assertEquals(tag1, tag2);
    assertEquals(tag1.hashCode(), tag2.hashCode());
  }
}
