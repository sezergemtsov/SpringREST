package ru.netology.springrest.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.netology.springrest.exceptions.InvalidCredentials;
import ru.netology.springrest.exceptions.UnauthorizedUser;
import ru.netology.springrest.model.Authorities;
import ru.netology.springrest.repository.UserRepository;

import java.util.List;

public class AuthorizationService {
    UserRepository userRepository;

    public List<Authorities> getAuthorities(String user, String password) {
        try {
            if (isEmpty(user) || isEmpty(password)) {
                throw new InvalidCredentials("User name or password is empty");
            }
            List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
            if (isEmpty(userAuthorities)) {
                throw new UnauthorizedUser("Unknown user " + user);
            }
            return userAuthorities;
        } catch (InvalidCredentials e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (UnauthorizedUser u) {
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, u.getMessage(), u);
        }
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
