package com.microservice.service;

import com.microservice.model.Comment;
import com.microservice.model.InteractionType;
import com.microservice.model.Post;
import com.microservice.repository.CommentRepository;
import com.microservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private PostRepository postRepository;

    public Comment addComment(Comment comment ,Long postId){
        Post post = null;
        if (comment.getAuthorType().equals("BOT")) {
            post = postRepository.findById(postId).orElseThrow(()->new RuntimeException("Post not found"));
            if (redisService.isBotLimitExceeded(postId)) throw new RuntimeException("Bot limit exceeded for this post");
            else if (redisService.isDepthExceeded(comment.getDepthLevel()))
                throw new RuntimeException("DepthLevel exceeded for this post");
            else if (redisService.isCooldownActive(comment.getAuthorId(), post.getAuthorId()))
                throw new RuntimeException("Cooldown Active");
        }
        comment.setCreatedAt(LocalDateTime.now());
        comment.setPostId(postId);
        Comment save =commentRepository.save(comment);
        if (comment.getAuthorType().equals("BOT")){
            redisService.sendQueueNotification(post.getAuthorId(),"Bot interacted with your post");
            redisService.updateViralityScore(postId, InteractionType.BOT_REPLY);
        }
        else redisService.updateViralityScore(postId,InteractionType.COMMENT);
        return save;

    }
}
