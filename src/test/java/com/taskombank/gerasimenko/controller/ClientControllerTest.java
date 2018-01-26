package com.taskombank.gerasimenko.controller;

import com.taskombank.gerasimenko.entity.Client;
import com.taskombank.gerasimenko.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testList() throws Exception{
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        clients.add(new Client());

        when(clientService.listAll()).thenReturn(clients);

        mockMvc.perform(get("/client/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/list"))
                .andExpect(model().attribute("clients", hasSize(2)));
    }
    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        when(clientService.getById(id)).thenReturn(new Client());

        mockMvc.perform(get("/client/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/show"))
                .andExpect(model().attribute("client", instanceOf(Client.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;

        when(clientService.getById(id)).thenReturn(new Client());

        mockMvc.perform(get("/client/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/clientform"))
                .andExpect(model().attribute("client", instanceOf(Client.class)));
    }

    @Test
    public void testNewClient() throws Exception {
        mockMvc.perform(get("/client/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/clientform"))
                .andExpect(model().attribute("client", instanceOf(Client.class)));
    }
}
