package com.zharov.sn.service.impl;

import com.zharov.sn.domain.entry.PostEntry;
import com.zharov.sn.domain.mapper.PostMapper;
import com.zharov.sn.domain.model.CustomPage;
import com.zharov.sn.domain.model.Post;
import com.zharov.sn.domain.repository.PostEntryRepository;
import com.zharov.sn.service.PostService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostEntryRepository repository;
    private final PostMapper mapper;

    @Override
    public @NotNull Post save(@NotNull final Post post) {
        return Optional
                .ofNullable(mapper.toEntry(post))
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("input user is null"));
    }

    @Override
    public @NotNull CustomPage<Post> findByUserName(final int page, final int count, @NotNull final String userName) {
        final Page<PostEntry> entries = repository.findByUserName(PageRequest.of(page, count), userName);

        final List<Post> content = entries.getContent().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return new CustomPage<Post>()
                .setTotalPages(entries.getTotalPages())
                .setTotalElements(entries.getTotalElements())
                .setPage(page)
                .setContent(content);
    }
}
