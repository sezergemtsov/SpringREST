package ru.netology.springrest.repository;

import org.springframework.stereotype.Repository;
import ru.netology.springrest.exceptions.UnauthorizedUser;
import ru.netology.springrest.model.Authorities;
import ru.netology.springrest.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    ConcurrentHashMap<User, List<Authorities>> users;

    public UserRepository() {
        users = new ConcurrentHashMap<>();
    }

    @SuppressWarnings("all")
    public List<Authorities> getUserAuthorities(User user) {
        boolean isPresent = users.entrySet().stream()
                .filter(x -> x.getKey().getName().equals(user.getName()))
                .anyMatch(x -> x.getKey().getPassword().equals(user.getPassword()));

        if (isPresent) {
            return users.entrySet().stream()
                    .filter(x -> x.getKey().getName().equals(user.getName()))
                    .filter(x -> x.getKey().getPassword().equals(user.getPassword()))
                    .findFirst().get().getValue();
        } else {
            return new ArrayList<>();
        }
    }
    public User getUser(String name, String password) {
        boolean isPresent = users.entrySet().stream()
                .filter(x -> x.getKey().getName().equals(name))
                .anyMatch(x -> x.getKey().getPassword().equals(password));
        if (isPresent) {
            return users.entrySet().stream()
                    .filter(x -> x.getKey().getName().equals(name))
                    .filter(x -> x.getKey().getPassword().equals(password))
                    .findFirst().get().getKey();
        } else {
            throw new UnauthorizedUser("unauthorized user");
        }
    }
}
