package com.example.users_api.controller;

import com.example.users_api.model.User;
import com.example.users_api.service.UserService;
import com.example.users_api.util.ValidationUtil;
import com.example.users_api.util.DateUtil;
import com.example.users_api.util.CryptoUtil;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(
            @RequestParam(required = false) String sortedBy,
            @RequestParam(required = false) String filter) {

        List<User> users = new ArrayList<>(userService.getUsers());

        // FILTER
        if (filter != null && filter.contains(":")) {

            String[] parts = filter.split(":");

            if(parts.length == 2){
                String field = parts[0];
                String value = parts[1];

                if (field.equalsIgnoreCase("name")) {
                    users.removeIf(u -> !u.getName().equalsIgnoreCase(value));
                }

                if (field.equalsIgnoreCase("email")) {
                    users.removeIf(u -> !u.getEmail().equalsIgnoreCase(value));
                }
            }
        }

        // SORT
        if (sortedBy != null) {

            if (sortedBy.equalsIgnoreCase("name")) {
                users.sort((u1, u2) -> u1.getName().compareToIgnoreCase(u2.getName()));
            }

            if (sortedBy.equalsIgnoreCase("email")) {
                users.sort((u1, u2) -> u1.getEmail().compareToIgnoreCase(u2.getEmail()));
            }
        }

        return users;
    }

    @PostMapping
    public User createUser(@RequestBody User user){

        // CAMPOS OBLIGATORIOS
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new RuntimeException("Email is required");
        }

        if(user.getName() == null || user.getName().isEmpty()){
            throw new RuntimeException("Name is required");
        }

        if(user.getPassword() == null || user.getPassword().isEmpty()){
            throw new RuntimeException("Password is required");
        }

        // VALIDAR EMAIL
        if(!ValidationUtil.isValidEmail(user.getEmail())){
            throw new RuntimeException("Invalid email format");
        }

        // VALIDAR RFC
        if(!ValidationUtil.isValidRFC(user.getTaxId())){
            throw new RuntimeException("Invalid RFC format");
        }

        // VALIDAR TELEFONO
        if(!ValidationUtil.isValidPhone(user.getPhone())){
            throw new RuntimeException("Invalid phone number");
        }

        // VALIDAR taxId UNICO
        boolean exists = userService.getUsers()
                .stream()
                .anyMatch(u -> u.getTaxId().equals(user.getTaxId()));

        if(exists){
            throw new RuntimeException("taxId already exists");
        }

        // GENERAR UUID
        user.setId(UUID.randomUUID());

        // TIMESTAMP MADAGASCAR
        user.setCreatedAt(DateUtil.getMadagascarTimestamp());

        // ENCRIPTAR PASSWORD
        user.setPassword(CryptoUtil.encrypt(user.getPassword()));

        userService.getUsers().add(user);

        return user;
    }

    // UPDATE USER
    @PatchMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User updatedUser){

        for(User user : userService.getUsers()){

            if(user.getId().equals(id)){

                if(updatedUser.getName() != null){
                    user.setName(updatedUser.getName());
                }

                if(updatedUser.getEmail() != null){
                    user.setEmail(updatedUser.getEmail());
                }

                if(updatedUser.getPhone() != null){
                    user.setPhone(updatedUser.getPhone());
                }

                return user;
            }
        }

        throw new RuntimeException("User not found");
    }

    // DELETE USER
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id){

        boolean removed = userService.getUsers()
                .removeIf(u -> u.getId().equals(id));

        if(!removed){
            throw new RuntimeException("User not found");
        }

        return "User deleted successfully";
    }
}