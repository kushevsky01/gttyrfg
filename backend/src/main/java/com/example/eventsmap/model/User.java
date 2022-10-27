package com.example.eventsmap.model;

import lombok.*;
import javax.validation.constraints.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Сущноть пользователя
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    /**
     * id пользователя в БД
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Имя пользователя
     */
    @NotEmpty(message = "Поле имя не заполнено")
    @Column(name = "name", length = 128)
    private String name;

    /**
     * Логин пользователя
     */
    @NotEmpty(message = "Поле логин не заполнено")
    @Column(name = "username", length = 128)
    private String username;

    /**
     * Пароль пользователя
     */
    @Size(min= 8, max = 50, message = "Пароль должен состоять минимум из 6 символов")
    @Column(name = "password")
    private String password;

    /**
     * Email пользователя
     */
    @Email(message = "Неправильно введён email")
    @NotEmpty(message = "Поле email не заполнено")
    @Column(name = "email", length = 50)
    private String email;

    /**
     * Роль пользователя (Admin, User, Moderator, Guest)
     */
    @Column(name = "role", length = 50)
    private String role;

    /**
     * Активность пользователя active(true)/banned(false)
     */
    @Column(name = "active")
    private boolean active;

    /**
     * Список мероприятий, принадлежащих пользователю
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Events> eventsList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_events",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "events_id")}
    )
    @ToString.Exclude
    private List<Events> userEvents = new ArrayList<>();

    public User(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }
}
