package com.example.BlogBYMay.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private LocalDateTime registerDate;

    @PrePersist
    protected void onCreate(){
        this.registerDate = LocalDateTime.now();
    }
}
