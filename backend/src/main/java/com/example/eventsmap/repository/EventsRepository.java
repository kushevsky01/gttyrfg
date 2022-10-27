package com.example.eventsmap.repository;

import com.example.eventsmap.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий мероприятий
 */
@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {

    /**
     * Ищет мероприятия по названию
     * @param title мероприятия
     * @return найденное мероприятие
     */
    Optional<Events> findByTitle(String title);

}
