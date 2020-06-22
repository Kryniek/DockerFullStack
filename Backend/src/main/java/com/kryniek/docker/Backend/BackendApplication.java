package com.kryniek.docker.Backend;

import java.util.List;

import lombok.Builder;
import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}

@RestController
@RequestMapping("api/users")
class UserController {
    @GetMapping
    public List<User> getAll() {
        return List.of(
                User.builder()
                        .id(1)
                        .name("Name1")
                        .surname("Surname1")
                        .build(),
                User.builder()
                        .id(2)
                        .name("Name2")
                        .surname("Surname2")
                        .build(),
                User.builder()
                        .id(3)
                        .name("Name3")
                        .surname("Surname3")
                        .build());
    }
}

@Builder
@Value
class User {
    Integer id;
    String name;
    String surname;
}