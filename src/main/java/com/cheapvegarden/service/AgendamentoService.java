package com.cheapvegarden.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.TransactionScoped;

import com.cheapvegarden.repository.dao.AgendamentoDao;
import com.cheapvegarden.repository.dto.AgendamentoDto;
import com.cheapvegarden.repository.entity.Agendamento;
import com.cheapvegarden.service.converter.AgendamentoConverter;
import com.cheapvegarden.service.validator.AgendamentoValidacao;

@ApplicationScoped
public class AgendamentoService {

    @Inject
    AgendamentoDao dao;

    @Inject
    AgendamentoConverter converter;

    @Inject
    AgendamentoValidacao validacao;

    public AgendamentoDto salvar(AgendamentoDto agendamentoDto) throws Exception {
        try {
            Agendamento agendamento = converter.toEntity(agendamentoDto);
            LocalTime horaInicio = agendamento.getHoraInicio();
            LocalTime horaFim = agendamento.getHoraFim();
            validacao.validarHoraFimDoAgendamento(horaInicio, horaFim);
            dao.persistAndFlush(agendamento);
            return converter.toDto(agendamento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<AgendamentoDto> listarAgendamentos() throws Exception {
        try {
            List<AgendamentoDto> agendamentoDtoList = new ArrayList<>();
            List<Agendamento> agendamentos = dao.listAll();
            agendamentoDtoList = converter.toDtoList(agendamentos);
            return agendamentoDtoList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<AgendamentoDto> listarAgendamentosPorCultura(Long culturaId) throws Exception {
        try {
            List<Agendamento> agendamentoListados = dao.buscarAgendamentosPorCulturaId(culturaId);
            List<AgendamentoDto> agendamentoDtoList = converter.toDtoList(agendamentoListados);
            return agendamentoDtoList;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public AgendamentoDto deletarAgendamento(Long id) throws Exception {
        try {
            Agendamento agendamento = dao.findById(id);
            dao.delete(agendamento);
            return converter.toDto(agendamento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @TransactionScoped
    public void deletar(Long culturaId) throws Exception {
        try {
            dao.deletarAgendamentosDeUmaCultura(culturaId);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}
