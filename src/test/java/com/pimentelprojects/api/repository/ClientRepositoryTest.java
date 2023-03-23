package com.pimentelprojects.api.repository;

import com.pimentelprojects.api.models.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

}
