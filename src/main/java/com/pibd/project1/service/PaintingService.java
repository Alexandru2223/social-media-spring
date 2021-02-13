package com.pibd.project1.service;

import com.pibd.project1.domain.entity.ArtistEntity;
import com.pibd.project1.domain.entity.CommentEntity;
import com.pibd.project1.domain.entity.PaintingEntity;
import com.pibd.project1.domain.model.PaintingDTO;
import com.pibd.project1.mapper.PaintingDTOToPaintingEntityMapperWithoutArtist;
import com.pibd.project1.mapper.PaintingEntityToPainterDTOMapper;
import com.pibd.project1.mapper.PaintingEntityToPainterDTOMappetWithArtist;
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
public class PaintingService {

    private final PaintingRepository repository;

    private final ArtistRepository artistRepository;

    private final CommentRepository commentRepository;

    private final PaintingEntityToPainterDTOMapper paintingEntityToPainterDTOMapper;

    private final PaintingEntityToPainterDTOMappetWithArtist paintingEntityToPainterDTOMappetWithArtist;

    private final PaintingDTOToPaintingEntityMapperWithoutArtist paintingDTOToPaintingEntityMapperWithoutArtist;

    public List<PaintingDTO> getPaintingByArtist(long id) {

        return repository.getPaintingsByArtist(id)
                .stream()
                .map(paintingEntityToPainterDTOMapper::convert)
                .collect(Collectors.toList());
    }

    public List<PaintingDTO> getPaintingById(long id) {

        return repository.findById(id)
                .stream()
                .map(paintingEntityToPainterDTOMappetWithArtist::convert)
                .collect(Collectors.toList());
    }

    public List<PaintingDTO> getAllPaintings() {

        return repository.getPaintingEntitiesSortedByDate()
                .stream()
                .map(paintingEntityToPainterDTOMappetWithArtist::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void getLikes(long id) {

        PaintingEntity paintingEntity = repository.findById(id).get();
        paintingEntity.setLikes(paintingEntity.getLikes() + 1);
        repository.save(paintingEntity);

    }

    @Transactional
    public void dropLikes(long id) {

        PaintingEntity paintingEntity = repository.findById(id).get();
        paintingEntity.setLikes(paintingEntity.getLikes() - 1);
        repository.save(paintingEntity);

    }


    @Transactional
    public void createPainting(PaintingDTO paintingDTO, String email) {

        ArtistEntity artistEntityByEmail = artistRepository.getArtistEntityByEmail(email);
        PaintingEntity paintingEntity = paintingDTOToPaintingEntityMapperWithoutArtist.convert(paintingDTO);
        PaintingEntity savedEntity = repository.save(paintingEntity);
        List<PaintingEntity> paintings = artistEntityByEmail.getPaintings();
        paintings.add(savedEntity);
        artistEntityByEmail.setPaintings(paintings);


    }

    public void delete(long id) throws NotFoundException{

        PaintingEntity paintingEntity = repository.findById(id).orElseThrow(()-> new NotFoundException("Paiting with id provided doesn't exists"));
        List<CommentEntity> allByPaintingId = commentRepository.findAllByPaintingId(id);
        allByPaintingId.forEach(x -> commentRepository.delete(x));
        repository.delete(paintingEntity);
    }


}
