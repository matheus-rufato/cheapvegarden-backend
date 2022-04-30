package com.cheapvegarden.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.TransactionScoped;

import com.cheapvegarden.repository.dao.ControleDao;
import com.cheapvegarden.repository.dto.AgendamentoDto;
import com.cheapvegarden.repository.dto.ControleDto;
import com.cheapvegarden.repository.dto.CulturaLeituraDto;
import com.cheapvegarden.repository.entity.Controle;
import com.cheapvegarden.service.converter.ControleConverter;

import lombok.*;

@Data
@ApplicationScoped
public class ControleService {

    @Inject
    ControleDao dao;

    @Inject
    ControleConverter converter;

    @Inject
    AgendamentoService agendamentoService;

    @Inject
    CulturaService culturaService;

    @Inject
    SetupService setupService;

    @Inject
    SetupSemLigacaoService setupSemLigacaoService;

    public ControleDto alterar(ControleDto controleDto) throws Exception {

        try {

            Controle controle = dao.findAll().firstResult();

            if (Objects.nonNull(controle)) {

                Boolean tipoDeControle = setupService.buscarTipoControle();

                if (Objects.nonNull(tipoDeControle)) {
                    Boolean statusSolenoideRequisicao = controleDto.getStatusSolenoide();
                    Boolean statusSolenoideLeitura = controle.getStatusSolenoide();

                    if (!(statusSolenoideRequisicao) && statusSolenoideLeitura && !(tipoDeControle)) {
                        controle.setDesabilitarAgendamento(Boolean.TRUE);
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

    public Boolean lerStatusSolenoide() throws Exception {
        try {
            Boolean statusSolenoide = dao.buscarStatusSolenoide();
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

    public void controlarIrrigacaoPorAgendamento(LocalTime hora) throws Exception {
        try {

            CulturaLeituraDto culturaDto = culturaService.buscarCulturaAtiva();

            List<AgendamentoDto> agendamentoDtoList = agendamentoService
                    .listarAgendamentosPorCultura(culturaDto.getId());

            Boolean desabilitarAgendamento = dao.buscarDesabilitarAgendamento();
            Boolean irrigacao = lerStatusSolenoide();

            Boolean iniciarIrrigacao = agendamentoDtoList
                    .stream()
                    .anyMatch((agendamento) -> hora.isAfter(agendamento.getHoraInicio())
                            && hora.isBefore(agendamento.getHoraFim()));

            if (iniciarIrrigacao && !(desabilitarAgendamento) && !(irrigacao)) {
                alterarStatus(Boolean.TRUE);
            } else if (!(iniciarIrrigacao) && !(desabilitarAgendamento) && irrigacao) {
                alterarStatus(Boolean.FALSE);
            } else if (!(iniciarIrrigacao) && desabilitarAgendamento) {
                // alterarDesalibitarAgendamento(Boolean.FALSE);
                dao.alterarDesabilitarAgendamento(Boolean.FALSE);
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    // private void alterarDesalibitarAgendamento(Boolean desabilitarAgendamento)
    // throws Exception {
    // try {
    // // Controle controle = dao.findAll().singleResult();
    // // controle.setDesabilitarAgendamento(desabilitarAgendamento);
    // // dao.persistAndFlush(controle);
    // dao.alterarDesabilitarAgendamento(desabilitarAgendamento);
    // } catch (Exception e) {
    // throw new Exception(e.getMessage(), e.getCause());
    // }
    // }

    @TransactionScoped
    private void alterarStatus(Boolean statusSolenoide) throws Exception {
        try {
            Controle controle = dao.findAll().singleResult();
            controle.setStatusSolenoide(statusSolenoide);
            dao.persistAndFlush(controle);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    private Controle atribuirValores(Controle controle, ControleDto controleDto) {
        controle.setStatusSolenoide(controleDto.getStatusSolenoide());
        controle.setUmidadeSolo(controleDto.getUmidadeSolo());
        controle.setTemperaturaClima(controleDto.getTemperaturaClima());
        controle.setUmidadeClima(controleDto.getUmidadeClima());
        return controle;
    }
}
