package com.zharov.sn.domain.repository;

import com.zharov.sn.domain.entry.PostEntry;
import com.zharov.sn.domain.entry.UserEntry;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface PostEntryRepository {

    @NotNull PostEntry save(@NotNull PostEntry userEntry);

    @NotNull Page<PostEntry> findByUserName(@NotNull PageRequest request, @NotNull String userName);
}
