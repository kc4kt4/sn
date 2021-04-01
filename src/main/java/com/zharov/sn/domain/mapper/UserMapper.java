package com.zharov.sn.domain.mapper;

import org.jetbrains.annotations.Nullable;
import com.zharov.sn.domain.entry.UserEntry;
import com.zharov.sn.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Nullable User toDto(@Nullable UserEntry entry);

    @Nullable UserEntry toEntry(@Nullable User user);
}