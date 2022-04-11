package com.cheapvegarden.repository.entity;

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

    @Column(name = "Umidade_maxima")
    @NotNull
    @Positive
    @Max(99)
    @Min(1)
    private Integer umidadeMaxima;

    @Column(name = "Umidade_minima")
    @NotNull
    @Positive
    @Max(99)
    @Min(1)
    private Integer umidadeMinima;

}
