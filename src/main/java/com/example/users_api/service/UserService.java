package com.example.users_api.service;

import com.example.users_api.model.Address;
import com.example.users_api.model.User;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public UserService() {

        Address work = new Address(1, "workaddress", "street No. 1", "UK");
        Address home = new Address(2, "homeaddress", "street No. 2", "AU");

        List<Address> addresses = Arrays.asList(work, home);

        users.add(new User(
                UUID.randomUUID(),
                "user1@mail.com",
                "user1",
                "+1 55 555 555 55",
                "7c4a8d09ca3762af61e59520943dc26494f8941b",
                "AARR990101XXX",
                "01-01-2026 00:00:00",
                addresses
        ));

        users.add(new User(
                UUID.randomUUID(),
                "user2@mail.com",
                "user2",
                "+1 55 555 555 56",
                "password",
                "BBBB990101XXX",
                "01-01-2026 00:00:00",
                addresses
        ));

        users.add(new User(
                UUID.randomUUID(),
                "user3@mail.com",
                "user3",
                "+1 55 555 555 57",
                "password",
                "CCCC990101XXX",
                "01-01-2026 00:00:00",
                addresses
        ));
    }

    public List<User> getUsers() {
        return users;
    }
}