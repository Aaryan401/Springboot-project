package com.example.BlogBYMay.Service;

import com.example.BlogBYMay.Entity.Profile;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Repository.ProfileRepository;
import com.example.BlogBYMay.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserServiceInterface{

    @Autowired
    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    @Override
    public String saveUser(User user) {
        userRepository.save(user);
        return "Your account has been created";
    }

    @Override
    public String saveProfile(Long userId,Profile profile) {
       User foundUser= userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
       profile.setUser(foundUser);
       profileRepository.save(profile);
       return "Your profile has been saved";
    }


}
