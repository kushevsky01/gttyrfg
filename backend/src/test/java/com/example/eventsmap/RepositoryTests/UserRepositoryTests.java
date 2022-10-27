package com.example.eventsmap.RepositoryTests;

import com.example.eventsmap.model.User;
import com.example.eventsmap.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Тесты для репозитория сущности User
 */
@DataJpaTest
public class UserRepositoryTests {
    private TestEntityManager entityManager;

    private UserRepository userRepository;

    private User userTest = new User(
            "TestName",
            "TestUsername",
            "Tests@email.ru");

    @Autowired
    UserRepositoryTests(TestEntityManager entityManager, UserRepository userRepository){
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void set(){
        entityManager.persist(userTest);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void whenFindByName_thenReturnUser(){
        Optional<User> userFound = userRepository.findByName(userTest.getName());
        assertThat(userFound.get().getName())
                .isEqualTo(userTest.getName());
    }

    @Test
    public void whenFindByUsername_thenReturnUser(){
        Optional<User> userFound = userRepository.findByUsername(userTest.getUsername());
        assertThat(userFound.get().getUsername())
                .isEqualTo(userTest.getUsername());
    }

    @Test
    public void whenFindByEmail_thenReturnUser() {
        Optional<User> userFound = userRepository.findByEmail(userTest.getEmail());
        assertThat(userFound.get().getEmail())
                .isEqualTo(userTest.getEmail());

    }
}
