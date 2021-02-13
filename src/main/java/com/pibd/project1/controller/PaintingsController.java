package com.pibd.project1.controller;

import com.pibd.project1.domain.model.PaintingDTO;
import com.pibd.project1.service.PaintingService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/paintings")
@AllArgsConstructor
public class PaintingsController {

    private final PaintingService service;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PaintingDTO> getPaintingsByArtist(@PathVariable(name = "id") long id) {

        return service.getPaintingByArtist(id);
    }
    @GetMapping("/painting/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PaintingDTO> getPaintingById(@PathVariable(name = "id") long id) {

        return service.getPaintingById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PaintingDTO> getAllPatintings() {
        return service.getAllPaintings();
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PaintingDTO paintingDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        service.createPainting(paintingDTO, currentPrincipalName);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "id") long id) throws NotFoundException {
        service.delete(id);
    }



}
