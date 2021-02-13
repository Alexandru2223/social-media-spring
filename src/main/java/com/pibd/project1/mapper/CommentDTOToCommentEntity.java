package com.pibd.project1.mapper;

import com.pibd.project1.domain.entity.CommentEntity;
import com.pibd.project1.domain.model.CommentDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentDTOToCommentEntity implements Converter<CommentDTO, CommentEntity> {

    @Override
    public CommentEntity convert(CommentDTO source) {

        return CommentEntity.builder()
                .artistId(source.getArtistId())
                .paintingId(source.getPaintingId())
                .date(source.getDate())
                .comment(source.getComment())
                .build();
    }
}
