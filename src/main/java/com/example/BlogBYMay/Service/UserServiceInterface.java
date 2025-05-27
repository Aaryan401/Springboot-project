package com.example.BlogBYMay.Service;

import com.example.BlogBYMay.Entity.Profile;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Model.AllProfileDetailsDTO;
import com.example.BlogBYMay.Model.ProfileDto;

public interface UserServiceInterface {
    public String saveUser(User user);
    public String saveProfile(Long userId,Profile profile);
    public ProfileDto getSomeProfileDetails(Long profileId);
    public AllProfileDetailsDTO getAllProfileDetails(Long profileId);
}
