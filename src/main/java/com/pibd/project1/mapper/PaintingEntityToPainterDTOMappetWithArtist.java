package com.pibd.project1.mapper;

import com.pibd.project1.domain.entity.ArtistEntity;
import com.pibd.project1.domain.entity.PaintingEntity;
import com.pibd.project1.domain.model.ArtistDTO;
import com.pibd.project1.domain.model.PaintingDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PaintingEntityToPainterDTOMappetWithArtist implements Converter<PaintingEntity, PaintingDTO> {

    private final ArtistEntityToArtistDTOWithoutPaintingMapper artistEntityToArtistDTOWithoutPaintingMapper;

    @Override
    public PaintingDTO convert(PaintingEntity source) {

        return PaintingDTO.builder()
                .paintingId(source.getPaintingId())
                .name(source.getName())
                .date(source.getDate())
                .likes(source.getLikes())
                .text(source.getText())
                .image(source.getImage())
                .artists(mapArtist(source.getArtists()))
                .build();
    }

    private List<ArtistDTO> mapArtist(List<ArtistEntity> artists) {
        return artists.stream()
                .map(artistEntityToArtistDTOWithoutPaintingMapper::convert)
                .collect(Collectors.toList());
    }
}
