package com.example.demo.controller;

import com.example.demo.models.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public List<Client> getAllClients(){
        return clientService.findAll();
    }

    @GetMapping("/find/{id}")
    public Optional<Client> getClientById(@PathVariable("id") Long id){
        return clientService.findClientById(id);
    }

    @PostMapping("/save")
    public void saveClient(@RequestBody Client client){
        clientService.saveClient(client);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        clientService.deleteClientById(id);
    }

}
