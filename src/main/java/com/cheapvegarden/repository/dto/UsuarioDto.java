package com.cheapvegarden.repository.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    
    @Size(max = 50)
    @NotNull
    private String nome;

    @Size(max = 11)
    @NotNull
    private String telefone;

    @Size(max = 50)
    @NotNull
    private String email;

    @Size(max = 6, min = 6)
    @NotNull
    private String senha;
}
