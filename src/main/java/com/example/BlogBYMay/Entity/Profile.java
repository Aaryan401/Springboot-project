package com.example.BlogBYMay.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(nullable = false,unique = true,updatable = false)
    private Long profileId;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false,length = 3)
    private int age;

    @Column(nullable = false,unique = true,length = 13)
    private String mobile;

    @Column(nullable = false,length = 6)
    private String pincode;

    @Column(nullable = false,updatable = false)
    private LocalDateTime profileCreatedDate;

    @PrePersist
    protected void onCreate() {
      this.profileCreatedDate=LocalDateTime.now();
      this.profileUpdatedDate = LocalDateTime.now();
    }

    @Column(nullable = false)
    private LocalDateTime profileUpdatedDate;

    @PreUpdate
    protected void onUpdate() {
        this.profileUpdatedDate = LocalDateTime.now();
    }

    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",unique = true)
    @JsonIgnore
    private User user;

}
