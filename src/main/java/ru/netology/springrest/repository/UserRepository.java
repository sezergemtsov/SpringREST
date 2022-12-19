package ru.netology.springrest.repository;

import ru.netology.springrest.model.Authorities;
import ru.netology.springrest.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {

    ConcurrentHashMap<String, User> users;
    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> authorities = new ArrayList<>();
        if (users.containsKey(user)) {
            return users.get(user).getAuthorities();
        }
        return authorities;
    }

    public void save(User user) {
        users.putIfAbsent(user.getName(), user);
    }
}
