package com.zharov.sn.service.impl;

import com.zharov.sn.domain.mapper.UserMapper;
import com.zharov.sn.domain.model.User;
import com.zharov.sn.domain.repository.UserEntryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @Autowired
    UserServiceImpl service;
    @MockBean
    UserEntryRepository repository;
    @MockBean
    UserMapper mapper;

    @BeforeEach
    void setUp() {
        Mockito.reset(
                repository,
                mapper
        );
    }

    @AfterEach
    void tearDown() {
        Mockito.verifyNoMoreInteractions(repository);
        Mockito.verifyNoMoreInteractions(mapper);
    }

    @Test
    @Disabled("notImplemented")
    void save() {
    }

    @Test
    @Disabled("notImplemented")
    void findAll() {
    }

    @Test
    @Disabled("notImplemented")
    void find() {
    }
}