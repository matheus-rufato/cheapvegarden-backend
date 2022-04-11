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
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_controle")
    private Controle controle;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_setup")
    private Setup setup;

    @Column(name = "Nome")
    @NotNull
    @Size(max = 20)
    private String nome;
}
