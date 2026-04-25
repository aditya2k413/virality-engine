package com.microservice.controller;

import com.microservice.model.InteractionType;
import com.microservice.model.Post;
import com.microservice.service.PostService;
import com.microservice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private RedisService redisService;

    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }
    @PostMapping("/{postId}/like")
    public void likePost(@PathVariable Long postId){
        redisService.updateViralityScore(postId, InteractionType.LIKE);
    }

}
