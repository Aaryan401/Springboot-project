package com.example.BlogBYMay.Model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AllProfileDetailsDTO {

    private Long profileId;

    @NotBlank(message="Full name must not be Blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Full name must contain only alphabets")
    private String fullName;

    @NotBlank(message = "Age must not be Blank")
    @Min(value = 18, message = "Age must be greater than 18")
    private int age;

    @NotBlank(message="Email must not be Blank")
    @Email(message="Email must be valid")
    private String email;

    @NotBlank(message="Password must not be Blank")
    @Size(min=10,max=13,message = "Mobile no. must have 10 digit")
    private String mobile;

    @NotBlank(message = "Address must not be Blank")
    private String address;

    @NotBlank(message = "City must not be Blank")
    private String city;

    @NotBlank(message = "State must not be Blank")
    private String state;

    @NotBlank(message = "Pincode must not be Blank")
    @Size(min=6,max=6,message = "Pincode must have 6 digit")
    private String pincode;

    private String image;

    private LocalDateTime registerDate;
    private LocalDateTime profileCreatedDate;
    private LocalDateTime profileUpdatedDate;
}
