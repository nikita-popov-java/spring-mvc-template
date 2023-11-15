package com.nikitapopov.library.utils;

import com.nikitapopov.library.dao.PersonDAO;
import com.nikitapopov.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PeopleValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.show(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "Такой пользователь уже существует!");
        }
    }
}
