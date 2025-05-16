package com.example.demo_may.Service.UserService;


import com.example.demo_may.Entity.User;
import com.example.demo_may.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Service is a special type of @Controller notation that is use to create the service class
//This contains Business logic i.e. all the task's that we have to perform those are written in service class
//Step 1: We have to print the list of user from the db.

public class UserServiceImpl implements UserServiceInterface{

    @Autowired      //@Autowired is used to inject the dependency
    private UserRepository userRepository;

    @Override
    public String saveUser(User user){
        User save=userRepository.save(user);
        return "user has been registered";
}
    @Override
    public String updateUserDetails(Long id, User user){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAge(user.getAge());
            userRepository.save(existingUser);
        }
        return"Your Details has been updated";
    }

    public List<User> getAllUser(){
        List<User> all= userRepository.findAll();
        return all;
    }

    @Override
    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "User has been deleted";
    }

    @Override

    public Optional<User> getUserById(Long id) {
        Optional<User> all=userRepository.findById(id);
        return all;
    }
    public List<User> findUserByAge(int age) {
        List<User> all=userRepository.findAllByAge(age);
        return all;
        }
}
