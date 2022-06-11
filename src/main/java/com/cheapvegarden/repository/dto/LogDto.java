package com.cheapvegarden.repository.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogDto {

    private long id;

    private LocalTime hora;

    private LocalDate data;

    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal fluxo;

    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal temperaturaClima;

    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal umidadeClima;

    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal umidadeSolo;
}