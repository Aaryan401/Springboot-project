package com.example.demo_may.Entity;

import jakarta.persistence.*;

//This is a Hibernate notation that is used to create the table into the database
@Table(name="users")

//This Entity is used to represent the table in the DB.
@Entity
public class User {
    @Id             //This is also a Hibernate notation used to generate primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This is used to create id unique
    private Long id;
    private String firstName;   //Always write entity into camelCase
    private String lastName;
    private String email;
    private int age;

    public User(Long id, String firstName, String lastName, String email, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
