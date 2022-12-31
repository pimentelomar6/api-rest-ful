package com.example.demo.service;

import com.example.demo.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> findAll();
    Optional<Client> findClientById(Long id);
    void saveClient(Client client);
    void deleteClientById(Long id);

}
