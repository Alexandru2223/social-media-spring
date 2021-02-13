package com.pibd.project1.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "register")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idregister")
    private String registerId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @Column(name = "token")
    private String token;

    @OneToOne(mappedBy = "registerEntity", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private RoleEntity roleEntity;

}
