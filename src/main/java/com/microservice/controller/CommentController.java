package com.microservice.controller;

import com.microservice.model.Comment;
import com.microservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("{postId}/comments")
    public Comment createComment(@RequestBody Comment comment, @PathVariable Long postId){
        return commentService.addComment(comment,postId);

    }

}
