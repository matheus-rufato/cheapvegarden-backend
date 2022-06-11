package com.cheapvegarden.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.cheapvegarden.repository.dao.ControleDao;
import com.cheapvegarden.repository.dto.AgendamentoDto;
import com.cheapvegarden.repository.dto.ControleDto;
import com.cheapvegarden.repository.entity.Controle;
import com.cheapvegarden.service.converter.ControleConverter;
import com.cheapvegarden.service.validator.SetupValidacao;

@ApplicationScoped
public class ControleService {

    @Inject
    ControleDao dao;

    @Inject
    ControleConverter converter;

    @Inject
    AgendamentoService agendamentoService;

    @Inject
    SetupService setupService;

    @Inject
    SetupValidacao setupValidacao;

    public ControleDto alterar(ControleDto controleDto) throws Exception {

        try {

            Controle controle = dao.findAll().firstResult();

            if (Objects.nonNull(controle)) {
                controle = atribuirValores(controle, controleDto);
            } else {
                controle = converter.toEntity(controleDto);
                setupService.salvarPrimeiroSetup();
            }
            dao.persistAndFlush(controle);
            return converter.toDto(controle);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public boolean lerStatusSolenoide() throws Exception {
        try {
            boolean statusSolenoide = dao.findAll().singleResult().isStatusSolenoide();
            return statusSolenoide;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public ControleDto lerControle() throws Exception {
        try {
            Controle controle = dao.findAll().firstResult();
            ControleDto controleDto = converter.toDto(controle);
            return controleDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }

    }

    public void controlarIrrigacaoPorAgendamento(
            LocalTime horaAtual,
            long culturaId) throws Exception {

        try {

            List<AgendamentoDto> agendamentoDtoList = agendamentoService
                    .listarAgendamentosPorCultura(culturaId);

            boolean irrigacao = lerStatusSolenoide();

            boolean iniciarIrrigacao = agendamentoDtoList
                    .stream()
                    .anyMatch((agendamento) -> horaAtual.isAfter(agendamento.getHoraInicio())
                            && horaAtual.isBefore(agendamento.getHoraFim()));

            if (iniciarIrrigacao && !(irrigacao)) {
                alterarStatusSolenoide(true);
            } else if (!(iniciarIrrigacao) && irrigacao) {
                alterarStatusSolenoide(false);
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @Transactional
    public void alterarStatusSolenoide(boolean statusSolenoide) throws Exception {
        try {
            setupValidacao.validarSeTipoControleEstaFalso();
            dao.alterarStatusSolenoide(statusSolenoide);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    private Controle atribuirValores(Controle controle, ControleDto controleDto) {
        controle.setStatusSolenoide(controleDto.isStatusSolenoide());
        controle.setUmidadeSolo(controleDto.getUmidadeSolo());
        controle.setTemperaturaClima(controleDto.getTemperaturaClima());
        controle.setUmidadeClima(controleDto.getUmidadeClima());
        return controle;
    }
}