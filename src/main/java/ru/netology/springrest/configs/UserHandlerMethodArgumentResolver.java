package ru.netology.springrest.configs;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.netology.springrest.exceptions.InvalidCredentials;
import ru.netology.springrest.repository.UserRepository;

public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserRepository repository;

    public UserHandlerMethodArgumentResolver(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return true;
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,final ModelAndViewContainer mavContainer,final NativeWebRequest webRequest,final WebDataBinderFactory binderFactory) throws Exception {

        String name = webRequest.getParameter("user");
        System.out.println(name);
        if (name == null || name.isEmpty()) {
            throw new InvalidCredentials("User name or password is empty");
        }
        String password = webRequest.getParameter("password");
        System.out.println(password);
        if (password == null || password.isEmpty()) {
            throw new InvalidCredentials("User name or password is empty");
        }
        return repository.getUser(name, password);
    }

}
