package com.zharov.sn.domain.entry;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "posts")
public class PostEntry {
    @Id
    private String id;
    private String userName;
    private String text;
}
