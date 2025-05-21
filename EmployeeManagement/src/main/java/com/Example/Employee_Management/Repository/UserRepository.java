package com.Example.Employee_Management.Repository;


import com.Example.Employee_Management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findUserByFirstName(String firstName);

    public Optional<User> findByLastName(String lastName);

    public Optional<User> findUserByEmail(String email);

    public Optional<User> findUserByNumber(String number);

    public Optional<User> findUserByFirstNameAndEmail(String firstName, String Email);

    public Optional<User> findUserByLastNameAndEmail(String lastName,String email);

    public Optional<User> findUserByFirstNameAndLastName(String firstName,String lastName);

    public String deleteUserByEmail(String email);

}