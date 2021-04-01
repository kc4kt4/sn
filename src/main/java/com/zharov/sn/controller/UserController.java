package com.zharov.sn.controller;

import com.zharov.sn.domain.model.CustomPage;
import com.zharov.sn.domain.model.User;
import com.zharov.sn.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User save(@RequestBody final User user) {
        return service.save(user);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomPage<User> find(@RequestParam(defaultValue = "0") final int page,
                                 @RequestParam(defaultValue = "10") final int count) {
        return service.findByUserName(page, count);
    }
}
