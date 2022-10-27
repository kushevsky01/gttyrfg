package com.example.eventsmap.RepositoryTests;

import com.example.eventsmap.model.Country;
import com.example.eventsmap.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Тесты для репозитория сущности Country
 */
@DataJpaTest
public class CountryRepositoryTests {
    private TestEntityManager entityManager;

    private CountryRepository countryRepository;

    private Country countryTest = new Country(
            "Test");

    @Autowired
    CountryRepositoryTests(TestEntityManager entityManager, CountryRepository countryRepository){
        this.entityManager = entityManager;
        this.countryRepository = countryRepository;
    }

    @BeforeEach
    public void set(){
        entityManager.persist(countryTest);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void whenFindByName_thenReturnCountry(){
        Optional<Country> countryFound = countryRepository.findByName(countryTest.getName());
        assertThat(countryFound.get().getName())
                .isEqualTo(countryTest.getName());
    }
}
