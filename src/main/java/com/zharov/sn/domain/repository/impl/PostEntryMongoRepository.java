package com.zharov.sn.domain.repository.impl;

import com.zharov.sn.domain.entry.PostEntry;
import com.zharov.sn.domain.entry.UserEntry;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PostEntryMongoRepository extends MongoRepository<PostEntry, String> {

    Page<PostEntry> findAllByUserName(@NotNull String userName, @NotNull PageRequest request);
}
