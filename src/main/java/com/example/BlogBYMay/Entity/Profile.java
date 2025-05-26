package com.example.BlogBYMay.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users_profile")
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    private String city;
    private String state;
    private String address;
    private String age;
    private String mobile;

    @Temporal(TemporalType.DATE)
    private LocalDateTime profileCreatedDate;

    @Temporal(TemporalType.DATE)
    private LocalDateTime profileUpdatedDate;

    @PrePersist
    protected void onCreate() {
        this.profileCreatedDate = LocalDateTime.now();
    }

    @PostPersist
    protected void onUpdate() {
        this.profileUpdatedDate = LocalDateTime.now();
    }

    @OneToOne
    @JoinColumn(name="user_id",unique = true)
    private User user;

}
