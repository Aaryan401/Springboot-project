package com.Example.Employee_Management.Service;


import com.Example.Employee_Management.Entity.User;
import com.Example.Employee_Management.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.Example.Employee_Management.Exception.CustomException.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserServiceIterface {

    @Autowired
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger("UserServiceImpl.class");
    @Override
    public String saveUser(User user) {
        if (userRepository.findUserByFirstNameAndLastName(user.getFirstName(), user.getLastName()).isPresent()) {
            logger.error("User with name" +user.getFirstName(), user.getLastName() +"already exists");
            throw new UserAlreadyExistsException("User with name " + user.getFirstName() + " " + user.getLastName() + " already exists");
        }
        userRepository.findUserByEmail(user.getEmail()).ifPresent(
                user1 -> {
                    logger.error("User with email {} already exists",user1.getEmail() );//Here {} tells that there's a data will come
                    throw new UserAlreadyExistByThisEmailException("User with email" + user1.getEmail() + "already exists");
                }
        );
        userRepository.findUserByNumber(user.getNumber()).ifPresent(
                user1 -> {
                    logger.error("User with number" + user1.getNumber() + "already exists");
                    throw new UserAlreadyExistByThisNumberException("User with number" + user1.getNumber() + "already exists");
                }
        );
        User user1 = userRepository.save(user);
        logger.info("User registered successfully");
        return ("User has been registered");
    }

    @Override
    public User getUserById(Long userId) {
        logger.info("User with ID" + userId + "is going to be fetched");
        User user = userRepository.findById(userId).orElseThrow(() ->{ logger.warn("User with Id "+userId+" has not present");
        return new UserNotFoundException("User" + userId + "has not been found");
        });
        return user;
    }

    @Override
    public User getUserByFirstName(String firstName) {
        return userRepository.findUserByFirstName(firstName).orElseThrow(() -> { logger.warn("User with first name" + firstName + " has not present");
            return new UserNotFoundByFirstNameException("User" + firstName + "has not been found");
        });
    }

    @Override
    public User getUserByLastName(String lastName) {
        Optional<User> optionalUser = userRepository.findByLastName(lastName);
        if (optionalUser.isPresent()) {
            logger.info("User with last name" + lastName + "is present");
            return optionalUser.get();
        } else {
            logger.warn("User with last name" + lastName + " has not present");
            throw new UserNotFoundByLastNameException("No User found by" + lastName);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> optional = userRepository.findUserByEmail(email);
        if (optional.isPresent()) {
            logger.info("User with email" + email + "is present");
            return optional.get();
        } else {
            logger.warn("User with email" + email + " has not present");
            throw new UserNotFoundByEmailException("No such user exists with email" + email);
        }
    }

    @Override
    public User getUserByNumber(String number) {
        User user= userRepository.findUserByNumber(number).orElseThrow(() ->{
            logger.warn("User with number" + number + " has not present");
            return new UserNotFoundByNumberException("No User having this no." + number);
        });
        logger.info("User with number" + number + "is Present");
        return user;
    }

    @Override
    public User getUserByFirstNameAndLastName(String firstName, String lastName){
        return userRepository.findUserByFirstNameAndLastName(firstName,lastName).orElseThrow(()->{
        logger.warn("User with first name" + firstName + "and lastName" + lastName + " has not present");
        return new UserNotFoundByNameException("User with name" + firstName+" "+ lastName + " has not present");
        });
    }

    @Override
    public User getUserByFirstNameAndEmail(String firstName, String email) {
        return userRepository.findUserByFirstNameAndEmail(firstName,email).orElseThrow(() -> {
            logger.warn("User with first name " + firstName + " and email " + email + " has not present");
            return new UserNotFoundByFirstNameAndEmailException("User having name " + firstName + " has not been found with email" + email);
        });
    }

    @Override
    public User getUserByLastNameAndEmail(String lastName, String email) {
        Optional<User> user = userRepository.findUserByLastNameAndEmail(lastName, email);
        if (user.isPresent()) {
            logger.info("User with last name" + lastName + "and email" + email + "is present");
            return user.get();
        } else {
            logger.warn("User with last name" + lastName + "and email" + email + " has not present");
            throw new UserNotFoundByLastNameAndEmailException(("No such user exists with LastName" + lastName + " and email" + email));
        }
    }

    @Override
    public Page<User> getAllUser(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<User> user=userRepository.findAll(pageable);
        if(user.isEmpty()){
            logger.warn("Till now there is not any data exist");
            throw new NoDataExistException("Till now there is not any data exist");
        }
        else {
            logger.info("All users are fetched successfully");
            return user;
        }
    }

    @Override
    public User updateUser(Long userId, User user) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            if (!userRepository.findUserByEmail(user.getEmail()).get().getUserId().equals(userId)) {
                logger.error("User with email" + user.getEmail() + "already exists");
                throw new UserAlreadyExistByThisEmailException("User with email " + user.getEmail() + " already exists can't update your email to this email");
            }
        }
        userRepository.findUserByNumber(user.getNumber()).ifPresent(
                user1 -> {
                    if (!user1.getUserId().equals(userId)) {
                        logger.error("User with number " + user.getNumber() + " already exists");
                        throw new UserAlreadyExistByThisNumberException("User with number" + user.getNumber() + "already exists can't update your number to this number");
                    }
                }
        );
        User existingUser=userRepository.findById(userId).orElseThrow(()-> {
            logger.warn("User with ID" + userId + "has not present");
            return new UserNotFoundException("User with ID" + userId + "has not been found");
        });
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setNumber(user.getNumber());
            logger.info("User with ID" + userId + "is updated successfully");
            return userRepository.save(existingUser);
    }

    @Override
    public String deleteUser(Long userId) {
            userRepository.findById(userId).orElseThrow(() -> {
                    logger.warn("User with ID {} is not present", userId);
                    return new UserNotFoundException("User with ID " + userId + " has not been found");
                });

        logger.info("User having ID {} has present and deleting", userId);
        userRepository.deleteById(userId);
        logger.info("User with ID {} has been deleted successfully", userId);
        return "User with ID " + userId + " has been deleted successfully";
    }

    @Override
    public String deleteUserByEmail(String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            logger.info("User having Email {} has present and deleting", email);
            userRepository.deleteUserByEmail(email);
            logger.info("User having email" + email + " has been deleted successfully");
            return "User having email" + email + " has been deleted successfully";
        } else {
            logger.warn("User with Email id " + email + " has not present");
            throw new UserNotFoundByEmailException("User with Email id " + email + " has not been found");
        }
    }

    @Override
    public Page<User> findUserByAgeGreater(int age, int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<User> user = userRepository.findUserByAgeGreater(pageable,age);
        if (user.isEmpty()) {
            logger.warn("There is not any user having age greater than " + age);
            throw new NoDataExistException("There is not any user having age greater than " + age);
        } else {
            logger.info("All users having age greater than " + age + " are fetched successfully");
            return user;
        }
    }

    @Override
    public Page<User> findUserByAgeLesser(int age, int pageNo, int pageSize) {
        Pageable pageable=PageRequest.of(pageNo,pageSize);
        Page<User> user = userRepository.findUserByAgeLesser(pageable, age);
        if (user.isEmpty()) {
            logger.warn("There is not any user having age lesser than " + age);
            throw new NoDataExistException("There is not any user having age lesser than " + age);
        } else {
            logger.info("All users having age lesser than " + age + " are fetched successfully");
            return user;
        }
    }

    @Override
    public Page<User> findUserByAgeBetween(int age1, int age2, int pageNo, int pageSize) {
        Pageable pageable=PageRequest.of(pageNo,pageSize);
        Page<User> user = userRepository.findUserByAgeBetween(pageable,age1, age2);
        if (user.isEmpty()) {
            logger.warn("There is not any user having age between " + age1 + " and " + age2);
            throw new NoDataExistException("There is not any user having age between " + age1 + " and " + age2);
        } else {
            logger.info("All users having age between " + age1 + " and " + age2 + " are fetched successfully");
            return user;
        }
    }

    @Override
    public String updateUserByEmail(Long userId, String email) {
        int i =userRepository.updateUserByEmail(userId,email);
        return "User's email has been updated successfully";
    }

    @Override
    public String updateUserByNumber(Long userId, String number) {
        int i =userRepository.updateUserByNumber(userId,number);
        return "User's number has been updated successfully";
    }

    @Override
    public List<User> getUserSort(String columnName, boolean ascending) {
        Sort sort=ascending? Sort.by(columnName).ascending():Sort.by(columnName).descending();
        List<User> allSortedUser=userRepository.findAll(sort);
        return allSortedUser;
    }

    @Override
    public Page<User> getSortedUserInPage(String columnName, boolean ascending, int pageNumber, int pageSize) {
        Sort sort=ascending?Sort.by(columnName).ascending():Sort.by(columnName).descending();
        Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
        Page<User> allSortedUserInPage=userRepository.findAll(pageable);
        return allSortedUserInPage;
    }


}