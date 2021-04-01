package com.zharov.sn.domain.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.zharov.sn.domain.entry.UserEntry;

public interface UserEntryRepository {

    @NotNull UserEntry save(@NotNull UserEntry userEntry);

    @NotNull Page<UserEntry> find(@NotNull PageRequest request);
}
