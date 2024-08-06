package com.proiect.practica.medapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService usersService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = usersService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = usersService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
        Optional<User> existingUser = usersService.getUserById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            User updatedUser = usersService.saveUser(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
        usersService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
