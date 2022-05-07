package com.cheapvegarden.repository.dto;

import java.math.BigDecimal;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ControleDto {

    private long id;

    @NotNull
    private boolean statusSolenoide;

    @DecimalMax("100.00")
    @DecimalMin("-99.99")
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal temperaturaClima;

    @DecimalMax("100.00")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal umidadeClima;

    @DecimalMax("100.00")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal umidadeSolo;
}
