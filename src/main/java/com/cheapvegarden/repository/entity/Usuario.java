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
@UserDefinition
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    @Size(max = 100)
    @NotNull
    private String nome;

    @Column(name = "telefone")
    @Size(max = 15, min = 11)
    @NotNull
    private String telefone;

    @Column(name = "email")
    @Username
    @Size(max = 100)
    @Email
    @NotNull
    private String email;

    @Column(name = "senha")
    @Password
    @Size(min = 8)
    @NotNull
    private String senha;

    @Column(name = "tipo")
    @Roles
    @Size(max = 5, min = 4)
    @NotNull
    private String tipo;
}