package com.idatt2105.backend.dto;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.idatt2105.backend.model.User;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * Test class for the UserDTO class.
 */
@SpringBootTest
public class UserDTOTest {

  /*
   * Test the no-args constructor.
   */
  @Test
  public void testNoArgsConstructor() {
    UserDTO userDTO = new UserDTO();
    assertNotNull(userDTO);
  }

  /*
   * Test the getter and setter methods.
   */
  @Test
  public void testGetterSetter() {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(1L);
    userDTO.setUsername("testuser");
    userDTO.setQuizzes(Collections.emptyList());

    assertEquals(1L, userDTO.getId());
    assertEquals("testuser", userDTO.getUsername());
    assertEquals(Collections.emptyList(), userDTO.getQuizzes());
  }

  /*
   * Test the constructor with a User object.
   */
  @Test
  public void testConstructorWithUser() {
    User user = new User(1L, "testuser");
    UserDTO userDTO = new UserDTO(user);

    assertEquals(1L, userDTO.getId());
    assertEquals("testuser", userDTO.getUsername());
    assertEquals(null, userDTO.getQuizzes());
  }

  /*
   * Test the constructor with parameters.
   */
  @Test
  public void testConstructorWithParams() {
    UserDTO userDTO = new UserDTO(1L, "testuser", Collections.emptyList());

    assertEquals(1L, userDTO.getId());
    assertEquals("testuser", userDTO.getUsername());
    assertEquals(Collections.emptyList(), userDTO.getQuizzes());
  }

  /*
   * Test the toEntity method.
   */
  @Test
  public void testToEntity() {
    UserDTO userDTO = new UserDTO(1L, "testuser");
    User user = userDTO.toEntity();

    assertEquals(1L, user.getId());
    assertEquals("testuser", user.getUsername());
  }

  /*
   * Test the toString method.
   */
  @Test
  public void testToString() {
    UserDTO userDTO = new UserDTO(1L, "testuser", Collections.emptyList());
    assertDoesNotThrow(userDTO::toString);
  }

  /*
   * Test the hashCode method.
   */
  @Test
  public void testHashCode() {
    UserDTO userDTO1 = new UserDTO(1L, "testuser", Collections.emptyList());
    UserDTO userDTO2 = new UserDTO(1L, "testuser", Collections.emptyList());
    assertEquals(userDTO1.hashCode(), userDTO2.hashCode());
  }

  /*
   * Test the equals method.
   */
  @Test
  public void testEquals() {
    UserDTO userDTO1 = new UserDTO(1L, "testuser", Collections.emptyList());
    UserDTO userDTO2 = new UserDTO(1L, "testuser", Collections.emptyList());
    assertEquals(userDTO1, userDTO2);
  }
}
