package com.pibd.project1.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {

    private long saleId;

    private ArtistDTO artistDTO;

    private PaintingDTO paintingDTO;

    private BigDecimal price;

    private String condition;

}
