package com.example.users_api.service;

import com.example.users_api.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void shouldReturnInitialUsers() {

        List<User> users = userService.getUsers();

        assertNotNull(users);
        assertEquals(3, users.size());
    }
}
