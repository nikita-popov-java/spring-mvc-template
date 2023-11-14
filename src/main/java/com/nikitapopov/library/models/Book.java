package com.nikitapopov.library.models;

public class Book {
    private int id;
    private String name;
    private String author;
    private int yearOfCreated;
    private Integer holderId;

    public Book() {}

    public Book(int id, String name, String author, int yearOfCreated, Integer holderId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfCreated = yearOfCreated;
        this.holderId = holderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfCreated() {
        return yearOfCreated;
    }

    public void setYearOfCreated(int yearOfCreated) {
        this.yearOfCreated = yearOfCreated;
    }

    public Integer getHolderId() {
        return holderId;
    }

    public void setHolderId(Integer holderId) {
        this.holderId = holderId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
