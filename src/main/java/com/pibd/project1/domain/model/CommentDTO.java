package com.pibd.project1.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private long id;

    private long artistId;

    private long paintingId;

    private String comment;

    private Date date;

    private PaintingDTO painting;

    private ArtistDTO artist;

}
