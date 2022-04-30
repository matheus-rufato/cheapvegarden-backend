package com.cheapvegarden.repository.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetupSemLigacaoDto {

    private Long id;

    private Integer umidadeMaxima;

    private Integer umidadeMinima;
}
