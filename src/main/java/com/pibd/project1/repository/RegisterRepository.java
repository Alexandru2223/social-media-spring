package com.pibd.project1.repository;

import com.pibd.project1.domain.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Long> {
    RegisterEntity findRegisterEntityByToken(String token);
}
