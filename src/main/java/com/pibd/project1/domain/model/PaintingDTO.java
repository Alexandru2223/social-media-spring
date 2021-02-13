package com.pibd.project1.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaintingDTO {

    private long paintingId;

    private String name;

    private String text;

    private long likes;

    private Date date;

    private byte[] image;

    private List<ArtistDTO> artists;

    private List<CommentDTO> comments;
}
