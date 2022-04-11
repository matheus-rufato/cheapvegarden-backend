package com.cheapvegarden.repository.dto;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogDto {
    
    @NotNull
    private Boolean statusSolenoide;

    private LocalTime hora;

    @NotNull
    @PositiveOrZero
    private BigDecimal fluxo;

    @NotNull
    private BigDecimal temperaturaClima;

    @NotNull
    @PositiveOrZero
    @DecimalMax("99.99")
    private BigDecimal umidadeClima;

    @NotNull
    @PositiveOrZero
    @DecimalMax("99.99")
    private BigDecimal umidadeSolo;
}
