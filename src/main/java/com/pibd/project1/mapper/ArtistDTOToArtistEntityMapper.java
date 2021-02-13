package com.pibd.project1.mapper;

import com.pibd.project1.domain.entity.ArtistEntity;
import com.pibd.project1.domain.model.ArtistDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArtistDTOToArtistEntityMapper implements Converter<ArtistDTO, ArtistEntity> {

    @Override
    public ArtistEntity convert(ArtistDTO source) {

        return ArtistEntity.builder()
                .artistId(source.getArtistId())
                .born(source.getBorn())
                .email(source.getEmail())
                .country(source.getCountry())
                .fullName(source.getFullName())
                .avatar(source.getAvatar())
                .build();
    }
}
