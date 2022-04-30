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
    private Long id;

    @Column(name = "Status")
    @NotNull
    private Boolean status;

    @Column(name = "Tipo_controle")
    @NotNull
    private Boolean tipoControle;

    @Column(name = "Umidade_maxima")
    @NotNull
    @Positive
    @Max(100)
    private Integer umidadeMaxima;

    @Column(name = "Umidade_minima")
    @NotNull
    @Positive
    @Max(100)
    private Integer umidadeMinima;
}
