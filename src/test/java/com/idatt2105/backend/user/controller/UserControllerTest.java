package com.idatt2105.backend.user.controller;

import com.idatt2105.backend.controller.UserController;
import com.idatt2105.backend.model.User;
import com.idatt2105.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        User user = new User("username", "password");
        when(userService.addUser(user)).thenReturn(user);

        // Act
        ResponseEntity<String> response = userController.register(user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registered successfully", response.getBody());
        verify(userService, times(1)).addUser(user);
    }

    @Test
    void testRegisterUser_Failure() {
        // Arrange
        User user = new User("username", "password");
        when(userService.addUser(user)).thenThrow(new RuntimeException("User already exists"));

        // Act
        ResponseEntity<String> response = userController.register(user);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("User already exists", response.getBody());
        verify(userService, times(1)).addUser(user);
    }

    @Test
    void testLoginUser_Success() {
        // Arrange
        User user = new User("username", "password");
        when(userService.login(user)).thenReturn("Logged in");

        // Act
        ResponseEntity<String> response = userController.login(user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Logged in successfully", response.getBody());
        verify(userService, times(1)).login(user);
    }

    @Test
    void testLoginUser_Failure() {
        // Arrange
        User user = new User("username", "wrongpassword");
        when(userService.login(user)).thenThrow(new RuntimeException("Login failed"));

        // Act
        ResponseEntity<String> response = userController.login(user);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Login failed", response.getBody());
        verify(userService, times(1)).login(user);
    }

    @Test
    void testUpdateUser_Success() {
        // Arrange
        User user = new User("username", "password");
        when(userService.updateUser(user.getId(), user)).thenReturn(user);

        // Act
        ResponseEntity<String> response = userController.update(user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User updated successfully", response.getBody());
        verify(userService, times(1)).updateUser(user.getId(), user);
    }

    @Test
    void testUpdateUser_Failure() {
        // Arrange
        User user = new User("username", "password");
        when(userService.updateUser(user.getId(), user)).thenThrow(new RuntimeException("User not found"));

        // Act
        ResponseEntity<String> response = userController.update(user);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("User not found", response.getBody());
        verify(userService, times(1)).updateUser(user.getId(), user);
    }

    @Test
    void testDeleteUser_Success() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<String> response = userController.delete(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User deleted successfully", response.getBody());
        verify(userService, times(1)).deleteUser(id);
    }

    @Test
    void testDeleteUser_Failure() {
        // Arrange
        Long id = 1L;
        doThrow(new RuntimeException("User not found")).when(userService).deleteUser(id);

        // Act
        ResponseEntity<String> response = userController.delete(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("User not found", response.getBody());
        verify(userService, times(1)).deleteUser(id);
    }

}
