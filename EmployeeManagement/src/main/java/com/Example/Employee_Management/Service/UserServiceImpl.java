package com.Example.Employee_Management.Service;
import com.Example.Employee_Management.Entity.User;
import com.Example.Employee_Management.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Example.Employee_Management.Exception.CustomException.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserServiceIterface {
    private static final Logger logger = LoggerFactory.getLogger("UserServiceImpl.class");
    @Autowired
    private final UserRepository userRepository;

    @Override
    public String saveUser(User user) {
        if (userRepository.findUserByFirstNameAndLastName(user.getFirstName(), user.getLastName()).isPresent()) {
            logger.error("User with name" +user.getFirstName()+" {} already exists", user.getLastName()); //Here {} tells that there's a data will come
            throw new UserAlreadyExistsException("User with name " + user.getFirstName() + " " + user.getLastName() + " already exists");
        }
        userRepository.findUserByEmail(user.getEmail()).ifPresent(
                user1 -> {
                    logger.error("User with email" + user1.getEmail() + "already exists");
                    throw new UserAlreadyExistByThisEmailException("User with email" + user1.getEmail() + "already exists");
                }
        );
        userRepository.findUserByNumber(user.getPhoneNumber()).ifPresent(
                user1 -> {
                    logger.error("User with number" + user1.getPhoneNumber() + "already exists");
                    throw new UserAlreadyExistByThisNumberException("User with number" + user1.getPhoneNumber() + "already exists");
                }
        );
        logger.info("User is going to registered");
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
        logger.info("User with first name" + firstName + "is going to be fetched");
        return userRepository.findUserByFirstName(firstName).orElseThrow(() -> { logger.warn("User with first name" + firstName + " has not present");
            return new UserNotFoundByFirstNameException("User" + firstName + "has not been found");
        });
    }

    @Override
    public User getUserByLastName(String lastName) {
        Optional<User> optionalUser = userRepository.findByLastName(lastName);
        if (optionalUser.isPresent()) {
            logger.info("User with last name" + lastName + "is going to be fetched");
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
            logger.info("User with email" + email + "is going to be fetched");
            return optional.get();
        } else {
            logger.warn("User with email" + email + " has not present");
            throw new UserNotFoundByEmailException("No such user exists with email" + email);
        }
    }

    @Override
    public User getUserByNumber(String number) {
        logger.info("User with number" + number + "is going to be fetched");
        return userRepository.findUserByNumber(number).orElseThrow(() ->{ logger.warn("User with number" + number + " has not present");
            return new UserNotFoundByNumberException("No User having this no." + number);
        });
    }

    @Override
    public User getUserByFirstNameAndEmail(String firstName, String email) {
        logger.info("User with first name" + firstName + "and email" + email + "is going to be fetched");
        return userRepository.findUserByFirstNameAndEmail(firstName, email).orElseThrow(() -> { logger.warn("User with first name" + firstName + "and email" + email + " has not present");
            return new UserNotFoundByFirstNameAndEmailException("User" + firstName + "has not been found with email" + email);
        });
    }

    @Override
    public User getUserByLastNameAndEmail(String lastName, String email) {
        Optional<User> user = userRepository.findUserByLastNameAndEmail(lastName, email);
        if (user.isPresent()) {
            logger.info("User with last name" + lastName + "and email" + email + "is going to be fetched");
            return user.get();
        } else {
            logger.warn("User with last name" + lastName + "and email" + email + " has not present");
            throw new UserNotFoundByLastNameAndEmailException(("No such user exists with LastName" + lastName + " and email" + email));
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> user=userRepository.findAll();
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
    public User updateUser(User user, Long userId) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            if (!userRepository.findUserByEmail(user.getEmail()).get().getUserId().equals(userId)) {
                logger.error("User with email" + user.getEmail() + "already exists");
                throw new UserAlreadyExistByThisEmailException("User with email" + user.getEmail() + "already exists can't update your email to this email");
            }
        }
        userRepository.findUserByNumber(user.getPhoneNumber()).ifPresent(
                user1 -> {
                    if (!user1.getUserId().equals(userId)) {
                        logger.error("User with number" + user.getPhoneNumber() + "already exists");
                        throw new UserAlreadyExistByThisNumberException("User with number" + user.getPhoneNumber() + "already exists can't update your number to this number");
                    }
                }
        );
        User existingUser=userRepository.findById(userId).orElseThrow(()-> {
            logger.warn("User with ID" + userId + "has not present");
            return new UserNotFoundException("User with ID" + userId + "has not been found");
        });
            logger.info("User with ID" + userId + "is going to be updated");
            existingUser.setUserId(user.getUserId());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            return userRepository.save(existingUser);
    }

    @Override
    public String deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.warn("User with ID {} is not present", userId);
                    return new UserNotFoundException("User with ID " + userId + " has not been found");
                });

        logger.info("User with ID {} is going to be deleted", userId);
        userRepository.deleteById(userId);
        logger.info("User with ID {} has been deleted successfully", userId);
        return "User with ID " + userId + " has been deleted successfully";
    }

    @Override
    public String deleteUserByEmail(String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            logger.info("User having" + email + " is going to be deleted");
            userRepository.deleteUserByEmail(email);
            logger.info("User having" + email + " has been deleted successfully");
            return "User having" + email + " has been deleted successfully";
        } else {
            logger.warn("User with Email id " + email + " has not present");
            throw new UserNotFoundByEmailException("User with Email id " + email + " has not been found");
        }
    }
}