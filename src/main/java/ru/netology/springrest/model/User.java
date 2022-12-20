package ru.netology.springrest.model;

import jakarta.validation.Valid;

@SuppressWarnings("all")
public class User {
    private String name;
    private String password;

    public User() {

    }

    @Valid
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
