package com.pimentelprojects.api.service;

import com.pimentelprojects.api.dto.ClientResponse;
import com.pimentelprojects.api.models.Client;
import com.pimentelprojects.api.repository.ClientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImp clientServiceImp;

    @Test
    public void ClientServiceTest_CreateClient_ReturnClient(){
        Client client = Client.builder()
                .name("Carlos")
                .address("Avenida tachira")
                .mail("carlos@gmail.com")
                .phoneNumber("+5824598")
                .build();

        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        assertAll(() -> clientServiceImp.saveClient(client));
    }

    @Test
    public void ClientServiceTest_FindAll_ReturnClientResponse(){

        Page<Client> clients = Mockito.mock(Page.class);

        when(clientRepository.findAll(Mockito.any(Pageable.class))).thenReturn(clients);
        
        ClientResponse clientResponse1 = clientServiceImp.findAll(1,10);

        Assertions.assertThat(clientResponse1).isNotNull();

    }

    @Test
    public void ClientServiceTest_GetClientById_ReturnClient(){
        Client client = Client.builder()
                .name("Carlos")
                .address("Avenida tachira")
                .mail("carlos@gmail.com")
                .phoneNumber("+5824598")
                .build();

        when(clientRepository.findById(1L)).thenReturn(Optional.ofNullable(client));

        Client client2 = clientServiceImp.findClientById(1L).get();

        Assertions.assertThat(client2).isNotNull();
    }


    @Test
    public void ClientServiceTest_UpdateClient_ReturnClient(){
        Client client = Client.builder()
                .id(1L)
                .name("Carlos")
                .address("Avenida tachira")
                .mail("carlos@gmail.com")
                .phoneNumber("+5824598")
                .build();

        when(clientRepository.findById(1L)).thenReturn(Optional.ofNullable(client));
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

       clientServiceImp.saveClient(client);

       client.setName("Juan");
       client.setAddress("Coro Edo Falcon");

       clientServiceImp.saveClient(client);

       Client client1 = clientServiceImp.findClientById(1L).get();


        Assertions.assertThat(client1).isNotNull();
        Assertions.assertThat(client1.getName()).isEqualTo("Juan");
    }

    @Test
    public void ClientServiceTest_DeleteClientById_ReturnClient(){
        Client client = Client.builder()
                .name("Carlos")
                .address("Avenida tachira")
                .mail("carlos@gmail.com")
                .phoneNumber("+5824598")
                .build();

        when(clientRepository.findById(1L)).thenReturn(Optional.ofNullable(client));

        assertAll(() -> clientServiceImp.deleteClientById(1L));
    }



}
