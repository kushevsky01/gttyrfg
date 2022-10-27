package com.example.eventsmap.dto;

import lombok.Data;

import java.util.Date;

/**
 * DTO сущности Events
 */
@Data
public class EventsDTO {

    /**
     * Id мероприятия
     */
    private long id;

    /**
     * Название мероприятия
     */
    private String title;

    /**
     * Координаты проведения мероприятия
     */
    private int coordinates;

    /**
     * Дата проведения мероприятия
     */
    private Date date;

    /**
     * Дата создания мероприятия
     */
    private Date createdTime;

    /**
     * Дата изменения мероприятия
     */
    private Date updateTime;

    /**
     * Оценка мероприятия
     */
    private Integer rating;

    /**
     * Описание мероприятия
     */
    private String description;

    /**
     * Id города, в котором будет мероприятие
     */
    private long cityId;
}
