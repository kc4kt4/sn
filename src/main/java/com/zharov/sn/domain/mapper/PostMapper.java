package com.zharov.sn.domain.mapper;

import com.zharov.sn.domain.entry.PostEntry;
import com.zharov.sn.domain.model.Post;
import org.jetbrains.annotations.Nullable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Nullable Post toDto(@Nullable PostEntry entry);

    @Nullable PostEntry toEntry(@Nullable Post user);
}