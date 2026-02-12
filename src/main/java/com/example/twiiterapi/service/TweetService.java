package com.example.twiiterapi.service;

import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.model.User;

import java.util.List;
import java.util.Optional;

public interface TweetService {  // <- DİKKAT! class değil interface
    Tweet createTweet(Tweet tweet);
    List<Tweet> getTweetsByUser(User user);
    Optional<Tweet> getTweetById(Long id);
    Tweet updateTweet(Long id, String content);
    void deleteTweet(Long id);

}