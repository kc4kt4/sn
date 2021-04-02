package com.zharov.sn.service;

import com.zharov.sn.domain.model.CustomPage;
import com.zharov.sn.domain.model.Post;
import org.jetbrains.annotations.NotNull;

public interface PostService {

    @NotNull Post save(@NotNull Post userEntry);

    @NotNull CustomPage<Post> findByUserName(int page, int count, @NotNull String userName);

}
