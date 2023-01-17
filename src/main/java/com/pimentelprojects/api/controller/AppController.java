package com.pimentelprojects.api.controller;

import com.pimentelprojects.api.models.Client;
import com.pimentelprojects.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AppController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public List<Client> getAllClients(){
        return clientService.findAll();
    }

    @GetMapping("/clients/find/{id}")
    public Optional<Client> getClientById(@PathVariable("id") Long id){
        return clientService.findClientById(id);
    }

    @PostMapping("clients/save")
    public void saveClient(@RequestBody Client client){
        clientService.saveClient(client);
    }

    @DeleteMapping("clients/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        clientService.deleteClientById(id);
    }

}
