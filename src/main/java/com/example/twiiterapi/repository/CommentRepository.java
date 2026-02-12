package com.example.twiiterapi.repository;

import com.example.twiiterapi.model.Comment;
import com.example.twiiterapi.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Bir tweete ait tüm yorumlar
    List<Comment> findAllByTweetOrderByCreatedAtAsc(Tweet tweet);

}
