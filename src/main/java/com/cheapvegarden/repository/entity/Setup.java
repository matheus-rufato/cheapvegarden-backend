package com.cheapvegarden.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "setup")
public class Setup {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status")
    @NotNull
    private boolean status;

    @Column(name = "tipo_controle")
    @NotNull
    private boolean tipoControle;

    @Column(name = "umidade_maxima")
    @Positive
    @Max(100)
    @NotNull
    private int umidadeMaxima;

    @Column(name = "umidade_minima")
    @Positive
    @Max(100)
    @NotNull
    private int umidadeMinima;
}
