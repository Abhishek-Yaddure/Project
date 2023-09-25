package com.project.project.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private String name;

    private String email;

    private String password;

    private long  mobile_no;

    private String date_of_brith;

    private String gender;

    private String address;
}
