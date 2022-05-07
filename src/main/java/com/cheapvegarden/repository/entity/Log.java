package com.cheapvegarden.repository.entity;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Log")
public class Log {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Hora")
    @NotNull
    private LocalTime hora;

    @Column(name = "Fluxo")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal fluxo;

    @Column(name = "Temperatura_clima")
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal temperaturaClima;

    @Column(name = "Umidade_clima")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal umidadeClima;

    @Column(name = "Umidade_solo")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal umidadeSolo;
}
