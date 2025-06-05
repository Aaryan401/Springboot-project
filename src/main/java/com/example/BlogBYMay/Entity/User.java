package com.example.BlogBYMay.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    @Column(nullable = false,unique = true,updatable = false)
    private Long userId;

    @Column(nullable = false)
    @NotBlank(message = "First name must not be blank")
    @Pattern(regexp = "^[A-Za-z]+$",message = "Only letters are allowed")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @Column(nullable = false,unique = true)
    @Email(message = "Email must be valid")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 15, message = "Password must be at least 6 characters")
    @Pattern(regexp= "^[A-Za-z0-9]+$", message = "Only Letters and Digits are allowed")
    private String password;

    @Column(nullable = false,updatable = false)
    private LocalDateTime registerDate;

    @PrePersist
    protected void onCreate(){
        this.registerDate = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user", cascade= CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name= "student_course",
            joinColumns =@JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
    private Set<Course> courses=new HashSet<>();

}
