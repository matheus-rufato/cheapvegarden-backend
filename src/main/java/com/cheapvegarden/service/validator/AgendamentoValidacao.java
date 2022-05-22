package com.cheapvegarden.service.validator;

import java.time.LocalTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.repository.dto.AgendamentoDto;

@ApplicationScoped
public class AgendamentoValidacao {

    public void validarHoraFimDoAgendamento(LocalTime horaInicio, LocalTime horaFim) {
        if (!(horaFim.isAfter(horaInicio))) {
            throw new RuntimeException("Hora fim igual ou menor que hora de início");
        }
    }

    public void validarSeAgendamentoEMaiorQueDuasHoras(LocalTime horaInicio, LocalTime horaFim) {
        LocalTime horaAgendada = horaFim.minusHours(2);

        if (horaAgendada.isAfter(horaInicio)) {
            throw new RuntimeException("Agendamento maior do que 2 horas");
        }
    }

    public void validarIntervaloEntreAgendamento(
            LocalTime horaInicio,
            LocalTime horaFim,
            List<AgendamentoDto> agendamentos) {

        for (AgendamentoDto agendamento : agendamentos) {
            LocalTime horaInicioMenos29 = agendamento.getHoraInicio().minusMinutes(29);
            LocalTime horaFimMais29 = agendamento.getHoraFim().plusMinutes(29);
            if (!((horaInicio.isAfter(horaFimMais29)) != (horaFim.isBefore(horaInicioMenos29)))) {
                throw new RuntimeException("Intervalo menor do que 30min no agendamento");
            }
        }
    }
}
