package com.pibd.project1.service;

import com.pibd.project1.domain.entity.ArtistEntity;
import com.pibd.project1.domain.entity.CommentEntity;
import com.pibd.project1.domain.entity.PaintingEntity;
import com.pibd.project1.domain.model.CommentDTO;
import com.pibd.project1.mapper.CommentDTOToCommentEntity;
import com.pibd.project1.mapper.CommentEntityToCommentDTOWithoutPainting;
import com.pibd.project1.repository.ArtistRepository;
import com.pibd.project1.repository.CommentRepository;
import com.pibd.project1.repository.PaintingRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    private final PaintingRepository paintingRepository;

    private final CommentEntityToCommentDTOWithoutPainting commentEntityToCommentDTOWithoutPainting;

    private final CommentDTOToCommentEntity commentDTOToCommentEntity;

    private final ArtistRepository artistRepository;

    public List<CommentDTO> getCommentsByPainting(long id) throws NotFoundException {

        PaintingEntity paintingEntity = paintingRepository.findById(id).orElseThrow(() -> new NotFoundException("Imaginea nu a fost gasita"));
        List<CommentEntity> commentEntities = paintingEntity.getCommentEntities();
        return commentEntities.stream()
                .map(commentEntityToCommentDTOWithoutPainting::convert)
                .collect(Collectors.toList());

    }

    @Transactional
    public void createComment(CommentDTO commentDTO){

        CommentEntity commentEntity = commentDTOToCommentEntity.convert(commentDTO);
        ArtistEntity artistEntity = artistRepository.findById(commentDTO.getArtistId()).get();
        PaintingEntity paintingEntity = paintingRepository.findById(commentDTO.getPaintingId()).get();
        commentEntity.setArtistEntity(artistEntity);
        commentEntity.setPaintingEntity(paintingEntity);
        repository.save(commentEntity);
    }

}
