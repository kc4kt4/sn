package com.zharov.sn.controller;

import com.zharov.sn.domain.model.CustomPage;
import com.zharov.sn.domain.model.Post;
import com.zharov.sn.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Post save(@RequestBody final Post post) {
        return service.save(post);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomPage<Post> find(@RequestParam(defaultValue = "0") final int page,
                                 @RequestParam(defaultValue = "10") final int count,
                                 @RequestHeader(value = "X-UserName") String userName) {
        return service.findByUserName(page, count, userName);
    }
}
