package com.example.driverschool.controller;

import com.example.driverschool.model.User;
import com.example.driverschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUserInfo) {
        User updatedUser = userService.updateUser(userId, updatedUserInfo);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/email")
    public ResponseEntity<User> updateUserEmail(@PathVariable Long userId, @RequestBody Map<String, String> request) {
        String newEmail = request.get("newEmail");
        User updatedUser = userService.updateUserEmail(userId, newEmail);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }




    // Other user-related endpoints
}
