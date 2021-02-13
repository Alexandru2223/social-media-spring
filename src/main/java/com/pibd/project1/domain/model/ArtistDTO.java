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
public class ArtistDTO {

    private long artistId;

    private String fullName;

    private String email;

    private Date born;

    private String country;

    private byte[] avatar;

    private List<PaintingDTO> paintings;

    private List<LikesDTO> likes;

    private List<CommentDTO> comments;

}
