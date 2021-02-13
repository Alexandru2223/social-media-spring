package com.pibd.project1.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "painting")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaintingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "painting_id")
    private long paintingId;

    @Column(name = "name")
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "likes")
    private long likes;

    @Column(name = "date")
    private Date date;

    @Column(name = "image")
    private byte[] image;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "paintings")
    private List<ArtistEntity> artists = new ArrayList<>();


    @OneToMany(mappedBy = "paintingEntity")
    private List<CommentEntity> commentEntities = new ArrayList<>();
}
