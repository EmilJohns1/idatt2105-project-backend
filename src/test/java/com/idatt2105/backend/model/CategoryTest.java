package com.idatt2105.backend.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** The CategoryTest class is a test class that tests the Category class. */
class CategoryTest {

  /**
   * This method tests the getters and setters of the Category class. It verifies that the getters
   * return the correct values and that the setters set the correct values.
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange
    Category category = new Category();
    Long id = 1L;
    String name = "Test Category";
    String pictureUrl = "https://example.com/image.jpg";
    Quiz quiz1 = new Quiz();
    Quiz quiz2 = new Quiz();
    Set<Quiz> quizzes = new HashSet<>();
    quizzes.add(quiz1);
    quizzes.add(quiz2);

    // Act
    category.setId(id);
    category.setName(name);
    category.setPictureUrl(pictureUrl);
    category.setQuizzes(quizzes);

    // Assert
    assertEquals(id, category.getId());
    assertEquals(name, category.getName());
    assertEquals(pictureUrl, category.getPictureUrl());
  }

  /**
   * This method tests the toString method of the Category class. It verifies that the toString
   * method returns the correct string representation of the object.
   */
  @Test
  public void testToString() {
    // Arrange
    Category category = new Category();
    category.setId(1L);
    category.setName("Test Category");
    category.setPictureUrl("https://example.com/image.jpg");

    // Act
    String result = category.toString();

    // Assert
    assertTrue(result.contains("1"));
    assertTrue(result.contains("Test Category"));
    assertTrue(result.contains("https://example.com/image.jpg"));
  }

  /**
   * This method tests the equals method of the Category class. It verifies that the equals method
   * returns true when comparing two objects with the same values and false when comparing two
   * objects with different values.
   */
  @Test
  public void testEquals() {
    // Arrange
    Category category1 = new Category();
    category1.setId(1L);
    category1.setName("Test Category");
    category1.setPictureUrl("https://example.com/image.jpg");

    Category category2 = new Category();
    category2.setId(1L);
    category2.setName("Test Category");
    category2.setPictureUrl("https://example.com/image.jpg");

    Category category3 = new Category();
    category3.setId(2L);
    category3.setName("Test Category 2");
    category3.setPictureUrl("https://example.com/image2.jpg");

    // Act
    boolean result1 = category1.equals(category2);
    boolean result2 = category1.equals(category3);

    // Assert
    assertTrue(result1);
    assertFalse(result2);
    assertTrue(category1.canEqual(category3));
  }

  /**
   * This method tests the hashCode method of the Category class. It verifies that the hashCode
   * method returns the same value for two objects with the same values and different values for two
   * objects with different values.
   */
  @Test
  public void testHashCode() {
    // Arrange
    Category category1 = new Category();
    category1.setId(1L);
    category1.setName("Test Category");
    category1.setPictureUrl("https://example.com/image.jpg");

    Category category2 = new Category();
    category2.setId(1L);
    category2.setName("Test Category");
    category2.setPictureUrl("https://example.com/image.jpg");

    Category category3 = new Category();
    category3.setId(2L);
    category3.setName("Test Category 2");
    category3.setPictureUrl("https://example.com/image2.jpg");

    // Act
    int hashCode1 = category1.hashCode();
    int hashCode2 = category2.hashCode();
    int hashCode3 = category3.hashCode();

    // Assert
    assertEquals(hashCode1, hashCode2);
    assertNotEquals(hashCode1, hashCode3);
  }
}
