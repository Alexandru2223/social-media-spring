package com.pibd.project1.mapper;

import com.pibd.project1.domain.entity.LikesEntity;
import com.pibd.project1.domain.model.LikesDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LikesEntityToLikesDTOWithoutArtist implements Converter<LikesEntity, LikesDTO> {


    @Override
    public LikesDTO convert(LikesEntity source) {

        return LikesDTO.builder()
                .id(source.getId())
                .artistId(source.getArtistId())
                .paintingId(source.getPaintingId())
                .build();
    }
}
