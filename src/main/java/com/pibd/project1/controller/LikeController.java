package com.pibd.project1.controller;

import com.pibd.project1.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/like")
@AllArgsConstructor
public class LikeController {

    private final LikeService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void giveLike(@PathVariable(name = "id") long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        service.giveLike(id, currentPrincipalName);
    }

}
