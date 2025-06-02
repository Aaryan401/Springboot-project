package com.example.BlogBYMay.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    @NotBlank(message = "Full name must not be Blank")
    @Pattern(regexp = "^[A-Za*z]+$", message = "Full name must contain only alphabet")
    private String fullName;

    @NotBlank(message = "Email must not be Blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "City must not be blank")
    private String city;

    @NotBlank(message = "State must not be blank")
    private String state;

    @NotBlank(message = "Pincode must not be blank")
    @Size(min=6, max=6, message = "Pincode must be 6 digits")
    private String pincode;
}
