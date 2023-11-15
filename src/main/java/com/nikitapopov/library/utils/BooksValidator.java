package com.nikitapopov.library.utils;

import com.nikitapopov.library.dao.BookDAO;
import com.nikitapopov.library.dao.PersonDAO;
import com.nikitapopov.library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BooksValidator implements Validator {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksValidator(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Book.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (personDAO.show(book.getHolderId()) == null) {
            errors.rejectValue("holderId", "", "Пользователя с таким ID не существует!");
        }

        Book existingBook = bookDAO.show(book.getId());
        if (existingBook != null
                && existingBook.getName().equals(book.getName())
                && existingBook.getAuthor().equals(book.getAuthor())) {
            errors.rejectValue("name", "", "Такая книга уже существует!");
        }
    }
}
