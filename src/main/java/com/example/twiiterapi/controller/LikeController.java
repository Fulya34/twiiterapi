package com.example.twiiterapi.controller;

import com.example.twiiterapi.model.Like;
import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.model.User;
import com.example.twiiterapi.service.LikeService;
import com.example.twiiterapi.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<Like> likeTweet(@RequestParam Long tweetId,
                                          @AuthenticationPrincipal User user) {
        Optional<Tweet> tweet = tweetService.getTweetById(tweetId);
        if (tweet.isEmpty()) return ResponseEntity.badRequest().build();

        Like like = likeService.likeTweet(user, tweet.get());
        return ResponseEntity.ok(like);
    }

    @PostMapping("/dislike")
    public ResponseEntity<Void> dislikeTweet(@RequestParam Long tweetId,
                                             @AuthenticationPrincipal User user) {
        Optional<Tweet> tweet = tweetService.getTweetById(tweetId);
        if (tweet.isEmpty()) return ResponseEntity.badRequest().build();

        likeService.dislikeTweet(user, tweet.get());
        return ResponseEntity.noContent().build();
    }
}