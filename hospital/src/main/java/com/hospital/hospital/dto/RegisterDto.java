package com.hospital.hospital.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class RegisterDto {

    private String name;
    private String email;
    private String password;
    private String role;

}
