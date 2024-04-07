package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.enumerator.FeedbackType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The FeedbackTests class is a test class that tests the Feedback class. */
class FeedbackTests {

  private Feedback feedback;

  @BeforeEach
  void setUp() {
    feedback = new Feedback();
  }

  /** The GetterTests class is a test class that tests the getters of the Feedback class. */
  @Nested
  class GetterTests {
    /**
     * This method tests the getId method of the Feedback class. It verifies that the method returns
     * the correct id.
     */
    @Test
    void testGetId() {
      Long id = 1L;
      feedback.setId(id);
      assertEquals(id, feedback.getId());
    }

    /**
     * This method tests the getFirstName method of the Feedback class. It verifies that the method
     * returns the correct first name.
     */
    @Test
    void testGetFirstName() {
      String firstName = "John";
      feedback.setFirstName(firstName);
      assertEquals(firstName, feedback.getFirstName());
    }

    /**
     * This method tests the getLastName method of the Feedback class. It verifies that the method
     * returns the correct last name.
     */
    @Test
    void testGetLastName() {
      String lastName = "Doe";
      feedback.setLastName(lastName);
      assertEquals(lastName, feedback.getLastName());
    }

    /**
     * This method tests the getEmail method of the Feedback class. It verifies that the method
     * returns the correct email.
     */
    @Test
    void testGetEmail() {
      String email = "john.doe@example.com";
      feedback.setEmail(email);
      assertEquals(email, feedback.getEmail());
    }

    /**
     * This method tests the getFeedbackType method of the Feedback class. It verifies that the
     * method returns the correct feedback type.
     */
    @Test
    void testGetFeedbackType() {
      FeedbackType feedbackType = FeedbackType.ASSISTANCE;
      feedback.setFeedbackType(feedbackType);
      assertEquals(feedbackType, feedback.getFeedbackType());
    }

    /**
     * This method tests the getContent method of the Feedback class. It verifies that the method
     * returns the correct content.
     */
    @Test
    void testGetContent() {
      String content = "This is a feedback content.";
      feedback.setContent(content);
      assertEquals(content, feedback.getContent());
    }

    /**
     * This method tests the getUser method of the Feedback class. It verifies that the method
     * returns the correct user.
     */
    @Test
    void testGetUser() {
      User user = new User();
      user.setId(1L);
      feedback.setUser(user);
      assertEquals(user, feedback.getUser());
    }
  }

  /** The SetterTests class is a test class that tests the setters of the Feedback class. */
  @Nested
  class SetterTests {
    /**
     * This method tests the setId method of the Feedback class. It verifies that the method sets
     * the correct id.
     */
    @Test
    void testSetId() {
      Long id = 1L;
      feedback.setId(id);
      assertEquals(id, feedback.getId());
    }

    /**
     * This method tests the setFirstName method of the Feedback class. It verifies that the method
     * sets the correct first name.
     */
    @Test
    void testSetFirstName() {
      String firstName = "John";
      feedback.setFirstName(firstName);
      assertEquals(firstName, feedback.getFirstName());
    }

    /**
     * This method tests the setLastName method of the Feedback class. It verifies that the method
     * sets the correct last name.
     */
    @Test
    void testSetLastName() {
      String lastName = "Doe";
      feedback.setLastName(lastName);
      assertEquals(lastName, feedback.getLastName());
    }

    /**
     * This method tests the setEmail method of the Feedback class. It verifies that the method sets
     * the correct email.
     */
    @Test
    void testSetEmail() {
      String email = "john.doe@example.com";
      feedback.setEmail(email);
      assertEquals(email, feedback.getEmail());
    }

    /**
     * This method tests the setFeedbackType method of the Feedback class. It verifies that the
     * method sets the correct feedback type.
     */
    @Test
    void testSetFeedbackType() {
      FeedbackType feedbackType = FeedbackType.ASSISTANCE;
      feedback.setFeedbackType(feedbackType);
      assertEquals(feedbackType, feedback.getFeedbackType());
    }

    /**
     * This method tests the setContent method of the Feedback class. It verifies that the method
     * sets the correct content.
     */
    @Test
    void testSetContent() {
      String content = "This is a feedback content.";
      feedback.setContent(content);
      assertEquals(content, feedback.getContent());
    }

    /**
     * This method tests the setUser method of the Feedback class. It verifies that the method sets
     * the correct user.
     */
    @Test
    void testSetUser() {
      User user = new User();
      user.setId(1L);
      feedback.setUser(user);
      assertEquals(user, feedback.getUser());
    }
  }

  /**
   * Test constructor with all parameters. It verifies that the constructor sets the correct values.
   */
  @Test
  void testConstructorWithAllParameters() {
    // Arrange
    Long id = 1L;
    String firstName = "John";
    String lastName = "Doe";
    String email = "john-doe@gmail.com";
    FeedbackType feedbackType = FeedbackType.ASSISTANCE;
    String content = "This is a feedback content.";
    User user = new User();
    user.setId(1L);

    // Act
    Feedback feedback = new Feedback(id, firstName, lastName, email, feedbackType, content, user);

    // Assert
    assertEquals(id, feedback.getId());
    assertEquals(firstName, feedback.getFirstName());
    assertEquals(lastName, feedback.getLastName());
    assertEquals(email, feedback.getEmail());
    assertEquals(feedbackType, feedback.getFeedbackType());
    assertEquals(content, feedback.getContent());
    assertEquals(user, feedback.getUser());
  }

  /**
   * This method tests the toString method of the Feedback class. It verifies that the method
   * returns the correct string.
   */
  @Test
  void testToString() {
    // Arrange
    feedback.setId(1L);
    feedback.setFirstName("John");
    feedback.setLastName("Doe");
    feedback.setEmail("john-doe@gmail.com");
    feedback.setFeedbackType(FeedbackType.ASSISTANCE);
    feedback.setContent("This is a feedback content.");

    // Act
    String result = feedback.toString();

    // Assert
    assertEquals(
        "Feedback(id=1, firstName=John, lastName=Doe, email=john-doe@gmail.com, "
            + "feedbackType=ASSISTANCE, content=This is a feedback content., user=null)",
        result);
  }

  /**
   * This method tests the equals method of the Feedback class. It verifies that the equals method
   * returns true when comparing two objects with the same values and false when comparing two
   * objects with different values.
   */
  @Test
  void testEquals() {
    // Arrange
    Feedback feedback1 = new Feedback();
    feedback1.setId(1L);
    feedback1.setFirstName("John");
    feedback1.setLastName("Doe");
    feedback1.setEmail("john-doe@gmail.com");
    feedback1.setFeedbackType(FeedbackType.ASSISTANCE);
    feedback1.setContent("This is a feedback content.");
    User user1 = new User();
    user1.setId(1L);
    feedback1.setUser(user1);

    Feedback feedback2 = new Feedback();
    feedback2.setId(1L);
    feedback2.setFirstName("John");
    feedback2.setLastName("Doe");
    feedback2.setEmail("john-doe@gmail.com");
    feedback2.setFeedbackType(FeedbackType.ASSISTANCE);
    feedback2.setContent("This is a feedback content.");
    feedback2.setUser(user1);

    // Act
    boolean actual = feedback1.equals(feedback2);

    // Assert
    assertEquals(true, actual);
    assertTrue(feedback1.canEqual(feedback2));
  }

  /**
   * This method tests the hashCode method of the Feedback class. It verifies that the hashCode
   * method returns the same value for two objects with the same values and different values for two
   * objects with different values.
   */
  @Test
  void testHashCode() {
    // Arrange
    Feedback feedback1 = new Feedback();
    feedback1.setId(1L);
    feedback1.setFirstName("John");
    feedback1.setLastName("Doe");
    feedback1.setEmail("john-doe@gmail.com");
    feedback1.setFeedbackType(FeedbackType.ASSISTANCE);
    feedback1.setContent("This is a feedback content.");
    User user1 = new User();
    user1.setId(1L);
    feedback1.setUser(user1);

    Feedback feedback2 = new Feedback();
    feedback2.setId(1L);
    feedback2.setFirstName("John");
    feedback2.setLastName("Doe");
    feedback2.setEmail("john-doe@gmail.com");
    feedback2.setFeedbackType(FeedbackType.ASSISTANCE);
    feedback2.setContent("This is a feedback content.");
    feedback2.setUser(user1);

    // Act
    int hashCode1 = feedback1.hashCode();
    int hashCode2 = feedback2.hashCode();

    // Assert
    assertEquals(hashCode1, hashCode2);
  }
}
