package com.zharov.sn;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

import static org.assertj.core.api.Java6Assertions.assertThat;

public abstract class AbstractMongoDBContainer {
    static final MongoDBContainer MONGO = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    static {
        Startables.deepStart(Collections.singletonList(MONGO)).join();
    }

    @DynamicPropertySource
    private static void mongoDBProperties(final @NotNull DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", MONGO::getReplicaSetUrl);

    }

    @BeforeAll
    static void containers() {
        assertThat(MONGO.isRunning()).isTrue();
    }

}
