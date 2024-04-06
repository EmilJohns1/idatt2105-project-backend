package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.enumerator.FeedbackType;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
