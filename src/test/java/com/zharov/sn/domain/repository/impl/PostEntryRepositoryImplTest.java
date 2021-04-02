package com.zharov.sn.domain.repository.impl;

import com.zharov.sn.AbstractMongoDBContainer;
import com.zharov.sn.domain.entry.PostEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PostEntryRepositoryImplTest extends AbstractMongoDBContainer {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    PostEntryRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection(PostEntry.class);
    }

    @Test
    @Disabled("notImplementer")
    void save() {
    }

    @Test
    void findByUserName() {
        mongoTemplate.save(new PostEntry().setUserName("user1").setText("text1"));
        mongoTemplate.save(new PostEntry().setUserName("user1").setText("text2"));
        mongoTemplate.save(new PostEntry().setUserName("user2").setText("text3"));

        //test
        final Page<PostEntry> result = repository.findByUserName(PageRequest.of(0, 5), "user1");

        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent())
                .hasSize(2)
                .matches(e -> e.stream().allMatch(r -> r.getUserName().equals("user1")))
                .matches(e -> e.get(0).getText().equals("text1"))
                .matches(e -> e.get(1).getText().equals("text2"));
    }
}