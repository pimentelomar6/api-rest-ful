package com.pimentelprojects.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pimentelprojects.api.dto.ClientResponse;
import com.pimentelprojects.api.models.Client;
import com.pimentelprojects.api.service.ClientService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(controllers = AppController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientService clientService;


    private Client client;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init(){
        client = Client.builder().name("Carlos Andrade").mail("carlos_42@gmail.com").phoneNumber("+5824821693").address("Room 24, Look 42").build();
    }


    @Test
    public void ClientController_GetAllClients_ReturnClients () throws Exception{
        ClientResponse clientResponse = ClientResponse.builder().pageSize(10).last(true).pageNumber(1).content(Arrays.asList(client)).build();
        when(clientService.findAll(1,10)).thenReturn(clientResponse);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                .param("pageNumber", "1")
                .param("pageSize","10"));


        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(clientResponse.getContent().size())));

    }


}
