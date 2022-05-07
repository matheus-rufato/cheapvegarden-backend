package com.cheapvegarden.service;

import java.time.LocalTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.TransactionScoped;

import com.cheapvegarden.repository.dao.AgendamentoDao;
import com.cheapvegarden.repository.dto.AgendamentoDto;
import com.cheapvegarden.repository.entity.Agendamento;
import com.cheapvegarden.service.converter.AgendamentoConverter;
import com.cheapvegarden.service.validator.AgendamentoValidacao;
import com.cheapvegarden.service.validator.CulturaValidacao;

@ApplicationScoped
public class AgendamentoService {

    @Inject
    AgendamentoDao dao;

    @Inject
    AgendamentoConverter converter;

    @Inject
    AgendamentoValidacao validacao;

    @Inject
    CulturaValidacao culturaValidacao;

    public AgendamentoDto salvar(AgendamentoDto agendamentoDto) throws Exception {
        try {
            LocalTime horaInicio = agendamentoDto.getHoraInicio();
            LocalTime horaFim = agendamentoDto.getHoraFim();
            List<AgendamentoDto> agendamentos = listarAgendamentosPorCultura(agendamentoDto.getCulturaId());

            validacao.validarHoraFimDoAgendamento(horaInicio, horaFim);

            if (!agendamentos.isEmpty()) {
                validacao.validarIntervaloEntreAgendamento(horaInicio, horaFim, agendamentos);
            }

            Agendamento agendamento = converter.toEntity(agendamentoDto);
            dao.persistAndFlush(agendamento);
            return converter.toDto(agendamento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<AgendamentoDto> listarAgendamentosPorCultura(long culturaId) throws Exception {
        try {
            culturaValidacao.validarSeIdDaCulturaExiste(culturaId);

            List<Agendamento> agendamentoListados = dao.buscarAgendamentosPorCulturaId(culturaId);
            List<AgendamentoDto> agendamentoDtoList = converter.toDtoList(agendamentoListados);
            return agendamentoDtoList;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public AgendamentoDto deletarAgendamento(long id) throws Exception {
        try {
            Agendamento agendamento = dao.findById(id);
            dao.delete(agendamento);
            return converter.toDto(agendamento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @TransactionScoped
    public void deletarAgendamentosPorCultura(long culturaId) throws Exception {
        try {
            dao.deletarAgendamentosDeUmaCultura(culturaId);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}
