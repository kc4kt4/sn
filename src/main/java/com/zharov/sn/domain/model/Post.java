package com.zharov.sn.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Post {
    private String id;
    private String userName;
    private String text;
}
