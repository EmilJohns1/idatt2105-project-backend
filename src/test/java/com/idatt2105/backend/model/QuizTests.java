package com.idatt2105.backend.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The QuizTests class is a test class that tests the Quiz class. */
class QuizTests {
  /**
   * This method tests the getTags method of the Quiz class. It verifies that the method returns the
   * correct tags.
   */
  @Test
  void getTagsReturnsCorrectTags() {
    Quiz quiz = new Quiz();
    Set<Tag> tags = new HashSet<>();
    tags.add(new Tag("tag1"));
    tags.add(new Tag("tag2"));
    quiz.setTags(tags);
    assertEquals(tags, quiz.getTags());
  }

  /**
   * This method tests the setTags method of the Quiz class. It verifies that the method sets the
   * correct tags.
   */
  @Test
  void setTagsSetsCorrectTags() {
    Quiz quiz = new Quiz();
    Set<Tag> tags = new HashSet<>();
    tags.add(new Tag("tag1"));
    tags.add(new Tag("tag2"));
    quiz.setTags(tags);
    assertEquals(tags, quiz.getTags());
  }

  /**
   * This method tests the addTags method of the Quiz class. It verifies that the method adds the
   * correct tags.
   */
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

  /**
   * This method tests the removeTags method of the Quiz class. It verifies that the method removes
   * the correct tags.
   */
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
