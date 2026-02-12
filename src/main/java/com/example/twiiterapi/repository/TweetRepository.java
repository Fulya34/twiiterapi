package com.example.twiiterapi.repository;

import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    // User objesine göre tweetleri getir
    List<Tweet> findByUser(User user);
}