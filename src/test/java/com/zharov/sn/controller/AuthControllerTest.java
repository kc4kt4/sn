package com.zharov.sn.controller;

import com.zharov.sn.domain.model.User;
import com.zharov.sn.filter.AuthFilter;
import com.zharov.sn.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {AuthController.class, AuthFilter.class})
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    MockMvc mockMvc;
    @Autowired
    AuthController controller;
    @Autowired
    AuthFilter filter;
    @MockBean
    UserService userService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ControllerAdviceExceptionHandler())
                .addFilter(filter)
                .build();

        reset(userService);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(userService);
    }

    @Test
    void login_200() throws Exception {
        final User user = new User()
                .setUserName("user-name")
                .setPwd("password")
                .setEmail("email-email@gmail.com");
        when(userService.findByUserName("user-name")).thenReturn(Optional.of(user));
        final String token = DigestUtils.sha256Hex(user.getUserName() + user.getEmail());

        //test
        mockMvc.perform(post("/auth/login")
                .header("X-UserName", "user-name")
                .header("X-Basic", Base64.encodeBase64String("user-name:password".getBytes())))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Token", token));

        verify(userService, times(1)).findByUserName("user-name");
    }

    @Test
    void login_403_missingUserName() throws Exception {
        //test
        mockMvc.perform(post("/auth/login")
                .header("X-Basic", Base64.encodeBase64String("user-name:password".getBytes())))
                .andExpect(status().isForbidden());
    }

    @Test
    void login_403_missingBasic() throws Exception {
        //test
        mockMvc.perform(post("/auth/login")
                .header("X-UserName", "user-name"))
                .andExpect(status().isForbidden());
    }

    @Test
    void login_403_incorrectBasic() throws Exception {
        //test
        mockMvc.perform(post("/auth/login")
                .header("X-Basic", Base64.encodeBase64String("user-name".getBytes())))
                .andExpect(status().isForbidden());
    }

    @Test
    void login_403_userNameNotMatches() throws Exception {
        //test
        mockMvc.perform(post("/auth/login")
                .header("X-UserName", "user-name-wrong")
                .header("X-Basic", Base64.encodeBase64String("user-name:password".getBytes())))
                .andExpect(status().isForbidden());
    }

    @Test
    void login_403_userNotFound() throws Exception {
        when(userService.findByUserName("user-name")).thenReturn(Optional.empty());

        //test
        mockMvc.perform(post("/auth/login")
                .header("X-UserName", "user-name")
                .header("X-Basic", Base64.encodeBase64String("user-name:password".getBytes())))
                .andExpect(status().isForbidden());

        verify(userService, times(1)).findByUserName("user-name");
    }

    @Test
    void login_403_passwordNotMatches() throws Exception {
        final User user = new User()
                .setUserName("user-name")
                .setPwd("password1")
                .setEmail("email-email@gmail.com");
        when(userService.findByUserName("user-name")).thenReturn(Optional.of(user));
        final String token = DigestUtils.sha256Hex(user.getUserName() + user.getEmail());

        //test
        mockMvc.perform(post("/auth/login")
                .header("X-UserName", "user-name")
                .header("X-Basic", Base64.encodeBase64String("user-name:password".getBytes())))
                .andExpect(status().isForbidden());

        verify(userService, times(1)).findByUserName("user-name");
    }

    @Test
    void logout() throws Exception {
        //test
        mockMvc.perform(post("/auth/logout")
                .header("X-UserName", "user-name")
                .header("X-Basic", Base64.encodeBase64String("user-name:password".getBytes())))
                .andExpect(status().isOk())
                .andExpect(header().doesNotExist("X-Token"));
    }
}