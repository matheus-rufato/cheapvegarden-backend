package com.cheapvegarden.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@UserDefinition
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    @Size(max = 50)
    @NotNull
    private String nome;

    @Column(name = "telefone")
    @Size(max = 11)
    @NotNull
    private String telefone;

    @Column(name = "username")
    @Size(max = 20)
    @NotNull
    @Username
    private String username;

    @Column(name = "email")
    @Size(max = 50)
    @Email
    @NotNull
    private String email;

    @Column(name = "senha")
    @Size(max = 10, min = 8)
    @NotNull
    @Password
    private String senha;

    @Column(name = "tipo")
    @NotNull
    @Roles
    private String tipo;
}
