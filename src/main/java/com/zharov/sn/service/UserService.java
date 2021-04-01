package com.zharov.sn.service;

import com.zharov.sn.domain.model.CustomPage;
import com.zharov.sn.domain.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface UserService {

    @NotNull User save(@NotNull User user);

    @NotNull CustomPage<User> findByUserName(int page, int count);

    @NotNull Optional<User> findByUserName(@NotNull String userName);
}
