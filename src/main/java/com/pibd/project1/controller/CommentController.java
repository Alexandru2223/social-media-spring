package com.pibd.project1.controller;


import com.pibd.project1.domain.model.CommentDTO;
import com.pibd.project1.service.CommentService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private CommentService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDTO> getCommentsByPainting(@PathVariable(name = "id") long id) throws NotFoundException {

        return service.getCommentsByPainting(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CommentDTO comment) {
        service.createComment(comment);
    }
}
