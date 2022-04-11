package com.cheapvegarden.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cheapvegarden.repository.dao.AgendamentoDao;
import com.cheapvegarden.repository.dto.AgendamentoDto;
import com.cheapvegarden.repository.entity.Agendamento;
import com.cheapvegarden.service.converter.AgendamentoConverter;

@ApplicationScoped
public class AgendamentoService {
    
    @Inject
    AgendamentoDao dao;

    @Inject
    AgendamentoConverter converter;

    public AgendamentoDto salvar(AgendamentoDto agendamentoDto) throws Exception {
        try {
            Agendamento agendamento = converter.toEntity(agendamentoDto);
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

    public AgendamentoDto deletarAgendamento(Long id) throws Exception {
        try {
            Agendamento agendamento = dao.findById(id);
            dao.delete(agendamento);
            return converter.toDto(agendamento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
