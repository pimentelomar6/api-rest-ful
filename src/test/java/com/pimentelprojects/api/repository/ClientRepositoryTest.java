package com.pimentelprojects.api.repository;

import com.pimentelprojects.api.models.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void ClientRepository_SaveAll_ReturnSavedClient(){

        //Arrange

        Client client = Client.builder()
                .name("Carlos")
                .address("Avenida tachira")
                .mail("carlos@gmail.com")
                .phoneNumber("+5824598")
                .build();
        //Act

        Client saveClient = clientRepository.save(client);

        //Assert

        Assertions.assertThat(saveClient).isNotNull();
        Assertions.assertThat(saveClient.getId()).isGreaterThan(0);

    }

    @Test
    public void ClientRepository_GetAll_ReturnMoreThenOneClient(){

        //Arrange
        Client client = Client.builder()
                .name("Carlos")
                .address("Avenida tachira")
                .mail("carlos@gmail.com")
                .phoneNumber("+5824598")
                .build();

        Client client2 = Client.builder()
                .name("Juan")
                .address("Calle 5 Room 23")
                .mail("juan_g@gmail.com")
                .phoneNumber("+58222598825")
                .build();

        clientRepository.save(client);
        clientRepository.save(client2);

        //Act

        List<Client> clientList = clientRepository.findAll();

        //Asserts
        Assertions.assertThat(clientList).isNotNull();
        Assertions.assertThat(clientList.size()).isEqualTo(2);

    }

    @Test
    public void ClientRepository_FindById_ReturnClient(){
        //Arrange
        Client client = Client.builder()
                .name("Carlos")
                .address("Avenida tachira")
                .mail("carlos@gmail.com")
                .phoneNumber("+5824598")
                .build();

        clientRepository.save(client);

        //Act

        Client returnClient = clientRepository.findById(client.getId()).get();

        //Asserts
        Assertions.assertThat(returnClient).isNotNull();


    }


}
