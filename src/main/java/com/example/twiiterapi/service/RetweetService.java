package com.example.twiiterapi.service;

import com.example.twiiterapi.model.Retweet;
import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.model.User;
import com.example.twiiterapi.repository.RetweetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
public class RetweetService {

    private final RetweetRepository retweetRepository;

    public RetweetService(RetweetRepository retweetRepository) {
        this.retweetRepository = retweetRepository;
    }

    // Retweet at
    public Retweet retweet(User user, Tweet tweet) {
        Optional<Retweet> existingRetweet = retweetRepository.findByUserAndTweet(user, tweet);
        if (existingRetweet.isPresent()) {
            return existingRetweet.get();
        }
        Retweet retweet = Retweet.builder()
                .user(user)
                .tweet(tweet)
                .createdAt(Instant.now())
                .build();
        return retweetRepository.save(retweet);
    }

    // Retweet sil
    public void deleteRetweet(User user, Tweet tweet) {
        Optional<Retweet> existingRetweet = retweetRepository.findByUserAndTweet(user, tweet);
        existingRetweet.ifPresent(retweetRepository::delete);
    }
}
