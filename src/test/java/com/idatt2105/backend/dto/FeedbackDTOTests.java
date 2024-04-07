package com.idatt2105.backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.idatt2105.backend.enumerator.FeedbackType;
import com.idatt2105.backend.model.Feedback;
import com.idatt2105.backend.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The FeedbackDTOTests class is a test class that tests the FeedbackDTO class. */
class FeedbackDTOTests {
  private FeedbackDTO feedbackDTO;

  @BeforeEach
  void setUp() {
    feedbackDTO = new FeedbackDTO();
  }

  /** The Constructor class is a test class that tests the constructors of the FeedbackDTO class. */
  @Nested
  class Constructor {
    /**
     * This method tests the no-args constructor of the FeedbackDTO class. It verifies that the
     * object is instantiated correctly.
     */
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

  /**
   * This method tests the constructor of the FeedbackDTO class with a Feedback parameter. It
   * verifies that the object is instantiated correctly.
   */
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

  /**
   * This method tests the convertToEntity method of the FeedbackDTO class. It verifies that the
   * method returns the correct Feedback object.
   */
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

  /** The GetterTests class is a test class that tests the getters of the FeedbackDTO class. */
  @Nested
  class GetterTests {
    /**
     * This method tests the getFirstName method of the FeedbackDTO class. It verifies that the
     * method returns the correct first name.
     */
    @Test
    void testGetFirstName() {
      String firstName = "John";
      feedbackDTO.setFirstName(firstName);
      assertEquals(firstName, feedbackDTO.getFirstName());
    }

    /**
     * This method tests the getLastName method of the FeedbackDTO class. It verifies that the
     * method returns the correct last name.
     */
    @Test
    void testGetLastName() {
      String lastName = "Doe";
      feedbackDTO.setLastName(lastName);
      assertEquals(lastName, feedbackDTO.getLastName());
    }

    /**
     * This method tests the getEmail method of the FeedbackDTO class. It verifies that the method
     * returns the correct email.
     */
    @Test
    void testGetEmail() {
      String email = "john.doe@example.com";
      feedbackDTO.setEmail(email);
      assertEquals(email, feedbackDTO.getEmail());
    }

    /**
     * This method tests the getFeedbackType method of the FeedbackDTO class. It verifies that the
     * method returns the correct feedback type.
     */
    @Test
    void testGetFeedbackType() {
      FeedbackType feedbackType = FeedbackType.ASSISTANCE;
      feedbackDTO.setFeedbackType(feedbackType);
      assertEquals(feedbackType, feedbackDTO.getFeedbackType());
    }

    /**
     * This method tests the getContent method of the FeedbackDTO class. It verifies that the method
     * returns the correct content.
     */
    @Test
    void testGetContent() {
      String content = "This is a feedback content.";
      feedbackDTO.setContent(content);
      assertEquals(content, feedbackDTO.getContent());
    }

    /**
     * This method tests the getUserId method of the FeedbackDTO class. It verifies that the method
     * returns the correct user id.
     */
    @Test
    void testGetUserId() {
      Long userId = 1L;
      feedbackDTO.setUserId(userId);
      assertEquals(userId, feedbackDTO.getUserId());
    }
  }

  /** The SetterTests class is a test class that tests the setters of the FeedbackDTO class. */
  @Nested
  class SetterTests {
    /**
     * This method tests the setFirstName method of the FeedbackDTO class. It verifies that the
     * method sets the correct first name.
     */
    @Test
    void testSetFirstName() {
      String firstName = "John";
      feedbackDTO.setFirstName(firstName);
      assertEquals(firstName, feedbackDTO.getFirstName());
    }

    /**
     * This method tests the setLastName method of the FeedbackDTO class. It verifies that the
     * method sets the correct last name.
     */
    @Test
    void testSetLastName() {
      String lastName = "Doe";
      feedbackDTO.setLastName(lastName);
      assertEquals(lastName, feedbackDTO.getLastName());
    }

    /**
     * This method tests the setEmail method of the FeedbackDTO class. It verifies that the method
     * sets the correct email.
     */
    @Test
    void testSetEmail() {
      String email = "john.doe@example.com";
      feedbackDTO.setEmail(email);
      assertEquals(email, feedbackDTO.getEmail());
    }

    /**
     * This method tests the setFeedbackType method of the FeedbackDTO class. It verifies that the
     * method sets the correct feedback type.
     */
    @Test
    void testSetFeedbackType() {
      FeedbackType feedbackType = FeedbackType.ASSISTANCE;
      feedbackDTO.setFeedbackType(feedbackType);
      assertEquals(feedbackType, feedbackDTO.getFeedbackType());
    }

    /**
     * This method tests the setContent method of the FeedbackDTO class. It verifies that the method
     * sets the correct content.
     */
    @Test
    void testSetContent() {
      String content = "This is a feedback content.";
      feedbackDTO.setContent(content);
      assertEquals(content, feedbackDTO.getContent());
    }

    /**
     * This method tests the setUserId method of the FeedbackDTO class. It verifies that the method
     * sets the correct user id.
     */
    @Test
    void testSetUserId() {
      Long userId = 1L;
      feedbackDTO.setUserId(userId);
      assertEquals(userId, feedbackDTO.getUserId());
    }
  }

  /**
   * This method tests the equals method of the FeedbackDTO class. It verifies that the method
   * returns the correct boolean value.
   */
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
