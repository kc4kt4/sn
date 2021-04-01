package com.zharov.sn.service.impl;

import com.zharov.sn.domain.entry.UserEntry;
import com.zharov.sn.domain.mapper.UserMapper;
import com.zharov.sn.domain.model.CustomPage;
import com.zharov.sn.domain.model.User;
import com.zharov.sn.domain.repository.UserEntryRepository;
import com.zharov.sn.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserEntryRepository repository;
    private final UserMapper mapper;

    @Override
    public @NotNull User save(final @NotNull User user) {
        return Optional
                .ofNullable(mapper.toEntry(user))
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("input user is null"));
    }

    @Override
    public @NotNull CustomPage<User> findByUserName(final int page, final int count) {
        final PageRequest pageRequest = PageRequest.of(page, count);
        final Page<UserEntry> entries = repository.find(pageRequest);

        final List<User> content = entries.getContent().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return new CustomPage<User>()
                .setContent(content)
                .setPage(page)
                .setTotalElements(entries.getTotalElements())
                .setTotalPages(entries.getTotalPages());
    }

    @Override
    public @NotNull Optional<User> findByUserName(final @NotNull String userName) {
        return repository.find(userName)
                .map(mapper::toDto);
    }
}
