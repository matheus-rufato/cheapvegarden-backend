package com.cheapvegarden.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Usuario")
public class Usuario {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    @NotNull
    @Size(max = 50)
    private String nome;

    @Column(name = "Telefone")
    @NotNull
    @Size(max = 11)
    private String telefone;

    @Column(name = "Email")
    @NotNull
    @Size(max = 50)
    @Email
    private String email;

    @Column(name = "Senha")
    @NotNull
    @Size(max = 10, min = 8)
    private String senha;
}
