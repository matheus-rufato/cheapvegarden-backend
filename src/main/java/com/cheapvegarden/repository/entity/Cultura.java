package com.cheapvegarden.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cultura")
public class Cultura {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_controle")
    @NotNull
    private Controle controle;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_setup")
    @NotNull
    private Setup setup;

    @Column(name = "nome")
    @Size(max = 20)
    @NotNull
    private String nome;
}
