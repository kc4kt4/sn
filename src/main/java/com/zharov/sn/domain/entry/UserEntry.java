package com.zharov.sn.domain.entry;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class UserEntry {
    @Id
    private String id;
    private String userName;
    private String email;
    private String pwd;
}
