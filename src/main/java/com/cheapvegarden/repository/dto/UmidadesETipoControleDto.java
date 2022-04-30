package com.cheapvegarden.repository.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UmidadesETipoControleDto {

    private Boolean tipoControle;

    private Integer umidadeMaxima;

    private Integer umidadeMinima;
}
