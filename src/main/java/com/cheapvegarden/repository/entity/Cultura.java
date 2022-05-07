package com.cheapvegarden.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Cultura")
public class Cultura {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_controle")
    @NotNull
    private Controle controle;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_setup")
    @NotNull
    private Setup setup;

    @Column(name = "Nome")
    @Size(max = 20)
    @NotNull
    private String nome;
}
