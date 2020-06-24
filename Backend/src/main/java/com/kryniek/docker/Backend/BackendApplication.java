package com.kryniek.docker.Backend;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static lombok.AccessLevel.PACKAGE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}

@Component
@RequiredArgsConstructor
class UserDataLoader implements ApplicationRunner {
    private final UserRepository repository;

    @Override
    public void run(ApplicationArguments args) {
        repository.saveAll(List.of(
                User.builder()
                        .name("Name1")
                        .surname("Surname1")
                        .build(),
                User.builder()
                        .name("Name2")
                        .surname("Surname2")
                        .build(),
                User.builder()
                        .name("Name3")
                        .surname("Surname3")
                        .build()));
    }
}

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
class UserController {
    private final UserRepository repository;

    @GetMapping
    public List<User> getAll() {
        return stream(repository.findAll().spliterator(), false).collect(toList());
    }
}

interface UserRepository extends CrudRepository<User, Integer> {
}

@Builder
@NoArgsConstructor(access = PACKAGE)
@AllArgsConstructor(access = PACKAGE)
@Getter
@Entity
class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
}