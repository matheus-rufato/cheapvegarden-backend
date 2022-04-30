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
    private Long id;

    @Column(name = "Hora")
    private LocalTime hora;

    @Column(name = "Fluxo")
    @DecimalMax("100.00")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    private BigDecimal fluxo;

    @Column(name = "Temperatura_clima")
    @DecimalMax("100.00")
    @DecimalMin("-99.99")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal temperaturaClima;

    @Column(name = "Umidade_clima")
    @DecimalMax("100.00")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    private BigDecimal umidadeClima;

    @Column(name = "Umidade_solo")
    @DecimalMax("100.00")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    private BigDecimal umidadeSolo;
}
