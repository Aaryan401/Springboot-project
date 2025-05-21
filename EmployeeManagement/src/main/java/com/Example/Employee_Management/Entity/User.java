package com.Example.Employee_Management.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Employees")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(name="user_email",length = 100, unique = true, nullable = false)
    private String email;

    @Column(length = 80, nullable = false)
    private String password;

    @Column(length=13, unique = true, nullable = false)
    private String number;
}
