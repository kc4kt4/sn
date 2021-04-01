package com.zharov.sn.service;

import com.zharov.sn.domain.model.CustomPage;
import com.zharov.sn.domain.model.User;
import org.jetbrains.annotations.NotNull;

public interface UserService {

    @NotNull User save(@NotNull User user);

    @NotNull CustomPage<User> find(int page, int count);
}
