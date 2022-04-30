package com.cheapvegarden.repository.dto;

import java.math.BigDecimal;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ControleDto {

    private Long id;

    @NotNull
    private Boolean statusSolenoide;

    private BigDecimal temperaturaClima;

    private BigDecimal umidadeClima;

    private BigDecimal umidadeSolo;
}
