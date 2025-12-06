package com.example.twiiterapi.service.impl;

import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.model.User;
import com.example.twiiterapi.repository.TweetRepository;
import com.example.twiiterapi.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;

    @Override
    public Tweet createTweet(Tweet tweet) {
        tweet.setCreatedAt(Instant.now());
        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> getTweetsByUser(User user) {
        return tweetRepository.findByUser(user);
    }

    @Override
    public Optional<Tweet> getTweetById(Long id) {
        return tweetRepository.findById(id);
    }

    @Override
    public Tweet updateTweet(Long id, String content) {
        Tweet existing = tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet bulunamadı"));
        existing.setContent(content);
        existing.setUpdatedAt(Instant.now());
        return tweetRepository.save(existing);
    }

    @Override
    public void deleteTweet(Long id) {
        tweetRepository.deleteById(id);
    }
}
