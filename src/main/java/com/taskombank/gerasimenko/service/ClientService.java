package com.taskombank.gerasimenko.service;


import com.taskombank.gerasimenko.entity.Client;
import java.util.List;

public interface ClientService {
    List<Client> listAll();

    Client getById(Integer id);

    void saveOrUpdate(Client client);

    void delete(Integer id);
}
