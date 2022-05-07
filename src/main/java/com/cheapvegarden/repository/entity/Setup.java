package com.cheapvegarden.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Setup")
public class Setup {

    @Id
    @JoinColumn(name = "ID")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Status")
    @NotNull
    private boolean status;

    @Column(name = "Tipo_controle")
    @NotNull
    private boolean tipoControle;

    @Column(name = "Umidade_maxima")
    @Positive
    @Max(100)
    @NotNull
    private int umidadeMaxima;

    @Column(name = "Umidade_minima")
    @Positive
    @Max(100)
    @NotNull
    private int umidadeMinima;
}
