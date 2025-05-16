package com.example.demo_may.Repository;

import com.example.demo_may.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//It is data access layer
//JpaReprository give permission Reprository to access the data
//Jpa stands for Java Persistence Api
//It is a helper that makes it supereasy to interact with a database without writing a lot of code.
//It is a repository between Java code and Database. It helps us to perform CURD Operation without any writing lot's of code.


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
                                                    //Entity(Class name), Id datatype
    //We are not going to create any kind of abstract method here.

    //This is the custom abstract method
    public List<User> findAllByAge(int age);

    // To find anything using custom logic just we have to give findBy followed by <parameterNAme> or findAllBy followed by<parametername>
   // public User findByEmail(String email);

    //Using two parameter
    // public List<User> findAllByAgeAndfirstName(int age, String name);
}
