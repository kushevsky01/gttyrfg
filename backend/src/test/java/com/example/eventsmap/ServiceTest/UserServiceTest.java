package com.example.eventsmap.ServiceTest;

import com.example.eventsmap.dto.UserDTO;
import com.example.eventsmap.model.User;
import com.example.eventsmap.repository.UserRepository;
import com.example.eventsmap.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    private final UserRepository userRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;

    private User user;

    @Autowired
    UserServiceTest(UserService userService, UserRepository userRepository, ModelMapper modelMapper){
        this.userService = userService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(1L)
                .name("test")
                .username("testUserName")
                .email("test@email.ru")
                .password("testTest")
                .build();

    }

    @Test
    void createUser(){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        UserDTO userTest = userService.createUser(userDTO);
        assertNotNull(userTest);
        assertEquals(user.getName(), userTest.getName());
        assertEquals(user.getUsername(), userTest.getUsername());
    }

    @Test
    void getAllUsers(){
        createUser();
        List<UserDTO> usersTest = userService.getAllUsers();
        List<User> users = userRepository.findAll();
        assertEquals(users.size(), usersTest.size());
    }

    @Test
    void getUserById(){
        createUser();
        UserDTO userTest = userService.getUserById(1L);
        assertNotNull(userTest);
        assertEquals(user.getName(), userTest.getName());
        assertThrows(ResponseStatusException.class, () -> userService.getUserById(2L));
    }

    @Test
    void updateUser(){
        createUser();
        User userToUpdate= User.builder()
                .name("test2")
                .username("test2Username")
                .email("email2@mail.ru")
                .password("Test2Test2")
                .build();

        UserDTO userDTOUpdate = modelMapper.map(userToUpdate, UserDTO.class);
        UserDTO updatedUser = userService.updateUser(user.getId(), userDTOUpdate);
        assertEquals("test2", updatedUser.getName());
        assertEquals("test2Username", updatedUser.getUsername());
        assertEquals("email2@mail.ru", updatedUser.getEmail());
        assertThrows(ResponseStatusException.class, () -> userService.updateUser(2L, userDTOUpdate));
    }

    @Test
    void deleteUSer(){
        createUser();

        int size = userService.getAllUsers().size();
        assertEquals(1, size);

        assertDoesNotThrow(() -> userService.deleteUser(1L));

        int sizeAfterDelete = userService.getAllUsers().size();
        assertEquals(0, sizeAfterDelete);
        assertThrows(ResponseStatusException.class, () -> userService.deleteUser(2L));
    }
}
