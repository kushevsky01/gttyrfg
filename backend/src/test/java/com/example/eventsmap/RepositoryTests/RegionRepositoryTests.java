package com.example.eventsmap.RepositoryTests;

import com.example.eventsmap.model.Region;
import com.example.eventsmap.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Тесты для репозитория сущности Region
 */
@DataJpaTest
public class RegionRepositoryTests {
    private TestEntityManager entityManager;

    private RegionRepository regionRepository;

    private Region regionTest = new Region(
            "Test");

    @Autowired
    RegionRepositoryTests(TestEntityManager entityManager, RegionRepository regionRepository){
        this.entityManager = entityManager;
        this.regionRepository = regionRepository;
    }

    @BeforeEach
    public void set(){
        entityManager.persist(regionTest);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void whenFindByName_thenReturnRegion(){
        Optional<Region> regionFound = regionRepository.findByName(regionTest.getName());
        assertThat(regionFound.get().getName())
                .isEqualTo(regionTest.getName());
    }
}
