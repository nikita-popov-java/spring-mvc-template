package com.nikitapopov.library.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public abstract class AbstractLibraryDAO<T> {
    protected final JdbcTemplate template;

    public AbstractLibraryDAO(JdbcTemplate template) {
        this.template = template;
    }

    public abstract List<T> index();
    public abstract Optional<T> show(String name);
    public abstract T show(int id);
    public abstract void save(T record);
    public abstract void update(int id, T record);
    public abstract void delete(int id);
}
