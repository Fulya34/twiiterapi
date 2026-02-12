package com.example.twiiterapi.service;

import com.example.twiiterapi.model.Like;
import com.example.twiiterapi.model.Tweet;
import com.example.twiiterapi.model.User;
import com.example.twiiterapi.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    // Like at
    public Like likeTweet(User user, Tweet tweet) {
        // Eğer zaten like varsa, geri dön
        Optional<Like> existingLike = likeRepository.findByUserAndTweet(user, tweet);
        if (existingLike.isPresent()) {
            return existingLike.get();
        }
        Like like = Like.builder().user(user).tweet(tweet).build();
        return likeRepository.save(like);
    }

    // Like kaldır (dislike)
    public void dislikeTweet(User user, Tweet tweet) {
        Optional<Like> existingLike = likeRepository.findByUserAndTweet(user, tweet);
        existingLike.ifPresent(likeRepository::delete);
    }

    // Kullanıcının bir tweeti beğenip beğenmediğini kontrol et (isteğe bağlı)
    public boolean isLikedByUser(User user, Tweet tweet) {
        return likeRepository.findByUserAndTweet(user, tweet).isPresent();
    }

}
