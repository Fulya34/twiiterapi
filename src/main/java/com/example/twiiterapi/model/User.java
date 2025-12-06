package com.example.twiiterapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Kullanıcı adı boş olamaz")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçerli bir email giriniz")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    @Column(nullable = false)
    private String password;

    private OffsetDateTime createdAt = OffsetDateTime.now(); // <-- EKLENDİ
}