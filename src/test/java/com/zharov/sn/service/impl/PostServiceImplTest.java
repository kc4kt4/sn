package com.zharov.sn.service.impl;

import com.zharov.sn.domain.mapper.PostMapper;
import com.zharov.sn.domain.repository.PostEntryRepository;
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

@ContextConfiguration(classes = {PostServiceImpl.class})
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class PostServiceImplTest {
    @Autowired
    PostServiceImpl service;
    @MockBean
    PostEntryRepository repository;
    @MockBean
    PostMapper mapper;

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
    void findByUserName() {
    }
}