package com.zharov.sn.domain.repository.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.zharov.sn.domain.entry.UserEntry;

import java.util.Optional;

public interface UserEntryMongoRepository extends MongoRepository<UserEntry, String> {

    Optional<UserEntry> findByUserName(@NotNull String userName);
}
