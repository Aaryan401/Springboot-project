package com.example.BlogBYMay.Controller;

import com.example.BlogBYMay.Entity.Profile;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Model.AllProfileDetailsDTO;
import com.example.BlogBYMay.Model.ProfileDto;
import com.example.BlogBYMay.Model.UpdateProfileDto;
import com.example.BlogBYMay.Service.User.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    public final UserServiceImpl userService;
    
    @PostMapping("registerUser")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        String response=userService.saveUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("registerProfile/{id}")
    public ResponseEntity<String> saveProfile(@PathVariable(name="id") Long userId,@RequestBody Profile profile){
        String response=userService.saveProfile(userId,profile);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("getSomeDetails/{id}")
    public ResponseEntity<ProfileDto> getSomeProfileDetails(@PathVariable(name="id") Long profileId){
        ProfileDto profileDto=userService.getSomeProfileDetails(profileId);
        return new ResponseEntity<>(profileDto,HttpStatus.OK);
    }

    @GetMapping("getAllDetails/{id}")
    public ResponseEntity<AllProfileDetailsDTO> getAllProfileDetails(@PathVariable(name="id") Long profileId){
        AllProfileDetailsDTO allProfileDto=userService.getAllProfileDetails(profileId);
        return new ResponseEntity<>(allProfileDto,HttpStatus.OK);
    }

     @PutMapping("update/{userId}")
     public ResponseEntity<String> updateProfileWithUser(@PathVariable(name="userId") Long userId, @RequestBody UpdateProfileDto updateProfileDto){
       String response=userService.updateProfilewithUser(userId,updateProfileDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
     }


    @PutMapping("update/{profileId}")
    public ResponseEntity<Profile> updateProfile(@PathVariable(name="profileId") Long profileId,@RequestBody Profile profile){
        Profile updatedProfile=userService.updateProfile(profileId, profile);
        return new ResponseEntity<>(updatedProfile,HttpStatus.OK);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable(name="id") Long profileId){
        String response=userService.deleteProfile(profileId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
