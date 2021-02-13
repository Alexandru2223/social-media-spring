package com.pibd.project1.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikesDTO {

    private long id;

    private long artistId;

    private long paintingId;

    private ArtistDTO artistDTO;

}
