package com.nikitapopov.library.dao;

import com.nikitapopov.library.models.Book;
import com.nikitapopov.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO extends AbstractLibraryDAO<Book> {

    @Autowired
    public BookDAO(JdbcTemplate template) {
        super(template);
    }

    @Override
    public List<Book> index() {
        return template.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Optional<Book> show(String name) {
        return template.query(
                        "SELECT * FROM book WHERE name=?",
                        new BeanPropertyRowMapper<>(Book.class),
                        name
                ).stream()
                .findAny();
    }

    @Override
    public Book show(int id) {
        return template.query(
                        "SELECT * FROM book WHERE id=?",
                        new BeanPropertyRowMapper<>(Book.class),
                        id
                ).stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(Book record) {
        template.update(
                "INSERT INTO book(name, author, year_of_created, holder_id) VALUES(?, ?, ?, ?)",
                record.getName(),
                record.getAuthor(),
                record.getYearOfCreated(),
                record.getHolderId()
        );
    }

    @Override
    public void update(int id, Book record) {
        template.update(
                "UPDATE book SET name=?, author=?, year_of_created=?, holder_id=? WHERE id=?",
                record.getName(),
                record.getAuthor(),
                record.getYearOfCreated(),
                record.getHolderId(),
                id
        );
    }

    @Override
    public void delete(int id) {
        template.update("DELETE FROM book WHERE id=?", id);
    }

    public List<Book> getUserBooks(int id) {
        return template.query("SELECT * FROM book WHERE holder_id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().toList();
    }

    public Person getBookHolder(int bookId) {
        return template.query("SELECT person.* " +
                                "FROM book INNER JOIN person ON book.holder_id=person.id " +
                                "WHERE book.id=?", new BeanPropertyRowMapper<>(Person.class), bookId)
                .stream()
                .findAny()
                .orElse(null);
    }

    public void setBookToUser(int bookId, Integer userId) {
        template.update("UPDATE book SET holder_id=? WHERE id=?", userId, bookId);
    }
}
