package com.pibd.project1.repository;

import com.pibd.project1.domain.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    ArtistEntity getArtistEntityByEmail(String email);

    @Query("select a from ArtistEntity a inner join fetch a.likesEntities where a.email=:email")
    ArtistEntity getArtistAndLikes(String email);

    List<ArtistEntity> getArtistEntitiesByFullNameContaining(String text);

}
