package com.example.twiiterapi.repository;

import com.example.twiiterapi.model.Retweet;
import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {

    Optional<Retweet> findByUserAndTweet(User user, Tweet tweet);
}
