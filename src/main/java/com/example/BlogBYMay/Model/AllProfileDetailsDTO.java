package com.example.BlogBYMay.Model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AllProfileDetailsDTO {
    private Long profileId;
    private String fullName;
    private String age;
    private String email;
    private String mobile;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private LocalDateTime registerDate;
    private LocalDateTime profileCreatedDate;
    private LocalDateTime profileUpdatedDate;
}
