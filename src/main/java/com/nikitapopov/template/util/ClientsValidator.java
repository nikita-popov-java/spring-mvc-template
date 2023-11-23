package com.nikitapopov.template.util;

import com.nikitapopov.template.dao.ClientDAO;
import com.nikitapopov.template.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

@Component
public class ClientsValidator implements Validator {
    private final ClientDAO clientDAO;

    @Autowired
    public ClientsValidator(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Client.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        // checking
        if (clientDAO.show(client.getEmail()).isPresent()) {
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
