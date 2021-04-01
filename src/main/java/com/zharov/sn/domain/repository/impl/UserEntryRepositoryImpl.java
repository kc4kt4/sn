package com.zharov.sn.domain.repository.impl;

import com.zharov.sn.domain.entry.UserEntry;
import com.zharov.sn.domain.repository.UserEntryRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserEntryRepositoryImpl implements UserEntryRepository {
    private final UserEntryMongoRepository repository;

    @Override
    public @NotNull UserEntry save(final @NotNull UserEntry userEntry) {
        return repository.save(userEntry);
    }

    @Override
    public @NotNull Page<UserEntry> find(final @NotNull PageRequest request) {
        return repository.findAll(request);
    }
}
