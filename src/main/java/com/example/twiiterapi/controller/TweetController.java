package com.example.twiiterapi.controller;

import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.model.User;
import com.example.twiiterapi.service.TweetService;
import com.example.twiiterapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;
    private final UserService userService;

    public TweetController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    // POST /tweet → tweet oluştur
    @PostMapping
    public ResponseEntity<Tweet> createTweet(@RequestParam Long userId, @RequestBody Tweet tweet) {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        tweet.setUser(user.get());
        Tweet savedTweet = tweetService.createTweet(tweet);
        return ResponseEntity.ok(savedTweet);
    }

    // GET /tweet/findByUserId?userId=1 → kullanıcının tüm tweetleri
    @GetMapping("/findByUserId")
    public ResponseEntity<List<Tweet>> getTweetsByUser(@RequestParam Long userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Tweet> tweets = tweetService.getTweetsByUser(user.get());
        return ResponseEntity.ok(tweets);
    }

    // PUT /tweet/{id} → tweet güncelle
    @PutMapping("/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable Long id, @RequestBody Tweet tweetDetails) {
        Optional<Tweet> existingTweet = tweetService.getTweetById(id);
        if (existingTweet.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Tweet updatedTweet = tweetService.updateTweet(existingTweet.get().getId(), tweetDetails.getContent());
        return ResponseEntity.ok(updatedTweet);
    }

    // DELETE /tweet/{id} → tweet sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id) {
        Optional<Tweet> existingTweet = tweetService.getTweetById(id);
        if (existingTweet.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tweetService.deleteTweet(existingTweet.get().getId());
        return ResponseEntity.noContent().build();
    }

}
