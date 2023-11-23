package com.nikitapopov.template.controllers;

import com.nikitapopov.template.models.Client;
import com.nikitapopov.template.services.ClientsService;
import com.nikitapopov.template.util.ClientsValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientsController {
    private final ClientsService clientsService;
    private final ClientsValidator clientsValidator;

    @Autowired
    public ClientsController(ClientsService clientsService, ClientsValidator clientsValidator) {
        this.clientsService = clientsService;
        this.clientsValidator = clientsValidator;
    }

    @GetMapping()
    public String index(Model model) {

        model.addAttribute("clients", clientsService.findAll());

        return "clients/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("client") @Valid Client client, BindingResult result) {

        clientsValidator.validate(client, result);
        if (result.hasErrors())
            return "clients/new";

        clientsService.save(client);

        return "redirect:/clients";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        Client findedClient = clientsService.find(id);

        if (findedClient == null)
            return "redirect:/clients";

        model.addAttribute("client", findedClient);

        return "clients/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        clientsService.delete(id);

        return "redirect:/clients";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("client") @Valid Client client,
                         BindingResult result) {

        if (result.hasErrors())
            return "clients/edit";

        clientsService.update(id, client);

        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {

        model.addAttribute("client", clientsService.find(id));

        return "clients/edit";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("client") Client client) {

        return "clients/new";
    }
}
