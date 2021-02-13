package com.pibd.project1.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "artist_id")
    private long artistId;

    @Column(name = "painting_id")
    private long paintingId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "painting_id", updatable = false, insertable = false)
    private PaintingEntity paintingEntity;

    @ManyToOne
    @JoinColumn(name = "artist_id", updatable = false, insertable = false)
    private ArtistEntity artistEntity;

}
