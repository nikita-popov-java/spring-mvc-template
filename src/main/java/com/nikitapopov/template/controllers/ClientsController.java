package com.nikitapopov.template.controllers;

import com.nikitapopov.template.dao.ClientDAO;
import com.nikitapopov.template.models.Client;
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
    private final ClientDAO clientDAO;
    private final ClientsValidator clientsValidator;

    @Autowired
    public ClientsController(ClientDAO clientDAO, ClientsValidator clientsValidator) {
        System.out.println("controller is creating...");
        this.clientDAO = clientDAO;
        this.clientsValidator = clientsValidator;
        System.out.println("controller was created...");
    }

    @GetMapping()
    public String index(Model model) {

        model.addAttribute("clients", clientDAO.index());

        return "clients/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("client") @Valid Client client, BindingResult result) {

        clientsValidator.validate(client, result);
        if (result.hasErrors())
            return "clients/new";

        clientDAO.save(client);

        return "redirect:/clients";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        if (clientDAO.show(id) == null)
            return "redirect:/clients";

        model.addAttribute("client", clientDAO.show(id));

        return "clients/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        clientDAO.delete(id);

        return "redirect:/clients";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("client") @Valid Client client,
                         BindingResult result) {

//        clientsValidator.validate(client, result);
        if (result.hasErrors())
            return "clients/edit";

        clientDAO.update(id, client);

        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {

        model.addAttribute("client", clientDAO.show(id));

        return "clients/edit";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("client") Client client) {

        return "clients/new";
    }
}
