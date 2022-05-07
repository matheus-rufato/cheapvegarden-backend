package com.cheapvegarden.repository.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetupDto {

    private long id;

    @NotNull
    private boolean status;

    @NotNull
    private boolean tipoControle;

    @Positive
    @Max(100)
    @NotNull
    private int umidadeMaxima;

    @Positive
    @Max(100)
    @NotNull
    private int umidadeMinima;
}
