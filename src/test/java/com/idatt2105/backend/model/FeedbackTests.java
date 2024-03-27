package com.idatt2105.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.enumerator.FeedbackType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeedbackTests {

  private Feedback feedback;

  @BeforeEach
  void setUp() {
    feedback = new Feedback();
  }

  @Nested
  class GetterTests {
    @Test
    void testGetId() {
      Long id = 1L;
      feedback.setId(id);
      assertEquals(id, feedback.getId());
    }

    @Test
    void testGetFirstName() {
      String firstName = "John";
      feedback.setFirstName(firstName);
      assertEquals(firstName, feedback.getFirstName());
    }

    @Test
    void testGetLastName() {
      String lastName = "Doe";
      feedback.setLastName(lastName);
      assertEquals(lastName, feedback.getLastName());
    }

    @Test
    void testGetEmail() {
      String email = "john.doe@example.com";
      feedback.setEmail(email);
      assertEquals(email, feedback.getEmail());
    }

    @Test
    void testGetFeedbackType() {
      FeedbackType feedbackType = FeedbackType.ASSISTANCE;
      feedback.setFeedbackType(feedbackType);
      assertEquals(feedbackType, feedback.getFeedbackType());
    }

    @Test
    void testGetContent() {
      String content = "This is a feedback content.";
      feedback.setContent(content);
      assertEquals(content, feedback.getContent());
    }

    @Test
    void testGetUser() {
      User user = new User();
      user.setId(1L);
      feedback.setUser(user);
      assertEquals(user, feedback.getUser());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetId() {
      Long id = 1L;
      feedback.setId(id);
      assertEquals(id, feedback.getId());
    }

    @Test
    void testSetFirstName() {
      String firstName = "John";
      feedback.setFirstName(firstName);
      assertEquals(firstName, feedback.getFirstName());
    }

    @Test
    void testSetLastName() {
      String lastName = "Doe";
      feedback.setLastName(lastName);
      assertEquals(lastName, feedback.getLastName());
    }

    @Test
    void testSetEmail() {
      String email = "john.doe@example.com";
      feedback.setEmail(email);
      assertEquals(email, feedback.getEmail());
    }

    @Test
    void testSetFeedbackType() {
      FeedbackType feedbackType = FeedbackType.ASSISTANCE;
      feedback.setFeedbackType(feedbackType);
      assertEquals(feedbackType, feedback.getFeedbackType());
    }

    @Test
    void testSetContent() {
      String content = "This is a feedback content.";
      feedback.setContent(content);
      assertEquals(content, feedback.getContent());
    }

    @Test
    void testSetUser() {
      User user = new User();
      user.setId(1L);
      feedback.setUser(user);
      assertEquals(user, feedback.getUser());
    }
  }
}
