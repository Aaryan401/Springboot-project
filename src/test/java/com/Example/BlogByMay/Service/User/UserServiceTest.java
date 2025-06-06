package com.Example.BlogByMay.Service.User;

import com.example.BlogBYMay.Entity.Profile;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Model.ProfileDto;
import com.example.BlogBYMay.Repository.ProfileRepository;
import com.example.BlogBYMay.Repository.UserRepository;
import com.example.BlogBYMay.Service.User.UserServiceImpl;
import com.example.BlogBYMay.Service.Utility.EmailService;
import com.example.BlogBYMay.Service.Utility.ImageService;
import jakarta.mail.MessagingException;
import org.assertj.core.api.AbstractStringAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)    // it is good practice to use otherwise test unit will not work.
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private Profile profile;
    private Long profileId;
    private Long userId;
    private MultipartFile file;

    @BeforeEach
    public void setUp(){
        //        MockitoAnnotations.openMocks(this);  //either we can use this in setUp() or we can use this extension on class level.
        user = User.builder()
                .userId(1L)
                .firstName("Aaryan")
                .lastName("Prashar")
                .email("aaryanuttam54321@gmail.com")
                .password("123456789")
                .build();

        profile =Profile.builder()
                .profileId(profileId)
                .age(20)
                .address("Banglore")
                .city("Bangalore")
                .state("Karnataka")
                .pincode("560048")
                .mobile("8409141589")
                .image("image.jpg")
                .user(user)
                .build();
    }

    @AfterEach
    public void tearDown(){
        user=null;
    }

    @Test
    public void testSaveUser() throws MessagingException {
        //mock userRepository and pretend that "when" you save "any" User.class then return user object;
        when(userRepository.save(any(User.class))).thenReturn(user);

        //mock emailService.registerEmail(anyString()) when call with any String, return dummy email body.
        when(emailService.registerEmail(anyString())).thenReturn("dummy email text body");

        //mock emailService.sendStanderEmail: when call with any String do nothing;
        doNothing().when(emailService).sendStandardEmail(anyString(),anyString(),anyString());

        //call the service and get the exact response(result) from the service layer.
        String response = userService.saveUser(user);

        // verify the response
        //match the result(response) is matching with expected Result or not.
        assertThat(response).isEqualTo("Your account has been created");

        //verify that userRepository.save(): was called exactly once with user object.
        verify(userRepository).save(user);
        System.out.println("Test passed");
    }



    @Test
    public void testSaveProfile() throws IOException{
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);
        when(imageService.saveImage(file)).thenReturn("image.jpg");
        String response = userService.saveProfile(userId, profile, file);
        assertThat(response).isEqualTo("Your profile has been saved");
        verify(profileRepository).save(profile);
        verify(imageService).saveImage(file);
        verify(userRepository).findById(userId);
        System.out.println("Test passed");
    }

    @Test
    public void testGetSomeProfileDetails_successful(){
        when(profileRepository.findById(profileId)).thenReturn(Optional.of(profile));
        ProfileDto someProfileDetails = userService.getSomeProfileDetails(profileId);
        assertThat(someProfileDetails).isNotNull();
        assertThat(someProfileDetails.getFullName()).isEqualTo("Aaryan Prashar");
        assertThat(someProfileDetails.getEmail()).isEqualTo("aaryanuttam54321@gmail.com");
        assertThat(someProfileDetails.getCity()).isEqualTo("Bangalore");
        assertThat(someProfileDetails.getState()).isEqualTo("Karnataka");
        assertThat(someProfileDetails.getPincode()).isEqualTo("560048");

        System.out.println("Test passed");
    }

    @Test
    public void testGetSomeProfileDetails_ProfileNotFound(){
        when(profileRepository.findById(profileId)).thenReturn(Optional.empty());
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> userService.getSomeProfileDetails(profileId));
        assertThat(runtimeException.getMessage()).isEqualTo("Profile not found");
        System.out.println("Test passed");
    }

    @Test
    public void testUpdateProfile(){
        when(profileRepository.findById(profileId)).thenReturn(Optional.of(profile));
        profile.setAge(24);
        profile.setAddress("Garudacharpalya, Mahadevapura");
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        Profile updatedProfile = userService.updateProfile(profileId, profile);
        assertThat(updatedProfile.getAge()).isEqualTo(24);
        assertThat(updatedProfile.getAddress()).isEqualTo("Garudacharpalya, Mahadevapura");
        assertThat(updatedProfile.getCity()).isEqualTo("Bangalore");
        assertThat(updatedProfile.getState()).isEqualTo("Karnataka");
        assertThat(updatedProfile.getPincode()).isEqualTo("560048");
        assertThat(updatedProfile.getMobile()).isEqualTo("8409141589");
        verify(profileRepository).save(profile);
        System.out.println("Test passed");
    }

    @Test
    public void testdeleteProfile(){
        doNothing().when(profileRepository).deleteById(profileId);

        String response = userService.deleteProfile(profileId);
        assertThat(response).isEqualTo("Porfile " + profileId + " has been deleted");
        verify(profileRepository).deleteById(profileId);
        System.out.println("Test passed");
    }
}
