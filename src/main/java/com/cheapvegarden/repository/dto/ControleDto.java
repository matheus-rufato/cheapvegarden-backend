package com.cheapvegarden.repository.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ControleDto {

    private Long id;

    @NotNull
    @Positive
    @Max(99)
    @Min(1)
    private Integer umidadeMaxima;

    @NotNull
    @Positive
    @Max(99)
    @Min(1)
    private Integer umidadeMinima;
}
