package com.example.BlogBYMay.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private String fullName;
    private String email;
    private String city;
    private String state;
    private String pincode;
}
