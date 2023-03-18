package com.pimentelprojects.api.service;

import com.pimentelprojects.api.dto.ClientResponse;
import com.pimentelprojects.api.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ClientResponse findAll(int pageNumber, int pageSize);

    Optional<Client> findClientById(Long id);

    void saveClient(Client client);

    void deleteClientById(Long id);

    boolean existById(Long id);

}
