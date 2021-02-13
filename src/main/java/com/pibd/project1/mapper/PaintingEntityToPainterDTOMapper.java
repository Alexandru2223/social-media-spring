package com.pibd.project1.mapper;

import com.pibd.project1.domain.entity.PaintingEntity;
import com.pibd.project1.domain.model.PaintingDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PaintingEntityToPainterDTOMapper implements Converter<PaintingEntity, PaintingDTO> {

    @Override
    public PaintingDTO convert(PaintingEntity source) {

        return PaintingDTO.builder()
                .paintingId(source.getPaintingId())
                .name(source.getName())
                .date(source.getDate())
                .likes(source.getLikes())
                .text(source.getText())
                .image(source.getImage())
                .build();
    }

}
