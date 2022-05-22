package com.cheapvegarden.repository.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetupSemLigacaoDto {

    private long id;

    @Positive
    @Max(100)
    @NotNull
    private int umidadeMaxima;

    @Positive
    @Max(100)
    @NotNull
    private int umidadeMinima;
}