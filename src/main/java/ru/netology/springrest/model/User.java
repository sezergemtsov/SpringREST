package ru.netology.springrest.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final String password;
    private List<Authorities> authorities;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        authorities = new ArrayList<>();
    }

    public User setAuthorities(Authorities authorities) {
        if (!this.authorities.contains(authorities)) {
            this.authorities.add(authorities);
        }
        return this;
    }

    public List<Authorities> getAuthorities() {
        return this.authorities;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
