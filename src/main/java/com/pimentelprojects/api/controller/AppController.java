package com.pimentelprojects.api.controller;

import com.pimentelprojects.api.models.Client;
import com.pimentelprojects.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
public class AppController {


    private ClientService clientService;

    @Autowired
    public AppController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        if(clientService.findAll().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Client>>(clientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public  ResponseEntity<Optional<Client>> getClientById(@PathVariable("id") Long id){
        if(!clientService.existById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Client>>(clientService.findClientById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveClient(@RequestBody Client client){
        if(client.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        clientService.saveClient(client);

        return new ResponseEntity<String>("Usuario creado exitosamente", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateClient(@RequestBody Client client){
        if(client.getId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(!clientService.existById(client.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        clientService.saveClient(client);

        return new ResponseEntity<String>("Usuario actualizado exitosamente", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        if(!clientService.existById(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        clientService.deleteClientById(id);
        return new ResponseEntity<String>("Usuario eliminado exitosamente", HttpStatus.OK);
    }

}
