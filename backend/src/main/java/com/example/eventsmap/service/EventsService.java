package com.example.eventsmap.service;

import com.example.eventsmap.dto.EventsDTO;
import com.example.eventsmap.model.City;
import com.example.eventsmap.model.Events;
import com.example.eventsmap.repository.CityRepository;
import com.example.eventsmap.repository.EventsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис с операциями над сущностью Events
 */
@Service
public class EventsService {

    /**
     * Репозиторий мероприятий
     */
    private final EventsRepository eventsRepository;

    /**
     * Репозиторий городов
     */
    private final CityRepository cityRepository;

    /**
     * Маппер для конвертирования сущности
     */
    private final ModelMapper modelMapper;

    /**
     * Инициализация полей класса
     */
    @Autowired
    public EventsService(EventsRepository eventsRepository, CityRepository cityRepository, ModelMapper modelMapper1) {
        this.eventsRepository = eventsRepository;
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper1;
    }

    /**
     * Ищет ввсе мероприятия
     * @return найденные мероприятия
     */
    public List<EventsDTO> getAllEvents() {
        List<Events> events = eventsRepository.findAll();
        return events
                .stream()
                .map(event -> modelMapper.map(event, EventsDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Ищет мероприятия по id
     * @param id id мероприятия
     * @return найденное меропритяие
     */
    public EventsDTO getEventById(long id) {
        Optional<Events> event = Optional.ofNullable(eventsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return modelMapper.map(event, EventsDTO.class);
    }

    /**
     * Создает мероприятия
     * @param eventDTO мероприятие, которое нужно создать
     * @return созданное мероприятие
     */
    public EventsDTO createEvent(EventsDTO eventDTO) {
        Events event = modelMapper.map(eventDTO, Events.class);
        Optional<City> optionalCity = Optional.ofNullable(cityRepository.findById(eventDTO.getCityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        City city = optionalCity.get();
        event.setCity(city);
        event.setCreatedTime(new Date());
        event.setUpdateTime(new Date());
        return modelMapper.map(eventsRepository.save(event), EventsDTO.class);
    }

    /**
     * Обновляет меропритие
     * @param id id мероприятия
     * @param eventDetails мероприятие со значениями, на которые нужно поменят
     * @return обновленное мероприятие
     */
    public EventsDTO updateEvent(long id, EventsDTO eventDetails) {
        Optional<Events> optionalEvent = Optional.ofNullable(eventsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        Events event = optionalEvent.get();
        event.setTitle(eventDetails.getTitle());
        event.setCoordinates(eventDetails.getCoordinates());
        event.setDate(eventDetails.getDate());
        event.setUpdateTime(new Date());
        event.setRating(eventDetails.getRating());
        event.setDescription(eventDetails.getDescription());
        return modelMapper.map(eventsRepository.save(event), EventsDTO.class);
    }

    /**
     * Удаляет мероприятие
     * @param id id мероприятия
     */
    public void deleteEvent(long id) {
        Optional<Events> event = Optional.ofNullable(eventsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        eventsRepository.deleteById(id);
    }
}
