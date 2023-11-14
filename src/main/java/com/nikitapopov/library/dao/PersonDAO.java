package com.nikitapopov.library.dao;

import com.nikitapopov.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO extends AbstractLibraryDAO<Person> {

    @Autowired
    public PersonDAO(JdbcTemplate template) {
        super(template);
    }

    @Override
    public List<Person> index() {
        return template.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public Optional<Person> show(String name) {
        return template.query(
                        "SELECT * FROM person WHERE full_name=?",
                        new BeanPropertyRowMapper<>(Person.class),
                        name
                ).stream()
                .findAny();
    }

    @Override
    public Person show(int id) {
        return template.query(
                        "SELECT * FROM person WHERE id=?",
                        new BeanPropertyRowMapper<>(Person.class),
                        id
                ).stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(Person record) {
        template.update(
                "INSERT INTO person(full_name, year_of_birth) VALUES(?, ?)",
                record.getFullName(),
                record.getYearOfBirth()
        );
    }

    @Override
    public void update(int id, Person record) {
        template.update(
                "UPDATE person SET full_name=?, year_of_birth=? WHERE id=?",
                record.getFullName(),
                record.getYearOfBirth(),
                id
        );
    }

    @Override
    public void delete(int id) {
        template.update("DELETE FROM person WHERE id=?", id);
    }
}