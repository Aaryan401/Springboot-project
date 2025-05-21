package com.Example.Employee_Management.Controller;


import com.Example.Employee_Management.Entity.User;
import com.Example.Employee_Management.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private final UserServiceImpl userService;

    private final Logger logger= LoggerFactory.getLogger("UserController");


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(User user){
        logger.info("User is going to register");
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
        logger.info("User with first name" + firstName + "is going to be fetched");
        User user=userService.getUserByFirstName(firstName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("getByLastName/{lastName}")
    public ResponseEntity<User> getUserByLastName(@PathVariable String lastName){
        logger.info("User with last name" + lastName + "is going to be fetched");
        User user=userService.getUserByLastName(lastName);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("getByEmail/{mail}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(name="mail") String email){
        logger.info("User with email" + email + "is going to be fetched");
        User user=userService.getUserByEmail(email);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("getByNumber")
    public ResponseEntity<User> getUserByNumber(@RequestParam(name="number") String number){
        logger.info("User with number" + number + "is going to be fetched");
        User user=userService.getUserByNumber(number);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("getByName/{FirstName}/{LastName}") //Like this we cn give two value in pathvariabe
    public ResponseEntity<User> getUserByName(@PathVariable(name="FirstName") String firstName, @PathVariable(name="LastName") String lastName){
        logger.info("User with name" + firstName +" "+ lastName + "is going to be fetched");
        User user = userService.getUserByFirstNameAndLastName(firstName,lastName);
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("getByFirstNameAndEmail")
    public ResponseEntity<User> getUserByFirstNameAndEmail(@RequestParam(name="FirstName") String firstName,
                                                           @RequestParam(name="Email") String email){
        logger.info("User with first name" + firstName + "and email" + email + "is going to be fetched");
        User user=userService.getUserByFirstNameAndEmail(firstName,email);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("getByLastNameAndEmail")
    public ResponseEntity<User> getByLastNameAndEmail(@RequestParam(name="LastName") String lastName,
                                                         @RequestParam(name="Email") String email){
        logger.info("User with last name" +lastName + "and email" + email + "is going to be fetched");
        User user=userService.getUserByLastNameAndEmail(lastName,email);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<User>> getAllUser(){
        logger.info("User's data is going to be fetched");
        List<User> user=userService.getAllUser();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(name="id") User user, Long userId){
        logger.info("User with ID" + userId + "is going to be updated");
        User user1=userService.updateUser(user,userId);
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

    @DeleteMapping("deleteUser")
    public ResponseEntity<String> deleteUser(Long userId){
        logger.info("User having ID {} is going to be deleted", userId);
        String response=userService.deleteUser(userId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("deleteByEmail/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email){
        logger.info("User having email {} is going to be deleted", email);
        String response=userService.deleteUserByEmail(email);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
