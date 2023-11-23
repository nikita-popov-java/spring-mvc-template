package com.nikitapopov.template.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.sql.Clob;
import java.sql.Date;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    @NotBlank
    @Size(min = 5, max = 50,
            message = "Введите корректное ФИО (от 5 до 50 символов)")
    @Pattern(regexp = "([а-яА-Я]*? ){2}([а-яА-Я]*?)",
            message = "Введите ФИО в формате 'Фамилия Имя Отчество'")
    private String fullName;

    @Column(name = "phone")
    @NotBlank
    @Pattern(regexp = "^(\\+7|8)?\\s?[(\\s]?\\d{3}[)]?[-\\s\\.]?\\d{3}[-\\s\\.]?\\d{4,6}$",
            message = "Введите телефон в корректном формате")
    private String phone;

    @Column(name = "email")
    @NotBlank
    @Email(message = "Введите email в формате xxxxxxxx@xxxx.xxx")
    private String email;

    @Column(name = "visiting_date")
    private Date visitingDate;

    @Column(name = "dentist_full_name")
    @NotBlank
    @Size(min = 5, max = 50,
            message = "Введите корректное ФИО (от 5 до 50 символов)")
    @Pattern(regexp = "^([а-яА-Яё]*?\\s){2}([а-яА-Яё]*?)$",
            message = "Введите ФИО в формате 'Фамилия Имя Отчество'")
    private String dentistFullName;

    public Client() {}

    private Client(String fullName, String phone, String email, Date visitingDate, String dentistFullName) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.visitingDate = visitingDate;
        this.dentistFullName = dentistFullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(Date visitingDate) {
        this.visitingDate = visitingDate;
    }

    public String getDentistFullName() {
        return dentistFullName;
    }

    public void setDentistFullName(String dentistFullName) {
        this.dentistFullName = dentistFullName;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%d;%s;%s;%s;%s;%s", id, fullName, phone, email, visitingDate, dentistFullName);
    }
}
