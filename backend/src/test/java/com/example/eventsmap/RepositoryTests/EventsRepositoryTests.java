package com.example.eventsmap.RepositoryTests;

import com.example.eventsmap.model.Events;
import com.example.eventsmap.repository.EventsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Тесты для репозитория сущности Events
 */
@DataJpaTest
public class EventsRepositoryTests {
    private TestEntityManager entityManager;

    private EventsRepository eventsRepository;

    private Events eventTest = new Events(
            "Test"
        );

    @Autowired
    EventsRepositoryTests(TestEntityManager entityManager, EventsRepository eventsRepository){
        this.entityManager = entityManager;
        this.eventsRepository = eventsRepository;
    }

    @BeforeEach
    public void set(){
        entityManager.persist(eventTest);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void whenFindByTitle_thenReturnEvent(){
        Optional<Events> eventFound = eventsRepository.findByTitle(eventTest.getTitle());
        assertThat(eventFound.get().getTitle())
                .isEqualTo(eventTest.getTitle());
    }
}
