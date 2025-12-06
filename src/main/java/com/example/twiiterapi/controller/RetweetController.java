package com.example.twiiterapi.controller;

import com.example.twiiterapi.model.Retweet;
import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.model.User;
import com.example.twiiterapi.service.RetweetService;
import com.example.twiiterapi.service.TweetService;
import com.example.twiiterapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/retweet")
public class RetweetController {

    private final RetweetService retweetService;
    private final UserService userService;
    private final TweetService tweetService;

    public RetweetController(RetweetService retweetService, UserService userService, TweetService tweetService) {
        this.retweetService = retweetService;
        this.userService = userService;
        this.tweetService = tweetService;
    }

    // POST /retweet → retweet at
    @PostMapping
    public ResponseEntity<Retweet> retweet(@RequestParam Long userId, @RequestParam Long tweetId) {
        Optional<User> user = userService.findById(userId);
        Optional<Tweet> tweet = tweetService.getTweetById(tweetId);

        if (user.isEmpty() || tweet.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Retweet retweet = retweetService.retweet(user.get(), tweet.get());
        return ResponseEntity.ok(retweet);
    }

    // DELETE /retweet → retweet sil
    @DeleteMapping
    public ResponseEntity<Void> deleteRetweet(@RequestParam Long userId, @RequestParam Long tweetId) {
        Optional<User> user = userService.findById(userId);
        Optional<Tweet> tweet = tweetService.getTweetById(tweetId);

        if (user.isEmpty() || tweet.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        retweetService.deleteRetweet(user.get(), tweet.get());
        return ResponseEntity.noContent().build();
    }
}
