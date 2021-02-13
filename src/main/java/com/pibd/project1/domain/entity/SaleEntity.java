package com.pibd.project1.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sale")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private long saleId;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private ArtistEntity artistEntity;

    @ManyToOne
    @JoinColumn(name = "painting_id")
    private PaintingEntity paintingEntity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "condition")
    private String condition;


}
