package com.zharov.sn.domain.entry;

import lombok.Data;

@Data
public class UserEntry {
    private String id;
    private String userName;
    private String email;
    private String pwd;
}
