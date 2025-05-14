package com.example.demo_may.Repository;

import com.example.demo_may.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
                                                    //Entity(Class name), Id datatype
    //We are not going to create any kind of abstract method here.
}
