package com.example.BlogBYMay.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "City cannot be null or blank")
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "State cannot be null or blank")
    private String state;

    @Column(nullable = false)
    @NotBlank(message = "Address cannot be null or blank")
    private String address;

    @Column(nullable = false,length = 3)
    @NotBlank(message = "Age cannot be null or blank")
    @Min(value = 18,message = "Age must be greater than 18")
    private int age;

    @Column(nullable = false,unique = true,length = 13)
    @NotBlank(message = "Mobile number cannot be null or blank")
    @Size(min = 10,max = 13,message = "Mobile number must be 10 digits")
    private String mobile;

    @Column(nullable = false,length = 6)
    @NotBlank(message = "Pincode cannot be null or blank")
    @Size(min = 6,max = 6,message = "Pincode must be 6 digits")
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
