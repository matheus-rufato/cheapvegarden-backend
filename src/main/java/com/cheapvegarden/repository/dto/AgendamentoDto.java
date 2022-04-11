package com.cheapvegarden.repository.dto;

import java.time.LocalTime;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDto {
    
    private Long id;
    
    private Long culturaId;

    private LocalTime horaInicio;

    private LocalTime horaFim;
}
