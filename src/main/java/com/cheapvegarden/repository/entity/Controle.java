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
@Entity(name = "controle")
public class Controle {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "controle")
    private List<Cultura> culturas;

    @Column(name = "status_solenoide")
    @NotNull
    private boolean statusSolenoide;

    @Column(name = "temperatura_clima")
    @DecimalMax("100.00")
    @DecimalMin("-99.99")
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal temperaturaClima;

    @Column(name = "umidade_clima")
    @DecimalMax("100.00")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal umidadeClima;

    @Column(name = "umidade_solo")
    @DecimalMax("100.00")
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal umidadeSolo;

    @Column(name = "desabilitar_agendamento")
    private boolean desabilitarAgendamento;
}
