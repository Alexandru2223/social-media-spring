package com.pibd.project1.controller;

import com.pibd.project1.domain.model.RegisterDTO;
import com.pibd.project1.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private final RegisterService service;

    @PostMapping
    public RegisterDTO create(@RequestBody RegisterDTO registerDTO) {

        return service.create(registerDTO);
    }

    @GetMapping("/{token}")
    public void validateAccount(@PathVariable(name = "token") String token) throws Exception {
        service.validateAccount(token);
    }
}
