package com.zharov.sn.domain.repository.impl;

import com.zharov.sn.AbstractMongoDBContainer;
import com.zharov.sn.domain.entry.UserEntry;
import com.zharov.sn.domain.repository.impl.UserEntryRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserEntryRepositoryImplTest extends AbstractMongoDBContainer {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    UserEntryRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection(UserEntry.class);
    }

    @Test
    void save() {
        Assertions.assertThat(mongoTemplate.findAll(UserEntry.class).size())
                .isEqualTo(0);
        final UserEntry entry = new UserEntry()
                .setUserName("userName")
                .setPwd("pwd")
                .setEmail("email@email.com");

        //test
        final UserEntry result = repository.save(entry);

        Assertions.assertThat(result)
                .isNotNull()
                .matches(e -> e.getId() != null)
                .matches(e -> e.getEmail().equals(entry.getEmail()))
                .matches(e -> e.getUserName().equals(entry.getUserName()))
                .matches(e -> e.getPwd().equals(entry.getPwd()));
        Assertions.assertThat(mongoTemplate.findAll(UserEntry.class))
                .matches(e -> e.size() == 1)
                .element(0)
                .isEqualTo(result);
    }

    @Test
    void findAll() {
        final UserEntry entry = new UserEntry()
                .setUserName("userName")
                .setPwd("pwd")
                .setEmail("email@email.com");
        final UserEntry saved = mongoTemplate.save(entry);

        //test
        final Page<UserEntry> result = repository.find(PageRequest.of(0, 10));

        Assertions.assertThat(result.getTotalElements())
                .isEqualTo(1);
        Assertions.assertThat(result.getTotalPages())
                .isEqualTo(1);
        Assertions.assertThat(result.getContent())
                .hasSize(1)
                .element(0)
                .isEqualTo(saved);
    }

    @Test
    @Disabled("notImplemented")
    void find() {

    }
}