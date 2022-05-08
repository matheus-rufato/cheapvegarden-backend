package com.cheapvegarden.repository.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long id;

    @Size(max = 50)
    @NotNull
    private String nome;

    @Size(max = 11)
    @NotNull
    private String telefone;

    @Size(max = 20)
    @NotNull
    private String username;

    @Size(max = 50)
    @Email
    @NotNull
    private String email;

    @Size(max = 10, min = 8)
    @NotNull
    private String senha;

    @NotNull
    private String tipo;
}
