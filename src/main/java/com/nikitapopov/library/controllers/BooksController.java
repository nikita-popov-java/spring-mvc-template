package com.nikitapopov.library.controllers;

import com.nikitapopov.library.dao.BookDAO;
import com.nikitapopov.library.dao.PersonDAO;
import com.nikitapopov.library.models.Book;
import com.nikitapopov.library.utils.BooksValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BooksValidator booksValidator;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BooksValidator booksValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.booksValidator = booksValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        Book book = bookDAO.show(id);

        model.addAttribute("book", book);

        if (book.getHolderId() != null)
            model.addAttribute("holder", personDAO.show(book.getHolderId()));

        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {

        model.addAttribute("book", bookDAO.show(id));

        return "books/edit";
    }

    @PostMapping()
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult result) {
        booksValidator.validate(book, result);
        if (result.hasErrors()) {
            return "books/edit";
        }
        bookDAO.save(book);

        return "redirect:/books";
    }
}
