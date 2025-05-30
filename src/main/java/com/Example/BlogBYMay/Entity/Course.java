package com.example.BlogBYMay.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "course")
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime CreatedDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate(){
        this.CreatedDate=LocalDateTime.now();
        this.updatedDate=LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedDate=LocalDateTime.now();
    }

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private Set<User> users=new HashSet<>();

}
