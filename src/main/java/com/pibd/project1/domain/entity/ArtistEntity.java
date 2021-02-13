package com.pibd.project1.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "artist")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private long artistId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "born")
    private Date born;

    @Column(name = "country")
    private String country;

    @Column(name = "avatar")
    private byte[] avatar;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "artist_painting",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "painting_id") }
    )
    private List<PaintingEntity> paintings = new ArrayList<>();

    @OneToMany(mappedBy = "artistEntity", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<LikesEntity> likesEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artistEntity", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntities = new ArrayList<>();
}
