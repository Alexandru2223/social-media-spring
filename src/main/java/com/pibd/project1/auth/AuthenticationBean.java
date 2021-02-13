package com.pibd.project1.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationBean {

    private String message;

    @Override
    public String toString() {
        return String.format("Welcome [message=%s]", message);

    }
}
