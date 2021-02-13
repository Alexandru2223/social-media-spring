package com.pibd.project1.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private String registerId;

    private String fullName;

    private String email;

    private String password;

    private int enabled;

    private String token;
}
