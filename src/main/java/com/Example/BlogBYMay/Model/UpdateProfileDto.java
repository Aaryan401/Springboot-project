package com.example.BlogBYMay.Model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProfileDto {

    @NotBlank(message = "Full name must not be Blank")
    @Pattern(regexp="^[A-Za-z]+$", message = "Full Name must contain only alphabet")
    private String fullName;

    @NotBlank(message = "Email must not be Blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password must not be null")
    @Pattern(regexp="^[A-Za-z0-9!@$%^&*]+$", message = "Password must contain only alphanumeric")
    private String password;

    @NotBlank(message = "Age must not be Blank")
    @Pattern(regexp="^[0-9]+$", message = "Age must be numeric")
    @Min(value = 18, message = "Age must be greater than 18")
    private int age;

    @NotBlank(message = "Mobile no. must not be Blank")
    @Pattern(regexp = "^[0-9+]+$", message = "Mobile no. must be numeric")
    @Size(min = 10, max = 12, message = "Mobile no. must have 10 digit")
    private String mobile;

    @NotBlank(message = "Address must not be Blank")
    private String address;

    @NotBlank(message = "City must not be Blank")
    private String city;

    @NotBlank(message = "State must not be Blank")
    private String state;

    @NotBlank(message = "Pincode must not be Blank")
    @Size(min = 6, max = 6, message = "Pincode must be 6 digits")
    @Pattern(regexp = "^[0-9]+$", message = "Pincode must be numeric")
    private String pincode;
}
