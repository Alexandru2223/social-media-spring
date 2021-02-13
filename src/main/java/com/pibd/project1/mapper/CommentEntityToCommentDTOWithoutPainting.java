package com.pibd.project1.mapper;

import com.pibd.project1.domain.entity.ArtistEntity;
import com.pibd.project1.domain.entity.CommentEntity;
import com.pibd.project1.domain.model.ArtistDTO;
import com.pibd.project1.domain.model.CommentDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentEntityToCommentDTOWithoutPainting implements Converter<CommentEntity, CommentDTO> {

    private final ArtistEntityToArtistDTOWithoutPaintingMapper artistEntityToArtistDTOWithoutPaintingMapper;

    @Override
    public CommentDTO convert(CommentEntity source) {

        return CommentDTO.builder()
                .id(source.getId())
                .paintingId(source.getPaintingId())
                .artistId(source.getArtistId())
                .comment(source.getComment())
                .date(source.getDate())
                .artist(mapArtist(source.getArtistEntity()))
                .build();
    }

    private ArtistDTO mapArtist(ArtistEntity artistEntity) {
        return artistEntityToArtistDTOWithoutPaintingMapper.convert(artistEntity);
    }
}
