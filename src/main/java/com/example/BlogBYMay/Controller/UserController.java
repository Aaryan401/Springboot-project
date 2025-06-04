package com.example.BlogBYMay.Controller;

import com.example.BlogBYMay.Entity.Profile;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Model.AllProfileDetailsDTO;
import com.example.BlogBYMay.Model.ProfileDto;
import com.example.BlogBYMay.Model.UpdateProfileDto;
import com.example.BlogBYMay.Service.User.UserServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    public final UserServiceImpl userService;
    
    @PostMapping("registerUser")
    public ResponseEntity<String> saveUser(@Valid @RequestBody User user) throws MessagingException {
        String response=userService.saveUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("registerProfile/{id}")
    public ResponseEntity<String> saveProfile(@PathVariable(name="id") Long userId,@Valid @RequestPart("profile") Profile profile,
                                              @RequestParam("file") MultipartFile file) throws IOException {
        String response=userService.saveProfile(userId,profile,file);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("getSomeDetails/{id}")
    public ResponseEntity<ProfileDto> getSomeProfileDetails(@PathVariable(name="id") Long profileId){
        ProfileDto profileDto=userService.getSomeProfileDetails(profileId);
        return new ResponseEntity<>(profileDto,HttpStatus.OK);
    }

    @GetMapping("getAllDetails/{id}")
    public ResponseEntity<AllProfileDetailsDTO> getAllProfileDetails(@PathVariable(name="id") Long profileId) throws IOException{
        AllProfileDetailsDTO allProfileDto=userService.getAllProfileDetails(profileId);
        return new ResponseEntity<>(allProfileDto,HttpStatus.OK);
    }

     @PutMapping("update/{userId}")
     public ResponseEntity<String> updateProfileWithUser(@PathVariable(name="userId") Long userId, @RequestBody UpdateProfileDto updateProfileDto){
       String response=userService.updateProfilewithUser(userId,updateProfileDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
     }


    @PutMapping("updateProfile/{profileId}")
    public ResponseEntity<Profile> updateProfile(@PathVariable(name="profileId") Long profileId,@Valid @RequestBody Profile profile){
        Profile updatedProfile=userService.updateProfile(profileId, profile);
        return new ResponseEntity<>(updatedProfile,HttpStatus.OK);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable(name="id") Long profileId){
        String response=userService.deleteProfile(profileId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
