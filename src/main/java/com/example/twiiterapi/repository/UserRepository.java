package com.example.twiiterapi.repository;

import com.example.twiiterapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Kullanıcıyı email ile bulmak için
    Optional<User> findByEmail(String email);

    // Kullanıcı adı ile bulmak için (isteğe bağlı)
    Optional<User> findByUsername(String username);

}
