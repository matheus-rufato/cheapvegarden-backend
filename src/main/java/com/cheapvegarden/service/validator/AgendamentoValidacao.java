package com.cheapvegarden.service.validator;

import java.time.LocalTime;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgendamentoValidacao {

    public void validarHoraFimDoAgendamento(LocalTime horaInicio, LocalTime horaFim) throws Exception {
        if (horaFim.isBefore(horaInicio)) {
            throw new Exception("Hora fim menor que hora de início");
        }
    }
}
