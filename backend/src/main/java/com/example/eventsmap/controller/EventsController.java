package com.example.eventsmap.controller;

import com.example.eventsmap.dto.EventsDTO;
import com.example.eventsmap.service.EventsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Rest контроллер для операций над сущностью Events
 */
@RestController
@RequestMapping("/events")
public class EventsController {

    /**
     * Сервис с операциями над сущностью Events
     */
    private final EventsService eventsService;

    /**
     * Инициализация полей класса
     */
    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    /**
     * Запрос на поиск всех мероприятий
     * @return все мероприятия
     */
    @GetMapping
    public List<EventsDTO> getAllEvents() {

        return eventsService.getAllEvents();
    }

    /**
     * Запрос на поиск мероприятия по id
     * @param eventId id мероприятия
     * @return найденное мероприятие в формате JSON
     */
    @GetMapping("/{eventId}")
    public ResponseEntity<EventsDTO> getEventById(@PathVariable(name = "eventId") long eventId){
        EventsDTO event = eventsService.getEventById(eventId);

        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    /**
     * Запрос на создание мероприятия
     * @parm eventDTO тело запроса
     * @return созданное мероприятие в формате JSON
     */
    @PostMapping
    public EventsDTO createEvent(@RequestBody EventsDTO eventDTO){

        return eventsService.createEvent(eventDTO);
    }

    /**
     * Запрос на обноление данных мероприятия
     * @param eventId id пероприятия
     * @param eventDTO тело запроса
     * @return обноленное мероприятие в формате JSON
     */
    @PutMapping("/{eventId}")
    public ResponseEntity<EventsDTO> updateEvent(@PathVariable(name = "eventId") long eventId, @RequestBody EventsDTO eventDTO){
        EventsDTO event = eventsService.updateEvent(eventId, eventDTO);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    /**
     * Запрос на удаление мероприятия
     * @param eventId id мероприятия
     * @return статус запроса
     */
    @DeleteMapping("/{eventId}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable(name = "eventId") long eventId){
        eventsService.deleteEvent(eventId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
