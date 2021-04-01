package com.zharov.sn.controller;

import com.zharov.sn.domain.model.CustomPage;
import com.zharov.sn.domain.model.User;
import com.zharov.sn.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserController.class})
class UserControllerTest {
    MockMvc mockMvc;
    @Autowired
    UserController controller;
    @MockBean
    UserService service;

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
    void save() throws Exception {
        when(service.save(new User().setUserName("iName"))).thenReturn(new User().setUserName("oName"));

        //test
        mockMvc.perform(post("/users")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"userName\":\"iName\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"userName\":\"oName\"}"));

        verify(service, times(1)).save(new User().setUserName("iName"));
    }

    @Test
    void find() throws Exception {
        final User user1 = new User().setUserName("uName1");
        final User user2 = new User().setUserName("uName2");
        final User user3 = new User().setUserName("uName3");
        final CustomPage<User> page = new CustomPage<User>()
                .setPage(0)
                .setContent(Arrays.asList(user1, user2, user3))
                .setTotalElements(5)
                .setTotalPages(2);
        when(service.findByUserName(0, 5)).thenReturn(page);

        //test
        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .param("count", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.totalPages").value(2))
                .andExpect(jsonPath("$.totalElements").value(5))
                .andExpect(jsonPath("$.content[0].userName").value("uName1"))
                .andExpect(jsonPath("$.content[1].userName").value("uName2"))
                .andExpect(jsonPath("$.content[2].userName").value("uName3"));

        verify(service, times(1)).findByUserName(0, 5);
    }
}