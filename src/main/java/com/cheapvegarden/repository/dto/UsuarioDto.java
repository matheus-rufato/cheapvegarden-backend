package com.cheapvegarden.repository.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private long id;

    @Size(max = 100)
    @NotNull
    private String nome;

    @Size(max = 15, min = 11)
    @NotNull
    private String telefone;

    @Size(max = 100)
    @Email
    @NotNull
    private String email;

    @Size(max = 10, min = 8)
    @NotNull
    private String senha;

    @Size(max = 5, min = 4)
    @NotNull
    private String tipo;
}