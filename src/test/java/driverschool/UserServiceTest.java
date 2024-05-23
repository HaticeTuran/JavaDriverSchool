package driverschool;

import com.example.driverschool.exception.ResourceNotFoundException;
import com.example.driverschool.model.User;
import com.example.driverschool.service.UserService;
import com.example.driverschool.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Given
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "user1", "password1", "email1", "ROLE_USER"));
        userList.add(new User(2L, "user2", "password2", "email2", "ROLE_USER"));
        when(userRepository.findAll()).thenReturn(userList);

        // When
        List<User> result = userService.getAllUsers();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetUserById() {
        // Given
        User user = new User(1L, "user1", "password1", "email1", "ROLE_USER");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // When
        User result = userService.getUserById(1L);

        // Then
        assertNotNull(result);
        assertEquals("user1", result.getUsername());
    }

    @Test
    void testUpdateUser() {
        // Given
        User existingUser = new User(1L, "user1", "password1", "email1", "ROLE_USER");
        User updatedUserInfo = new User();
        updatedUserInfo.setEmail("new_email@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        // When
        User result = userService.updateUser(1L, updatedUserInfo);

        // Then
        assertNotNull(result);
        assertEquals("new_email@example.com", result.getEmail());
    }

    @Test
    void testUpdateUserThrowsResourceNotFoundException() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(1L, new User()));
    }
}
