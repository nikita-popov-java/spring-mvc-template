package com.nikitapopov.library.controllers;

import com.nikitapopov.library.dao.BookDAO;
import com.nikitapopov.library.dao.PersonDAO;
import com.nikitapopov.library.models.Person;
import com.nikitapopov.library.utils.PeopleValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PeopleValidator peopleValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO, PeopleValidator peopleValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.peopleValidator = peopleValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());

        return "people/index";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("person") Person person) {

        return "people/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        model.addAttribute("books", bookDAO.getUserBooks(id));

        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", personDAO.show(id));

        return "people/edit";
    }

    @PostMapping()
    public String save(@ModelAttribute("person") @Valid Person person, BindingResult result) {

        peopleValidator.validate(person, result);
        if (result.hasErrors())
            return "people/new";

        personDAO.save(person);

        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String change(@PathVariable("id") int id,
                         @ModelAttribute("person") Person person,
                         BindingResult result) {

        peopleValidator.validate(person, result);
        if (result.hasErrors())
            return "people/edit";

        personDAO.update(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        personDAO.delete(id);

        return "redirect:/people";
    }
}