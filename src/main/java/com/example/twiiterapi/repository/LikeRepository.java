package com.example.twiiterapi.repository;

import com.example.twiiterapi.model.Like;
import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndTweet(User user, Tweet tweet);

}
