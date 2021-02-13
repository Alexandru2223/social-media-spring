package com.pibd.project1.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String roleId;

    @Column(name = "user_email")
    private String email;

    @Column(name = "authority")
    private String authority;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_email", nullable = false, insertable = false, updatable = false)
    private RegisterEntity registerEntity;

}
