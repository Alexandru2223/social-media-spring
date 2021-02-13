package com.pibd.project1.repository;

import com.pibd.project1.domain.entity.PaintingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends JpaRepository<PaintingEntity, Long> {

    @Query("select p from PaintingEntity p inner join fetch p.artists a where a.artistId = :id")
    List<PaintingEntity> getPaintingsByArtist(@Param("id") long id);

    @Query("select p from PaintingEntity p inner join fetch p.artists a where a.email = :email")
    List<PaintingEntity> getLikesByArtistEmail(@Param("email") String email);

    @Query("select p from PaintingEntity p order by p.date desc ")
    List<PaintingEntity> getPaintingEntitiesSortedByDate();

    @Query("select p from PaintingEntity p inner join fetch p.commentEntities c where p.paintingId = :id")
    PaintingEntity getPaintingWithComments(@Param("id") long id);

}
