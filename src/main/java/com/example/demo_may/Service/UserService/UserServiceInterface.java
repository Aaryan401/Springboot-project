package com.example.demo_may.Service.UserService;

import com.example.demo_may.Entity.User;

import java.util.Optional;

public interface UserServiceInterface {
    public String saveUser(User user);
    public String updateUserDetails(Long id, User user);
    public String deleteUser(Long id);
    public Optional<User> getUserById(Long id);
}
