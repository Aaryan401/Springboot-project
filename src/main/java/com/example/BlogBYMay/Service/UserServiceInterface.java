package com.example.BlogBYMay.Service;

import com.example.BlogBYMay.Entity.Profile;
import com.example.BlogBYMay.Entity.User;

public interface UserServiceInterface {
    public String saveUser(User user);
    public String saveProfile(Long userId,Profile profile);
}
