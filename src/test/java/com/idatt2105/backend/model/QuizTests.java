package com.idatt2105.backend.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuizTests {
  @Test
  void getTagsReturnsCorrectTags() {
    Quiz quiz = new Quiz();
    Set<Tag> tags = new HashSet<>();
    tags.add(new Tag("tag1"));
    tags.add(new Tag("tag2"));
    quiz.setTags(tags);
    assertEquals(tags, quiz.getTags());
  }

  @Test
  void setTagsSetsCorrectTags() {
    Quiz quiz = new Quiz();
    Set<Tag> tags = new HashSet<>();
    tags.add(new Tag("tag1"));
    tags.add(new Tag("tag2"));
    quiz.setTags(tags);
    assertEquals(tags, quiz.getTags());
  }

  @Test
  void addTagsCorrectlyAddsTags() {
    Quiz quiz = new Quiz();
    Set<Tag> tags = new HashSet<>();
    tags.add(new Tag("tag1"));
    tags.add(new Tag("tag2"));
    tags.add(null);
    quiz.addTags(tags);
    assertTrue(quiz.getTags().contains(new Tag("tag1")));
    assertTrue(quiz.getTags().contains(new Tag("tag2")));
    assertFalse(quiz.getTags().contains(null));
  }

  @Test
  void deleteTagsCorrectlyDeletesTags() {
    Quiz quiz = new Quiz();
    Set<Tag> tags = new HashSet<>();
    Tag tag1 = new Tag("tag1");
    Tag tag2 = new Tag("tag2");
    tags.add(tag1);
    tags.add(tag2);
    quiz.setTags(tags);
    quiz.removeTags(Set.of(tag1));
    assertFalse(quiz.getTags().contains(tag1));
    assertTrue(quiz.getTags().contains(tag2));
  }
}
