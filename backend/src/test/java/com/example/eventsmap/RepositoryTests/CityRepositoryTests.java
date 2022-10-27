package com.example.eventsmap.RepositoryTests;

import com.example.eventsmap.model.City;
import com.example.eventsmap.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Тесты для репозитория сущности City
 */
@DataJpaTest
public class CityRepositoryTests {
    private final TestEntityManager entityManager;

    private final CityRepository cityRepository;

    private final City cityTest = new City(
            "Test");

    @Autowired
    CityRepositoryTests(TestEntityManager entityManager, CityRepository cityRepository){
        this.entityManager = entityManager;
        this.cityRepository = cityRepository;
    }

    @BeforeEach
    public void set(){
        entityManager.persist(cityTest);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void whenFindByName_thenReturnCity(){
        Optional<City> cityFound = cityRepository.findByName(cityTest.getName());
        assertThat(cityFound.get().getName())
                .isEqualTo(cityTest.getName());
    }
}
