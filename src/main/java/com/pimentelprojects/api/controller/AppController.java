package com.pimentelprojects.api.controller;

import com.pimentelprojects.api.dto.ClientResponse;
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


    private final ClientService clientService;

    @Autowired
    public AppController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<ClientResponse> getAllClients(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
//        if(clientService.findAll(pageNumber,pageSize).isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(clientService.findAll(pageNumber,pageSize), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public  ResponseEntity<Optional<Client>> getClientById(@PathVariable("id") Long id){
        if(!clientService.existById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientService.findClientById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveClient(@RequestBody Client client){
        if(client.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        clientService.saveClient(client);

        return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
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

        return new ResponseEntity<>("Usuario actualizado exitosamente", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        if(!clientService.existById(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        clientService.deleteClientById(id);
        return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
    }

}
