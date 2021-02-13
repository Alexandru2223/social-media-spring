package com.pibd.project1.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "artist_id")
    private long artistId;

    @Column(name = "painting_id")
    private long paintingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", insertable = false, updatable = false)
    private ArtistEntity artistEntity;

}
