package com.pibd.project1.repository;

import com.pibd.project1.domain.entity.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<LikesEntity, Long> {

    @Modifying
    @Query("delete from LikesEntity l where l.id=:id")
     void deleteLike(@Param("id") long id);

}
