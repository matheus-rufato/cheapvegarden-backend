package com.cheapvegarden.repository.dto;

import java.time.LocalTime;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDto {

    private long id;

    @NotNull
    private long culturaId;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFim;
}