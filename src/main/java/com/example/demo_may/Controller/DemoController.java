package com.example.demo_may.Controller;

import com.example.demo_may.Entity.User;
import com.example.demo_may.Service.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("userid/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        Optional<User> userById = userService.getUserById(id);
        return userById;
    }

    @PostMapping("register")
        public String createUser(@RequestBody User user){
            String response = userService.saveUser(user);
            return response;
        }

    @PutMapping("update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user){
        String response =userService.updateUserDetails(id,user);
        return response;
    }
    @GetMapping("get-all-user")
    public List<User> getAllUser(){
        List<User> all= userService.getAllUser();
        return all;
    }

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id){
        String response=userService.deleteUser(id);
        return response;
    }

}
