package com.pibd.project1.controller;

import com.pibd.project1.domain.model.ArtistDTO;
import com.pibd.project1.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/artist")
@AllArgsConstructor
public class ArtistController {

    private final ArtistService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistDTO> getAllArtists(){
        return service.getAll();
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public ArtistDTO getArtistByEmail(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return service.getArtistByEmail(currentPrincipalName);
    }
    @GetMapping("/top5")
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistDTO> getTop5Artists(){

       return service.getTop5Artists();
    }

    @GetMapping("/search/{text}")
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistDTO> getArtistBySearch(@PathVariable(name = "text") String text){

        return service.getArtistBySearch(text);
    }


    @GetMapping("/likes")
    @ResponseStatus(HttpStatus.OK)
    public long getArtistTotalLikes(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return service.getArtistTotalLikes(currentPrincipalName);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ArtistDTO artistDTO) {
            service.create(artistDTO);
    }
}
