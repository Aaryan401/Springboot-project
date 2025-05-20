package com.Example.Employee_Management.Controller;


import com.Example.Employee_Management.Entity.User;
import com.Example.Employee_Management.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    public final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(User user){
        String response=userService.saveUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId){
        User user=userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("getByFirstName")
    public ResponseEntity<User> getUserByFirstName(@RequestBody() String firstName){
        User user=userService.getUserByFirstName(firstName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
