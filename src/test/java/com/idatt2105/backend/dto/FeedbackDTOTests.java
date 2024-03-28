package com.idatt2105.backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.enumerator.FeedbackType;
import com.idatt2105.backend.model.Feedback;
import com.idatt2105.backend.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeedbackDTOTests {
  private FeedbackDTO feedbackDTO;

  @BeforeEach
  void setUp() {
    feedbackDTO = new FeedbackDTO();
  }

  @Nested
  class Constructor {
    @Test
    void allArgsConstructorInstantiatesObject() {
      FeedbackDTO dto =
          new FeedbackDTO(
              "John", "Smith", "test@gmail.com", FeedbackType.ASSISTANCE, "Test message", 1L);
      assertEquals("John", dto.getFirstName());
      assertEquals("Smith", dto.getLastName());
      assertEquals("test@gmail.com", dto.getEmail());
      assertEquals(FeedbackType.ASSISTANCE, dto.getFeedbackType());
      assertEquals("Test message", dto.getContent());
      assertEquals(1L, dto.getUserId());
    }
  }

  @Test
  void constructorWithFeedbackParameter() {
    Feedback input = new Feedback();
    input.setFirstName("John");
    input.setLastName("Smith");
    input.setEmail("test@gmail.com");
    input.setFeedbackType(FeedbackType.ASSISTANCE);
    input.setContent("Test message");
    User user = new User();
    user.setId(1L);
    input.setUser(user);
    FeedbackDTO dto = new FeedbackDTO(input);
    assertEquals("John", dto.getFirstName());
    assertEquals("Smith", dto.getLastName());
    assertEquals("test@gmail.com", dto.getEmail());
    assertEquals(FeedbackType.ASSISTANCE, dto.getFeedbackType());
    assertEquals("Test message", dto.getContent());
    assertEquals(1L, dto.getUserId());
  }

  @Test
  void toEntity() {
    FeedbackDTO dto = new FeedbackDTO();
    dto.setFirstName("John");
    dto.setLastName("Smith");
    dto.setEmail("test@gmail.com");
    dto.setFeedbackType(FeedbackType.ASSISTANCE);
    dto.setContent("Test message");
    Feedback feedback = dto.convertToEntity();
    assertEquals("John", feedback.getFirstName());
    assertEquals("Smith", feedback.getLastName());
    assertEquals("test@gmail.com", feedback.getEmail());
    assertEquals(FeedbackType.ASSISTANCE, feedback.getFeedbackType());
    assertEquals("Test message", feedback.getContent());
  }

  @Nested
  class GetterTests {
    @Test
    void testGetFirstName() {
      String firstName = "John";
      feedbackDTO.setFirstName(firstName);
      assertEquals(firstName, feedbackDTO.getFirstName());
    }

    @Test
    void testGetLastName() {
      String lastName = "Doe";
      feedbackDTO.setLastName(lastName);
      assertEquals(lastName, feedbackDTO.getLastName());
    }

    @Test
    void testGetEmail() {
      String email = "john.doe@example.com";
      feedbackDTO.setEmail(email);
      assertEquals(email, feedbackDTO.getEmail());
    }

    @Test
    void testGetFeedbackType() {
      FeedbackType feedbackType = FeedbackType.ASSISTANCE;
      feedbackDTO.setFeedbackType(feedbackType);
      assertEquals(feedbackType, feedbackDTO.getFeedbackType());
    }

    @Test
    void testGetContent() {
      String content = "This is a feedback content.";
      feedbackDTO.setContent(content);
      assertEquals(content, feedbackDTO.getContent());
    }

    @Test
    void testGetUserId() {
      Long userId = 1L;
      feedbackDTO.setUserId(userId);
      assertEquals(userId, feedbackDTO.getUserId());
    }
  }

  @Nested
  class SetterTests {
    @Test
    void testSetFirstName() {
      String firstName = "John";
      feedbackDTO.setFirstName(firstName);
      assertEquals(firstName, feedbackDTO.getFirstName());
    }

    @Test
    void testSetLastName() {
      String lastName = "Doe";
      feedbackDTO.setLastName(lastName);
      assertEquals(lastName, feedbackDTO.getLastName());
    }

    @Test
    void testSetEmail() {
      String email = "john.doe@example.com";
      feedbackDTO.setEmail(email);
      assertEquals(email, feedbackDTO.getEmail());
    }

    @Test
    void testSetFeedbackType() {
      FeedbackType feedbackType = FeedbackType.ASSISTANCE;
      feedbackDTO.setFeedbackType(feedbackType);
      assertEquals(feedbackType, feedbackDTO.getFeedbackType());
    }

    @Test
    void testSetContent() {
      String content = "This is a feedback content.";
      feedbackDTO.setContent(content);
      assertEquals(content, feedbackDTO.getContent());
    }

    @Test
    void testSetUserId() {
      Long userId = 1L;
      feedbackDTO.setUserId(userId);
      assertEquals(userId, feedbackDTO.getUserId());
    }
  }

  @Test
  void equalObjectsGenerateEqualHashCodes() {
    FeedbackDTO dto1 = new FeedbackDTO();
    dto1.setFirstName("John");
    dto1.setLastName("Doe");
    dto1.setEmail("test@gmail.com");
    dto1.setFeedbackType(FeedbackType.ASSISTANCE);
    dto1.setContent("Test message");
    dto1.setUserId(1L);
    FeedbackDTO dto2 = new FeedbackDTO();
    dto2.setFirstName("John");
    dto2.setLastName("Doe");
    dto2.setEmail("test@gmail.com");
    dto2.setFeedbackType(FeedbackType.ASSISTANCE);
    dto2.setContent("Test message");
    dto2.setUserId(1L);

    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }
}
