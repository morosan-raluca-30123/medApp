package com.proiect.practica.medapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository usersRepository;

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return usersRepository.findById(id);
    }

    public User saveUser(User user) {
        return usersRepository.save(user);
    }

    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }
}
