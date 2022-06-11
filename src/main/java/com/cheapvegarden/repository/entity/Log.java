package com.cheapvegarden.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "log")
public class Log {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hora")
    @NotNull
    private LocalTime hora;

    @Column(name = "data")
    @NotNull
    private LocalDate data;

    @Column(name = "fluxo")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal fluxo;

    @Column(name = "temperatura_clima")
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal temperaturaClima;

    @Column(name = "umidade_clima")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal umidadeClima;

    @Column(name = "umidade_solo")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal umidadeSolo;
}