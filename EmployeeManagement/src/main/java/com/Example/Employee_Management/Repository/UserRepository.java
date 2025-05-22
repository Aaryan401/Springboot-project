package com.Example.Employee_Management.Repository;


import com.Example.Employee_Management.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findUserByFirstName(String firstName);

    public Optional<User> findByLastName(String lastName);

    public Optional<User> findUserByEmail(String email);

    public Optional<User> findUserByNumber(String number);

    public Optional<User> findUserByFirstNameAndEmail(String firstName, String email);

    public Optional<User> findUserByLastNameAndEmail(String lastName,String email);

    public Optional<User> findUserByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT u FROM User u WHERE u.age > :userAge ORDER BY u.age DESC")
    public List<User> findUserByAgeGreater(@Param("userAge") int age);


    @Query(value = "SELECT age FROM employees WHERE age < :userAge ORDER BY age ASC", nativeQuery = true )
    public List<User> findUserByAgeLesser(@Param("userAge") int age);

    @Query("SELECT u FROM User u WHERE u.age BETWEEN :userAge1 AND :userAge2 ORDER BY u.age ASC")
    public List<User> findUserByAgeBetween(@Param("userAge1") int age1,@Param("userAge2") int age2);

    @Modifying
    @Transactional
    @Query("Update User u SET u.email= :newEmail where u.userId= :id")
    public  int updateUserByEmail(@Param("id") Long userId, @Param("newEmail") String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.number = :newNumber WHERE u.id = :id")
    public int updateUserByNumber(@Param("id") Long userId, @Param("newNumber") String number);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.email = :userEmail")
    public int deleteUserByEmail(@Param("userEmail") String email);

}