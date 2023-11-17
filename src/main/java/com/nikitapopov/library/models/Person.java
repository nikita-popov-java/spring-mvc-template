package com.nikitapopov.library.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Person {
    private int id;
    @NotBlank(message = "Имя пользователя не может быть пустым!")
    @Pattern(message = "Введите данные в виде 'Фамилия Имя Отчество'",regexp = "([а-яА-Я]*? ){2}([а-яА-Я]*?)")
    private String fullName;
    @Max(value = 2023, message = "Человек не может быть рождён позже 2023 года! (на момент разработки приложения)")
    private int yearOfBirth;

    public Person() {}

    public Person(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}