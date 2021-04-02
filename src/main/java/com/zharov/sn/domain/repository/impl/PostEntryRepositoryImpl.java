package com.zharov.sn.domain.repository.impl;

import com.zharov.sn.domain.entry.PostEntry;
import com.zharov.sn.domain.repository.PostEntryRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PostEntryRepositoryImpl implements PostEntryRepository {
    private final PostEntryMongoRepository repository;

    @Override
    public @NotNull PostEntry save(@NotNull final PostEntry entry) {
        return repository.save(entry);
    }

    @Override
    public @NotNull Page<PostEntry> findByUserName(@NotNull final PageRequest request, @NotNull final String userName) {
        return repository.findAllByUserName(userName, request);
    }
}
