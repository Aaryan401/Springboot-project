package com.Example.Employee_Management.Service;

import com.Example.Employee_Management.Entity.User;
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
    public List<User> getAllUser();
    public User updateUser(Long userId, User user);
    public String deleteUser(Long userId);
    public String deleteUserByEmail(String email);
    public List<User> findUserByAgeGreater(int age);
    public List<User> findUserByAgeLesser(int age);
    public List<User> findUserByAgeBetween(int age1, int age2);
    public String updateUserByEmail(Long userId, String email);
    public String updateUserByNumber(Long userId, String number);
}
