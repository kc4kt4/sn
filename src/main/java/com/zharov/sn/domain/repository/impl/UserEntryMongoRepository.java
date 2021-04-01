package com.zharov.sn.domain.repository.impl;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.zharov.sn.domain.entry.UserEntry;

public interface UserEntryMongoRepository extends MongoRepository<UserEntry, String> {
}
