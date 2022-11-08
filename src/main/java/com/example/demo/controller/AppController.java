package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController @RequestMapping("api")
public class AppController {

    @Autowired
    UsuarioService usuarioService;

    // Listar y Buscar Usuarios

    @GetMapping("/saludar")
    public String saludar(){
        return "Hola";
    }

    @GetMapping("/all")
    public ArrayList<User> getAllUser(){
        return usuarioService.getAllUsers();
    }

    @GetMapping("/find/{id}")
    public Optional<User> getById(@PathVariable("id") long id){
        return usuarioService.getUserById(id);
    }

    // Guardar Usuario

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        return usuarioService.saveUser(user);
    }

    // Eliminar Usuario por medio de su ID
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id){

        if (usuarioService.deleteUserById(id)){
            return "El usuario del id: " + id + " se ha eliminado correctamente";
        } else {
            return "El usuario del id: " + id + " no pudo ser eliminado";
        }
    }

}
