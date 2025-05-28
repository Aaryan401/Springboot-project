package com.example.BlogBYMay.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProfileDto {
    private String fullName;
    private String email;
    private String password;
    private int age;
    private String mobile;
    private String address;
    private String city;
    private String state;
    private String pincode;
}
