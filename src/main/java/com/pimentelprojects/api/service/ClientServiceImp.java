package com.pimentelprojects.api.service;

import com.pimentelprojects.api.dto.ClientResponse;
import com.pimentelprojects.api.models.Client;
import com.pimentelprojects.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService {


    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImp(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientResponse findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        Page<Client> clients = clientRepository.findAll(pageable);
        List<Client> list = clients.getContent();

        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setContent(list);
        clientResponse.setPageNumber(clients.getNumber());
        clientResponse.setPageSize(clients.getSize());
        clientResponse.setTotalElements((int) clients.getTotalElements());
        clientResponse.setTotalPages(clients.getTotalPages());
        clientResponse.setLast(clients.isLast());


        return clientResponse;


    }

    @Override
    public Optional<Client> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);

    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return clientRepository.existsById(id);
    }
}
