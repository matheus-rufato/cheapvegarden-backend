package com.cheapvegarden.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import com.cheapvegarden.repository.dao.ControleDao;
import com.cheapvegarden.repository.dto.AgendamentoDto;
import com.cheapvegarden.repository.dto.ControleDto;
import com.cheapvegarden.repository.entity.Controle;
import com.cheapvegarden.service.converter.ControleConverter;

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
    SetupSemLigacaoService setupSemLigacaoService;

    public ControleDto alterar(ControleDto controleDto) throws Exception {

        try {

            Controle controle = dao.findAll().firstResult();

            if (Objects.nonNull(controle)) {

                boolean tipoDeControle = setupService.buscarTipoControle();

                if (Objects.nonNull(tipoDeControle)) {
                    boolean statusSolenoideRequisicao = controleDto.isStatusSolenoide();
                    boolean statusSolenoideLeitura = controle.isStatusSolenoide();

                    if (!(statusSolenoideRequisicao) && statusSolenoideLeitura && !(tipoDeControle)) {
                        controle.setDesabilitarAgendamento(true);
                    }
                }
                controle = atribuirValores(controle, controleDto);
            } else {
                controle = converter.toEntity(controleDto);
                setupSemLigacaoService.salvarPrimeiroSetup();
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
            long culturaId,
            boolean desabilitarAgendamento) throws Exception {

        try {

            List<AgendamentoDto> agendamentoDtoList = agendamentoService
                    .listarAgendamentosPorCultura(culturaId);

            boolean irrigacao = lerStatusSolenoide();

            boolean iniciarIrrigacao = agendamentoDtoList
                    .stream()
                    .anyMatch((agendamento) -> horaAtual.isAfter(agendamento.getHoraInicio())
                            && horaAtual.isBefore(agendamento.getHoraFim()));

            if (iniciarIrrigacao && !(desabilitarAgendamento) && !(irrigacao)) {
                alterarStatus(true);
            } else if (!(iniciarIrrigacao) && !(desabilitarAgendamento) && irrigacao) {
                alterarStatus(false);
            } else if (!(iniciarIrrigacao) && desabilitarAgendamento) {
                alterarDesabilitarAgendamento(false);
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public boolean buscarDesabilitarAgendamento() {
        try {
            boolean desabilitarAgendamento = dao.findAll().singleResult().isDesabilitarAgendamento();
            return desabilitarAgendamento;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Transactional
    public void alterarDesabilitarAgendamento(boolean desabilitarAgendamento) throws Exception {
        try {
            dao.alterarDesabilitarAgendamento(desabilitarAgendamento);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @TransactionScoped
    private void alterarStatus(boolean statusSolenoide) throws Exception {
        try {
            Controle controle = dao.findAll().singleResult();
            controle.setStatusSolenoide(statusSolenoide);
            dao.persistAndFlush(controle);
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