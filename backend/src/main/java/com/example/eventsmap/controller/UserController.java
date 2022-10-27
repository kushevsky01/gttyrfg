package com.example.eventsmap.controller;

import com.example.eventsmap.dto.UserDTO;
import com.example.eventsmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Rest контроллер для операций над сущностью USer
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Сервис с операциями над сущностью User
     */
    private final UserService userService;

    /**
     * Инициализация полей класса
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Запрос на поиск всех пользователей
     * @return всех пользователей
     */
    @GetMapping
    public List<UserDTO> getAllUsers() {

        return userService.getAllUsers() ;
    }

    /**
     * Запрос на поиск пользователя по id
     * @param userId id пользователя
     * @return найденного пользователя в формате JSON
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "userId") long userId) {
        UserDTO user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Запрос на создание пользователя
     * @param userDTO тело запроса
     * @return созданного пользователя в формате JSON
     */
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {

        return userService.createUser(userDTO);
    }

    /**
     * Запрос на обноление данных пользователя
     * @param userId пользователя
     * @param userDTO тело запроса
     * @return измененного пользователя в формате JSON
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "userId") long userId, @RequestBody UserDTO userDTO) {
        UserDTO user = userService.updateUser(userId, userDTO);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Запрос на удаление пользователя по id
     * @param userId пользователя
     * @return статус запроса
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable(name = "userId") long userId) {

        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
