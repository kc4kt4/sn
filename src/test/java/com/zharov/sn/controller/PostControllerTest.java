package com.zharov.sn.controller;

import com.zharov.sn.service.PostService;
import com.zharov.sn.service.UserService;
import com.zharov.sn.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ContextConfiguration(classes = {PostController.class})
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class PostControllerTest {
    MockMvc mockMvc;
    @Autowired
    PostController controller;
    @MockBean
    PostService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ControllerAdviceExceptionHandler())
                .build();

        reset(service);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(service);
    }

    @Test
    @Disabled("notImplemented")
    void save() {
    }

    @Test
    @Disabled("notImplemented")
    void find() {
    }
}