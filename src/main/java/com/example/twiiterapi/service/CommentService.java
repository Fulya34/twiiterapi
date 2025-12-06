package com.example.twiiterapi.service;

import com.example.twiiterapi.model.Comment;
import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Yorum oluşturma
    public Comment createComment(Comment comment) {
        comment.setCreatedAt(Instant.now());
        return commentRepository.save(comment);
    }

    // Tweet'e ait yorumları listeleme
    public List<Comment> getCommentsByTweet(Tweet tweet) {
        return commentRepository.findAllByTweetOrderByCreatedAtAsc(tweet);
    }

    // Yorum bulma
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // Yorum güncelleme
    public Comment updateComment(Comment comment, String newContent) {
        comment.setContent(newContent);
        comment.setUpdatedAt(Instant.now());
        return commentRepository.save(comment);
    }

    // Yorum silme
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
