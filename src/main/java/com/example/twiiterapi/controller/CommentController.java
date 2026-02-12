package com.example.twiiterapi.controller;

import com.example.twiiterapi.model.Comment;
import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.model.User;
import com.example.twiiterapi.service.CommentService;
import com.example.twiiterapi.service.TweetService;
import com.example.twiiterapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final TweetService tweetService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestParam Long tweetId,
                                                 @AuthenticationPrincipal User user,
                                                 @RequestBody Comment comment) {
        Optional<Tweet> tweet = tweetService.getTweetById(tweetId);
        if (tweet.isEmpty()) return ResponseEntity.badRequest().build();

        comment.setUser(user);
        comment.setTweet(tweet.get());
        Comment savedComment = commentService.createComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id,
                                                 @AuthenticationPrincipal User user,
                                                 @RequestBody Comment commentDetails) {
        Optional<Comment> existing = commentService.getCommentById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Comment comment = existing.get();
        // Sadece yorum sahibi veya tweet sahibi güncelleyebilir
        if (!comment.getUser().getId().equals(user.getId()) &&
                !comment.getTweet().getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }

        Comment updated = commentService.updateComment(comment, commentDetails.getContent());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id,
                                              @AuthenticationPrincipal User user) {
        Optional<Comment> existing = commentService.getCommentById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Comment comment = existing.get();
        if (!comment.getUser().getId().equals(user.getId()) &&
                !comment.getTweet().getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }

        commentService.deleteComment(comment);
        return ResponseEntity.noContent().build();
    }

}