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
    
    private LocalTime hora;

    @NotNull
    @PositiveOrZero
    private BigDecimal fluxo;

    @NotNull
    private BigDecimal temperaturaClima;

    @NotNull
    @PositiveOrZero
    @DecimalMax("100.00")
    private BigDecimal umidadeClima;

    @NotNull
    @PositiveOrZero
    @DecimalMax("100.00")
    private BigDecimal umidadeSolo;
}
