package com.example.twiiterapi.service.impl;

import com.example.twiiterapi.model.Comment;
import com.example.twiiterapi.model.Tweet;

import java.util.List;
import java.util.Optional;

public interface CommentServiceImpl {
    Comment createComment(Comment comment);
    Optional<Comment> getCommentById(Long id);
    Comment updateComment(Comment comment, String content);
    void deleteComment(Comment comment);
    List<Comment> getCommentsByTweet(Tweet tweet);
}
