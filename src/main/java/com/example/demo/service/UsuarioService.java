package com.example.demo.service;

import com.example.demo.models.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UsuarioService {

    ArrayList<User> getAllUsers();
    Optional<User> getUserById(long id);
    User saveUser(User user);
    boolean deleteUserById(long id);

}
