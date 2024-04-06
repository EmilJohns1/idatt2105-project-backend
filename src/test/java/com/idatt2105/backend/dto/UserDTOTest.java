package com.idatt2105.backend.dto;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.idatt2105.backend.model.User;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * The UserDTOTest class is a test class that tests the UserDTO class.
 */
@SpringBootTest
public class UserDTOTest {

  /**
   * This method tests the no-args constructor of the UserDTO class. It verifies that the object is
   * instantiated correctly.
   */
  @Test
  public void testNoArgsConstructor() {
    UserDTO userDTO = new UserDTO();
    assertNotNull(userDTO);
  }

  /**
   * This method tests the getters and setters of the UserDTO class. It verifies that the methods
   * return the correct values.
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

  /**
   * This method tests the constructor with a User parameter. It verifies that the object is
   * instantiated correctly and that the values are set correctly.
   */
  @Test
  public void testConstructorWithUser() {
    User user = new User(1L, "testuser");
    UserDTO userDTO = new UserDTO(user);

    assertEquals(1L, userDTO.getId());
    assertEquals("testuser", userDTO.getUsername());
    assertEquals(null, userDTO.getQuizzes());
  }

  /**
   * This method tests the constructor with parameters. It verifies that the object is instantiated
   * correctly and that the values are set correctly.
   */
  @Test
  public void testConstructorWithParams() {
    UserDTO userDTO = new UserDTO(1L, "testuser", Collections.emptyList());

    assertEquals(1L, userDTO.getId());
    assertEquals("testuser", userDTO.getUsername());
    assertEquals(Collections.emptyList(), userDTO.getQuizzes());
  }

  /**
   * This method tests the toEntity method of the UserDTO class. It verifies that the method returns
   * the correct User entity.
   */
  @Test
  public void testToEntity() {
    UserDTO userDTO = new UserDTO(1L, "testuser");
    User user = userDTO.toEntity();

    assertEquals(1L, user.getId());
    assertEquals("testuser", user.getUsername());
  }

  /**
   * This method tests the toString method of the UserDTO class. It verifies that the method does
   * not throw an exception.
   */
  @Test
  public void testToString() {
    UserDTO userDTO = new UserDTO(1L, "testuser", Collections.emptyList());
    assertDoesNotThrow(userDTO::toString);
  }

  /**
   * This method tests the hashCode method of the UserDTO class. It verifies that the method returns
   * the correct hash code.
   */
  @Test
  public void testHashCode() {
    UserDTO userDTO1 = new UserDTO(1L, "testuser", Collections.emptyList());
    UserDTO userDTO2 = new UserDTO(1L, "testuser", Collections.emptyList());
    assertEquals(userDTO1.hashCode(), userDTO2.hashCode());
  }

  /**
   * This method tests the equals method of the UserDTO class. It verifies that the method returns
   * the correct value.
   */
  @Test
  public void testEquals() {
    UserDTO userDTO1 = new UserDTO(1L, "testuser", Collections.emptyList());
    UserDTO userDTO2 = new UserDTO(1L, "testuser", Collections.emptyList());
    assertEquals(userDTO1, userDTO2);
  }
}
