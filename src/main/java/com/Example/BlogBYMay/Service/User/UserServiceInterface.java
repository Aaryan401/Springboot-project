package com.example.BlogBYMay.Service.User;

import com.example.BlogBYMay.Entity.Profile;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Model.AllProfileDetailsDTO;
import com.example.BlogBYMay.Model.ProfileDto;
import com.example.BlogBYMay.Model.UpdateProfileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserServiceInterface {
    public String saveUser(User user);
    public String saveProfile(Long userId,Profile profile, MultipartFile file) throws IOException;
    public ProfileDto getSomeProfileDetails(Long profileId);
    public AllProfileDetailsDTO getAllProfileDetails(Long profileId) throws IOException;
//  public Profile updateProfileWithUser(Long profileId,Long userId, Profile profile);
    public Profile updateProfile(Long profileId, Profile profile);
    public String deleteProfile(Long profileId);
    public String updateProfilewithUser(Long userId, UpdateProfileDto updateProfileDto);
}

