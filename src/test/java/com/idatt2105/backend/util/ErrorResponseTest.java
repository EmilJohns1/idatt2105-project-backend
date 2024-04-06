package com.idatt2105.backend.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The ErrorResponseTest class is a test class that tests the ErrorResponse class. */
class ErrorResponseTest {

  /**
   * This method tests the constructor of the ErrorResponse class. It verifies that the constructor
   * sets the correct values for title, status, and timestamp.
   */
  @Test
  void testConstructor() {
    // Arrange
    String title = "Error";
    int status = 500;
    String timestamp = "2022-01-01T12:00:00";

    // Act
    ErrorResponse errorResponse = new ErrorResponse(title, status, timestamp);

    // Assert
    assertEquals(title, errorResponse.getTitle());
    assertEquals(status, errorResponse.getStatus());
    assertEquals(timestamp, errorResponse.getTimestamp());
  }

  /** Test for the constructor of the {@link ErrorResponse} class with all arguments. */
  @Test
  void testConstructorWithAllArguments() {
    // Arrange
    String title = "Error";
    int status = 404;
    String timestamp = "2022-04-06T12:30:00";

    // Act
    ErrorResponse errorResponse = new ErrorResponse(title, status, timestamp);

    // Assert
    assertEquals(title, errorResponse.getTitle());
    assertEquals(status, errorResponse.getStatus());
    assertEquals(timestamp, errorResponse.getTimestamp());
  }

  /** Test for the constructor of the {@link ErrorResponse} class with no arguments. */
  @Test
  void testNoArgsConstructor() {
    // Arrange
    ErrorResponse errorResponse = new ErrorResponse();

    // Assert
    assertEquals(null, errorResponse.getTitle());
    assertEquals(0, errorResponse.getStatus());
    assertEquals(null, errorResponse.getTimestamp());
  }

  /** Test for the setter methods of the {@link ErrorResponse} class. */
  @Test
  void testSetterMethods() {
    // Arrange
    ErrorResponse errorResponse = new ErrorResponse();

    // Act
    errorResponse.setTitle("Error");
    errorResponse.setStatus(404);
    errorResponse.setTimestamp("2022-04-06T12:30:00");

    // Assert
    assertEquals("Error", errorResponse.getTitle());
    assertEquals(404, errorResponse.getStatus());
    assertEquals("2022-04-06T12:30:00", errorResponse.getTimestamp());
  }

  /** Test for the equals method of the {@link ErrorResponse} class. */
  @Test
  void testEquals() {
    // Arrange
    ErrorResponse errorResponse1 = new ErrorResponse("Error", 404, "2022-04-06T12:30:00");
    ErrorResponse errorResponse2 = new ErrorResponse("Error", 404, "2022-04-06T12:30:00");
    ErrorResponse errorResponse3 = new ErrorResponse("Error", 500, "2022-04-06T12:30:00");

    // Assert
    assertEquals(errorResponse1, errorResponse2);
    assertNotEquals(errorResponse1, errorResponse3);
    assertTrue(errorResponse1.canEqual(errorResponse3));
  }

  /** Test for the toString method of the {@link ErrorResponse} class. */
  @Test
  void testToString() {
    // Arrange
    ErrorResponse errorResponse = new ErrorResponse("Error", 404, "2022-04-06T12:30:00");

    // Assert
    assertEquals(
        "ErrorResponse(title=Error, status=404, timestamp=2022-04-06T12:30:00)",
        errorResponse.toString());
  }

  /** Test for the hashCode method of the {@link ErrorResponse} class. */
  @Test
  void testHashCode() {
    // Arrange
    ErrorResponse errorResponse1 = new ErrorResponse("Error", 404, "2022-04-06T12:30:00");
    ErrorResponse errorResponse2 = new ErrorResponse("Error", 404, "2022-04-06T12:30:00");

    // Assert
    assertEquals(errorResponse1.hashCode(), errorResponse2.hashCode());
  }
}
