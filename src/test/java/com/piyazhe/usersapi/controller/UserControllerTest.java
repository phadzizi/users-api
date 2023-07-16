package com.piyazhe.usersapi.controller;

import com.piyazhe.usersapi.model.User;
import com.piyazhe.usersapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User(1L, "John", "Doe", "john@example.com");
        User user2 = new User(2L, "Jane", "Smith", "jane@example.com");
        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userController.getAllUsers();

        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));

        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testGetUserById() {
        User user = new User(1L, "John", "Doe", "john@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());

        verify(userRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testGetUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(userRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testCreateUser() {
        User user = new User("John", "Doe", "john@example.com");
        User savedUser = new User(1L, "John", "Doe", "john@example.com");

        when(userRepository.save(user)).thenReturn(savedUser);

        ResponseEntity<User> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());

        verify(userRepository, times(1)).save(user);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testUpdateUser() {
        User existingUser = new User(1L, "John", "Doe", "john@example.com");
        User updatedUser = new User(1L, "John", "Smith", "john@example.com");

        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.updateUser(1L, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());

        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).save(updatedUser);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testUpdateUser_NotFound() {
        User updatedUser = new User(1L, "John", "Smith", "john@example.com");

        when(userRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<User> response = userController.updateUser(1L, updatedUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(userRepository, times(1)).existsById(1L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testDeleteUser_NotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(userRepository, times(1)).existsById(1L);
        verifyNoMoreInteractions(userRepository);
    }

}
