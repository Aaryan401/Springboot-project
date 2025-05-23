package com.Example.Employee_Management.Service;

import com.Example.Employee_Management.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserServiceIterface {
    public String saveUser(User user);
    public User getUserById(Long userId);
    public User getUserByFirstName(String firstName);
    public User getUserByLastName(String lastName);
    public User getUserByNumber(String number);
    public User getUserByEmail(String email);
    public User getUserByFirstNameAndLastName(String firstName, String lastName);
    public User getUserByFirstNameAndEmail(String firstName, String email);
    public User getUserByLastNameAndEmail(String lastName, String email);
    public Page<User> getAllUser(int pageNo, int pageSize);
    public User updateUser(Long userId, User user);
    public String deleteUser(Long userId);
    public String deleteUserByEmail(String email);
    public Page<User> findUserByAgeGreater(int age, int pageNo, int pageSize);
    public Page<User> findUserByAgeLesser(int age, int pageNo, int pageSize);
    public Page<User> findUserByAgeBetween(int age1, int age2, int pageNo, int pageSize);
    public String updateUserByEmail(Long userId, String email);
    public String updateUserByNumber(Long userId, String number);
}
