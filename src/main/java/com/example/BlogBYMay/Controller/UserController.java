package com.example.BlogBYMay.Controller;

import com.example.BlogBYMay.Entity.Profile;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Service.UserServiceImpl;
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
}
