package com.cheapvegarden.service.validator;

import java.time.LocalTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.repository.dto.AgendamentoDto;

@ApplicationScoped
public class AgendamentoValidacao {

    public void validarHoraFimDoAgendamento(LocalTime horaInicio, LocalTime horaFim) throws Exception {
        if (horaFim.isBefore(horaInicio)) {
            throw new Exception("Hora fim menor que hora de início");
        }
    }

    public void validarIntervaloEntreAgendamento(LocalTime horaInicio, LocalTime horaFim,
            List<AgendamentoDto> agendamentos) throws Exception {

        for (AgendamentoDto agendamento : agendamentos) {
            LocalTime horaInicioMenos29 = agendamento.getHoraInicio().minusMinutes(29);
            LocalTime horaFimMais29 = agendamento.getHoraFim().plusMinutes(29);
            if (!((horaInicio.isAfter(horaFimMais29)) != (horaFim
                    .isBefore(horaInicioMenos29)))) {
                throw new Exception("Intervalo menor do que 30min no agendamento");
            }
        }
    }
}
