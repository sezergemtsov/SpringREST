package ru.netology.springrest.repository;

import ru.netology.springrest.model.Authorities;
import ru.netology.springrest.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    ConcurrentHashMap<User, List<Authorities>> users;
    public List<Authorities> getUserAuthorities(String user, String password) {
        return users.entrySet().stream()
                .filter(x->x.getKey().getName().equals(user))
                .filter(x->x.getKey().getPassword().equals(password))
                .findFirst().get().getValue();
    }

    public void save(String user, String password) {
        User us = users.entrySet().stream()
                .filter(x -> x.getKey().getName().equals(user))
                .filter(x -> x.getKey().getPassword().equals(password))
                .findFirst().get().getKey();
        if (us == null) {
            List<Authorities> authorities = new ArrayList<>();
            authorities.add(Authorities.WRITE);
            users.put(new User(user, password), authorities);
        }
    }
}
