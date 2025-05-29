package com.example.BlogBYMay.Service.User;

import com.example.BlogBYMay.Entity.Profile;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Model.AllProfileDetailsDTO;
import com.example.BlogBYMay.Model.ProfileDto;
import com.example.BlogBYMay.Model.UpdateProfileDto;
import com.example.BlogBYMay.Repository.ProfileRepository;
import com.example.BlogBYMay.Repository.UserRepository;
import com.example.BlogBYMay.Service.User.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    @Override
    public String saveUser(User user) {
        userRepository.save(user);
        return "Your account has been created";
    }

    @Override
    public String saveProfile(Long userId, Profile profile) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        profile.setUser(foundUser);
        profileRepository.save(profile);
        return "Your profile has been saved";
    }

    //Without Using Builder
    @Override
    public ProfileDto getSomeProfileDetails(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("Profile not found"));

        ProfileDto profileDto = new ProfileDto();
        profileDto.setFullName(profile.getUser().getFirstName() + " " + profile.getUser().getLastName());
        profileDto.setEmail(profile.getUser().getEmail());
        profileDto.setCity(profile.getCity());
        profileDto.setState(profile.getState());
        profileDto.setPincode(profile.getPincode());

        return profileDto;

    }

    //Uses of Builder
    @Override
    public AllProfileDetailsDTO getAllProfileDetails(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("Profile not found"));

        AllProfileDetailsDTO allProfileDto = AllProfileDetailsDTO.builder()
                .profileId(profile.getProfileId())
                .fullName(profile.getUser().getFirstName() + " " + profile.getUser().getLastName())
                .age(profile.getAge())
                .email(profile.getUser().getEmail())
                .mobile(profile.getMobile())
                .address(profile.getAddress())
                .city(profile.getCity())
                .state(profile.getState())
                .pincode(profile.getPincode())
                .registerDate(profile.getUser().getRegisterDate())
                .profileCreatedDate(profile.getProfileCreatedDate())
                .profileUpdatedDate(profile.getProfileUpdatedDate())
                .build();
        return allProfileDto;
    }

    @Override
    public Profile updateProfile(Long profileId, Profile profile) {
        Profile existingProfile = profileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("Profile not found"));
        existingProfile.setAge(profile.getAge());
        existingProfile.setMobile(profile.getMobile());
        existingProfile.setAddress(profile.getAddress());
        existingProfile.setCity(profile.getCity());
        existingProfile.setState(profile.getState());
        existingProfile.setPincode(profile.getPincode());
        return profileRepository.save(existingProfile);
    }

    @Override
    public String updateProfilewithUser(Long userId, UpdateProfileDto updateProfileDto) {
        Profile profile = profileRepository.findByUserUserId(userId).orElseThrow(() -> new RuntimeException("Profile not found"));

        String[] str=updateProfileDto.getFullName().split(" ");
        profile.getUser().setFirstName(str[0]);
        profile.getUser().setLastName(str[1]);
        profile.getUser().setEmail(updateProfileDto.getEmail());
        profile.getUser().setPassword(updateProfileDto.getPassword());
        profile.setAge(updateProfileDto.getAge());
        profile.setMobile(updateProfileDto.getMobile());
        profile.setAddress(updateProfileDto.getAddress());
        profile.setCity(updateProfileDto.getCity());
        profile.setState(updateProfileDto.getState());
        profile.setPincode(updateProfileDto.getPincode());
        profileRepository.save(profile);
        return "Your Profile has been updated";

    }

    @Override
    public String deleteProfile(Long profileId) {
        profileRepository.deleteById(profileId);
        return "Porfile " + profileId + " has been deleted";
    }
}