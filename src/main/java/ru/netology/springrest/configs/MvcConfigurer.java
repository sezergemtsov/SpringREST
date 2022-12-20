package ru.netology.springrest.configs;

import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.netology.springrest.repository.UserRepository;

import java.util.List;

@Component
public class MvcConfigurer implements WebMvcConfigurer {

    private final UserRepository repository;

    public MvcConfigurer(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
         resolvers.add(new UserHandlerMethodArgumentResolver(repository));
    }
}
