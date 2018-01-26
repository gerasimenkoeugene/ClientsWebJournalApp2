package com.taskombank.gerasimenko.service.impl;

import com.taskombank.gerasimenko.entity.Client;
import com.taskombank.gerasimenko.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class ClientServiceImpl implements ClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rest.api.url}")
    String restApiUrl;

    @Override
    public List<Client> listAll() {
        return Arrays.asList(restTemplate.getForEntity(restApiUrl + "clients", Client[].class).getBody());
    }

    @Override
    public Client getById(Integer id) {
        return restTemplate.getForEntity(restApiUrl + "clients/" + id, Client.class).getBody();
    }

    @Override
    public void saveOrUpdate(Client client) {
        restTemplate.postForEntity(restApiUrl + "clients", new HttpEntity<>(client), Client.class);
    }

    @Override
    public void delete(Integer id) {
        restTemplate.delete(restApiUrl + "clients/" + id);
    }
}
