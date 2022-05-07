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
    private long id;

    @Column(name = "Nome")
    @Size(max = 50)
    @NotNull
    private String nome;

    @Column(name = "Telefone")
    @Size(max = 11)
    @NotNull
    private String telefone;

    @Column(name = "Email")
    @Size(max = 50)
    @Email
    @NotNull
    private String email;

    @Column(name = "Senha")
    @Size(max = 10, min = 8)
    @NotNull
    private String senha;
}
