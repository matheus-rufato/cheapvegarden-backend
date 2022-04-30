package com.cheapvegarden.repository.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Controle")
public class Controle {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "controle")
    private List<Cultura> culturas;

    @Column(name = "Status_solenoide")
    @NotNull
    private Boolean statusSolenoide;

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

    @Column(name = "Desabilitar_agendamento")
    private Boolean desabilitarAgendamento;
}
