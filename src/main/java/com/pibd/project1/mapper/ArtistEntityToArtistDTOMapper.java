package com.pibd.project1.mapper;

import com.pibd.project1.domain.entity.ArtistEntity;
import com.pibd.project1.domain.entity.LikesEntity;
import com.pibd.project1.domain.entity.PaintingEntity;
import com.pibd.project1.domain.model.ArtistDTO;
import com.pibd.project1.domain.model.LikesDTO;
import com.pibd.project1.domain.model.PaintingDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ArtistEntityToArtistDTOMapper implements Converter<ArtistEntity, ArtistDTO> {

    private final PaintingEntityToPainterDTOMapper paintingEntityToPainterDTOMapper;

    private final LikesEntityToLikesDTOWithoutArtist likesEntityToLikesDTOWithoutArtist;

    @Override
    public ArtistDTO convert(ArtistEntity source) {

        return ArtistDTO.builder()
                .artistId(source.getArtistId())
                .country(source.getCountry())
                .born(source.getBorn())
                .email(source.getEmail())
                .fullName(source.getFullName())
                .avatar(source.getAvatar())
                .paintings(mapPainting(source.getPaintings()))
                .likes(mapLikes(source.getLikesEntities()))
                .build();
    }

    private List<PaintingDTO> mapPainting(List<PaintingEntity> paintings) {

        return paintings.stream()
                .map(paintingEntityToPainterDTOMapper::convert)
                .collect(Collectors.toList());
    }

    private List<LikesDTO> mapLikes(List<LikesEntity> likes) {

        return likes.stream()
                .map(likesEntityToLikesDTOWithoutArtist::convert)
                .collect(Collectors.toList());
    }


}
