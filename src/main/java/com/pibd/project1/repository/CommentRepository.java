package com.pibd.project1.repository;

import com.pibd.project1.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByPaintingId(long id);

}
