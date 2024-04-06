package com.idatt2105.backend.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The TagTests class is a test class that tests the Tag class. */
class TagTests {
  /**
   * This method tests that tags with the same tag name are considered equal. It verifies that the
   * equals method returns true for two tags with the same tag name.
   */
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

  /**
   * This method tests the constructor of the Tag class. It verifies that the constructor sets the
   * tag name correctly.
   */
  @Test
  void constructorSetsTagNameCorrectly() {
    Tag tag = new Tag("tag1");
    assertEquals("tag1", tag.getTagName());
  }

  /** The GetterTests class is a test class that tests the getters of the Tag class. */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the Tag class. It verifies that the method returns the
     * correct id.
     */
    @Test
    void getIdReturnsCorrectId() {
      Tag tag = new Tag();
      tag.setId(1L);
      assertEquals(1L, tag.getId());
    }

    /**
     * This method tests the getTagName method of the Tag class. It verifies that the method returns
     * the correct tag name.
     */
    @Test
    void getTagNameReturnsCorrectName() {
      Tag tag = new Tag();
      tag.setTagName("tag1");
      assertEquals("tag1", tag.getTagName());
    }

    /**
     * This method tests the getQuizzes method of the Tag class. It verifies that the method returns
     * the correct quizzes.
     */
    @Test
    void getQuizzesReturnsCorrectQuizzes() {
      Tag tag = new Tag();
      Set<Quiz> quizzes = new HashSet<>();
      quizzes.add(new Quiz());
      tag.setQuizzes(quizzes);
      assertEquals(quizzes, tag.getQuizzes());
    }
  }

  /** The SetterTests class is a test class that tests the setters of the Tag class. */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the Tag class. It verifies that the method sets the
     * correct id.
     */
    @Test
    void setIdSetsCorrectId() {
      Tag tag = new Tag();
      tag.setId(1L);
      assertEquals(1L, tag.getId());
    }

    /**
     * This method tests the setTagName method of the Tag class. It verifies that the method sets
     * the correct tag name.
     */
    @Test
    void setTagNameSetsCorrectName() {
      Tag tag = new Tag();
      tag.setTagName("tag1");
      assertEquals("tag1", tag.getTagName());
    }

    /**
     * This method tests the setQuizzes method of the Tag class. It verifies that the method sets
     * the correct quizzes.
     */
    @Test
    void setQuizzesSetsCorrectQuizzes() {
      Tag tag = new Tag();
      Set<Quiz> quizzes = new HashSet<>();
      quizzes.add(new Quiz());
      tag.setQuizzes(quizzes);
      assertEquals(quizzes, tag.getQuizzes());
    }
  }

  /**
   * This method tests that tags with the same tag name generate the same hash code. It verifies
   * that the hash code is the same for two tags with the same tag name.
   */
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
