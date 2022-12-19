package ru.netology.springrest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.netology.springrest.exceptions.InvalidCredentials;
import ru.netology.springrest.exceptions.UnauthorizedUser;
import ru.netology.springrest.model.Authorities;
import ru.netology.springrest.service.AuthorizationService;

import java.util.List;

@RestController
@RequestMapping("/")
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        try {
            return service.getAuthorities(user, password);
        } catch (InvalidCredentials e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (UnauthorizedUser u) {
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, u.getMessage(), u);
        }
    }
}
