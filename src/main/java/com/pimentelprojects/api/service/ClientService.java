package com.pimentelprojects.api.service;

import com.pimentelprojects.api.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> findAll();
    Optional<Client> findClientById(Long id);
    void saveClient(Client client);
    void deleteClientById(Long id);

}
