package com.example.demo_may.Controller;

import com.example.demo_may.Entity.User;
import com.example.demo_may.Service.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController     //@RestController is specialized version of Controller annotation that is used to create restful webservice.
//Where the method return data directly
// It is combination of two notation Controller and RequestBody.
// If we use @Controller it required @ResponseBody at all handlerMethod to return data directly.
public class DemoController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("userId/{id}")  // These methods are called as Handler Methods
    public Optional<User> getUserById(@PathVariable Long id){   //@PathVariable is use to extract unique value directly from the URL path
        Optional<User> userById = userService.getUserById(id);
        return userById;
    }

    @PostMapping("register")
        public ResponseEntity<String> createUser(@RequestBody User user){       //@RequestBody is used to convert Json data into Java object
            String response = userService.saveUser(user);
            return new ResponseEntity<>("User registered successfully.",HttpStatus.CREATED);
    }

    @PutMapping("update/{userId}")      //If URL has different endpoint then function parameter then we can give it in @PathVariable(name="")
    public ResponseEntity<String> updateUser(@PathVariable(name="userId") Long id, @RequestBody User user){
        String response =userService.updateUserDetails(id,user);
        return new ResponseEntity<>(response,HttpStatus.OK);
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

    @GetMapping("search")
    public List<User> getByAge(@RequestParam(name="age", required=false, defaultValue = "23") int age) {
        return userService.findUserByAge(age);
        }

    @GetMapping("Searching")
    public String getDetails(   //@RequestParam is use to extract the query from the URL
                                    //We can make a query parameter Optional with required = false and set a default value
                                    //We can use this @RequestParam default value in using filter in shopping site's etc.
       @RequestParam(name="firstName") String name,
       @RequestParam(name="age", required = false, defaultValue  ="25") int age
                ){
        return "Searching for user name: "+name+ "age: "+age;
        }
    }

