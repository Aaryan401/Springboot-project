package com.Example.BlogByMay.Controller.User;

import com.example.BlogBYMay.Controller.UserController;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Service.User.UserServiceImpl;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.http.client.MockClientHttpResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    User user;

    @BeforeEach
    public void setUp(){
    user=User.builder()
            .userId(1L)
            .firstName("Aaryan")
            .lastName("Prashar")
            .email("aaryanuttam54321@gmail.com")
            .password("123456789")
            .build();

    }

    @AfterEach
    public void tearDown(){
        user=null;
    }

    @Test
    public void testSaveUser() throws MessagingException{
        when(userService.saveUser(user)).thenReturn("Your account has been created");

        ResponseEntity<String> response = userController.saveUser(user);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Your account has been created", response.getBody());
        verify(userService, times(1)).saveUser(user);

    }

}
