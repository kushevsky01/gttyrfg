package com.example.eventsmap.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Сущноть мероприятий
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "events")
public class Events {

    /**
     * id мероприятия в БД
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Координаты, где будет проходить мероприятие
     */
    @Column(name = "coordinates")
    private int coordinates;

    /**
     * Назание мероприятия
     */
    @NotEmpty(message = "Введите название мероприятия")
    @Column(name = "title")
    private String title;

    /**
     * Дата мероприятия
     */
    @Temporal(TIMESTAMP)
    @Column(name = "date")
    private Date date;

    /**
     * Дата создания мероприятия
     */
    @Temporal(TIMESTAMP)
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * Дата изменения мероприятия
     */
    @Temporal(TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * Оценка мероприятия
     */
    @DecimalMin(message = "Оценка должна быть положительной", value = "1")
    @DecimalMax(message = "Оценка не может быть выше 10", value = "10")
    @Column(name = "rating")
    private Integer rating;

    /**
     * Описание мероприятия
     */
    @Column(name = "description")
    private String description;

    /**
     * Пользователь, создаший мероприятие
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;

    /**
     * Город, в котором проходит мероприятие
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @ToString.Exclude
    private City city;

    /**
     * Список комментариев для мероприятие
     */
    @OneToMany(mappedBy = "events", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "userEvents")
    @ToString.Exclude
    private List<User> users = new ArrayList<>();

    public Events(String title) {
        this.title = title;

    }
}
