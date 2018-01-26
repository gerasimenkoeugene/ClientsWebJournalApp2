package com.taskombank.gerasimenko.controller;

import com.taskombank.gerasimenko.entity.Client;
import com.taskombank.gerasimenko.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping({"/list", "/"})
    public String listClients(Model model){
        model.addAttribute("clients", clientService.listAll());
        return "client/list";
    }

    @RequestMapping("/show/{id}")
    public String getClient(@PathVariable Integer id, Model model){
        model.addAttribute("client", clientService.getById(id));
        return "client/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("client", clientService.getById(id));
        return "client/clientform";
    }

    @RequestMapping("/new")
    public String newClient(Model model){
        model.addAttribute("client", new Client());
        return "client/clientform";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(Client client){
        clientService.saveOrUpdate(client);
        return "redirect:/client/list" ;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        clientService.delete(id);
        return "redirect:/client/list";
    }
}
