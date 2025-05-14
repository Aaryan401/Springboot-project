package com.example.demo_may.Service.UserService;


import com.example.demo_may.Entity.User;
import com.example.demo_may.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface{

    @Autowired
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

}
