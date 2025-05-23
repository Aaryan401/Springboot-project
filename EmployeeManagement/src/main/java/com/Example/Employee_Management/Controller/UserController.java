package com.Example.Employee_Management.Controller;


import com.Example.Employee_Management.Entity.User;
import com.Example.Employee_Management.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<String> registerUser(@RequestBody User user){
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
    public ResponseEntity<User> getUserByFirstName(@RequestParam(name="firstName") String firstName){
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

    @GetMapping("getByName/{FirstName}/{LastName}") //Like this we can give two value in pathvariabe
    public ResponseEntity<User> getUserByName(@PathVariable(name="FirstName") String firstName, @PathVariable(name="LastName") String lastName){
        logger.info("User with name" + firstName +" "+ lastName + "is going to be fetched");
        User user = userService.getUserByFirstNameAndLastName(firstName,lastName);
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("getByFirstNameAndEmail")
    public ResponseEntity<User> getUserByFirstNameAndEmail(@RequestParam(name="FirstName") String firstName,
                                                           @RequestParam(name="Email") String email){
        logger.info("User with first name " + firstName + " and email " + email + " is going to be fetched");
        User user=userService.getUserByFirstNameAndEmail(firstName,email);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("getByLastNameAndEmail")
    public ResponseEntity<User> getByLastNameAndEmail(@RequestParam(name="LastName") String lastName,
                                                         @RequestParam(name="Email") String email){
        logger.info("User with last name " +lastName + " and email " + email + " is going to be fetched");
        User user=userService.getUserByLastNameAndEmail(lastName,email);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<Page<User>> getAllUser(
            @RequestParam(name="page", defaultValue="0") int pageNumber,
            @RequestParam(name="size", defaultValue ="4") int pagesize){
        logger.info("User's data is going to be fetched");
        Page<User> user=userService.getAllUser(pageNumber,pagesize);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(name="id") Long userId, @RequestBody User user){
        logger.info("User with ID" + userId + "is going to be updated");
        User user1=userService.updateUser(userId,user);
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name="id") Long userId){
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

    @GetMapping("ageGreater/{age}/{pageNo}/{pageSize}")
    public ResponseEntity<Page<User>> getUsersByAge(@PathVariable(name="age") int age, @PathVariable(name="pageNo") int pageNo, @PathVariable(name="pageSize") int pageSize){
        logger.info("User's data with age is going to be fetched");
        Page<User> user=userService.findUserByAgeGreater(age,pageNo ,pageSize );
        return new ResponseEntity<>(user , HttpStatus.OK);
    }

    @GetMapping("ageLess/{age}/{pageNo}/{pageSize}")
    public ResponseEntity<Page<User>> getUsersByAgeLess(@PathVariable(name="age") int age, @RequestParam(name="pageNo", defaultValue="0") int pageNo, @RequestParam(name="pageSize", defaultValue="4") int pageSize){
        logger.info("User data with age less than {} is going to be fetched", age);
        Page<User> user=userService.findUserByAgeLesser(age,pageNo,pageSize);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("ageBetween/{age1}/{age2}/{pageNo}/{pageSize}")
    public ResponseEntity<Page<User>> getUsersByAgeBetween(@PathVariable(name="age1") int age1, @PathVariable(name="age2") int age2, @RequestParam(name="pageNo", defaultValue="0") int pageNo, @RequestParam(name="pageSize", defaultValue="4") int pageSize){
        logger.info("User data with age between {} and {} is going to be fetched", age1, age2);
        Page<User> user=userService.findUserByAgeBetween(age1,age2,pageNo,pageSize);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("updateUserEmail/{id}")
    public ResponseEntity<String> updateUserByEmail(@PathVariable(name="id") Long userId,@RequestBody User user){
        logger.info("User having ID {} is going to be updated", userId);
        String response=userService.updateUserByEmail(userId,user.getEmail());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("updateUserNumber/{id}")
    public ResponseEntity<String> updateUserByNumber(@PathVariable(name="id") Long userId,@RequestBody User user){
        logger.info("User having ID {} is going to be updated", userId);
        String response=userService.updateUserByNumber(userId,user.getNumber());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
