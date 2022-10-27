package com.example.eventsmap.model;

import lombok.*;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Сущноть комментариев мероприятия
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "comment")
public class Comment {

    /**
     * id комментария в БД
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Комментарий
     */
    @Column(name = "text")
    private String text;

    /**
     * id пользоателя, написавший комментарий
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * Дата создания комментария
     */
    @Temporal(TIMESTAMP)
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * Мероприятие, которому относится комментарий
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "events_id", referencedColumnName = "id")
    private Events events;

}
