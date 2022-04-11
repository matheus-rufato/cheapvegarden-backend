package com.cheapvegarden.repository.entity;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.*;

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

    @Column(name = "Status_solenoide")
    private Boolean statusSolenoide;

    @Column(name = "Hora")
    private LocalTime hora;

    @Column(name = "Fluxo")
    private BigDecimal fluxo;

    @Column(name = "Temperatura_clima")
    private BigDecimal temperaturaClima;

    @Column(name = "Umidade_clima")
    private BigDecimal umidadeClima;

    @Column(name = "Umidade_solo")
    private BigDecimal umidadeSolo;
}
