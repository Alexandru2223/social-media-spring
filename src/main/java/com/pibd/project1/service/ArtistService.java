package com.pibd.project1.service;

import com.pibd.project1.domain.entity.ArtistEntity;
import com.pibd.project1.domain.entity.PaintingEntity;
import com.pibd.project1.domain.model.ArtistDTO;
import com.pibd.project1.mapper.ArtistDTOToArtistEntityMapper;
import com.pibd.project1.mapper.ArtistEntityToArtistDTOMapper;
import com.pibd.project1.repository.ArtistRepository;
import com.pibd.project1.repository.PaintingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;

    private final ArtistEntityToArtistDTOMapper artistEntityToArtistDTOMapper;

    private final ArtistDTOToArtistEntityMapper artistDTOToArtistEntityMapper;

    private final PaintingRepository paintingRepository;

    public List<ArtistDTO> getAll() {

        return repository.findAll()
                .stream()
                .map(artistEntityToArtistDTOMapper::convert)
                .collect(Collectors.toList());

    }

    public void create(ArtistDTO artistDTO) {

        ArtistEntity artistEntity = artistDTOToArtistEntityMapper.convert(artistDTO);
        System.out.println(artistEntity);
        repository.save(artistEntity);
    }

    public ArtistDTO getArtistByEmail(String email) {

        ArtistEntity artistEntityByEmail = repository.getArtistEntityByEmail(email);
        return artistEntityToArtistDTOMapper.convert(artistEntityByEmail);
    }

    public long getArtistTotalLikes(String email) {

        List<PaintingEntity> paintingEntities = paintingRepository.getLikesByArtistEmail(email);
        return paintingEntities.stream()
                .map(x -> x.getLikes())
                .reduce(0L, Long::sum);
    }

    public List<ArtistDTO> getArtistBySearch(String text){

        List<ArtistEntity> artistEntitiesByFullNameContaining = repository.getArtistEntitiesByFullNameContaining(text);
        return artistEntitiesByFullNameContaining.stream()
                .map(artistEntityToArtistDTOMapper::convert).collect(Collectors.toList());

    }

    public List<ArtistDTO> getTop5Artists(){
            return null;

    }
}
