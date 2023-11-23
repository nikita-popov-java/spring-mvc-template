package com.nikitapopov.template.util;

import com.nikitapopov.template.models.Client;
import com.nikitapopov.template.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

@Component
public class ClientsValidator implements Validator {
    private final ClientsService clientsService;

    @Autowired
    public ClientsValidator(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Client.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        // checking
        if (clientsService.find(client.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Пользователь с таким email уже существует!");
        }

        //checking
        if (client.getFullName().split(" ").length != 3
                || Arrays.stream(client.getFullName().split(" "))
                .anyMatch(part -> Character.isLowerCase(part.charAt(0)))) {
            errors.rejectValue("fullName", "",  "Имя, фамилия и отчество должны начинаться с заглавной буквы!");
        }
    }
}
