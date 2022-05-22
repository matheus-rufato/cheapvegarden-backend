package com.cheapvegarden.service;

import java.time.LocalTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.cheapvegarden.repository.dao.SetupDao;
import com.cheapvegarden.repository.dto.SetupDto;
import com.cheapvegarden.repository.dto.SetupSemLigacaoDto;
import com.cheapvegarden.repository.dto.UmidadesETipoControleDto;
import com.cheapvegarden.repository.entity.Setup;
import com.cheapvegarden.service.converter.SetupConverter;
import com.cheapvegarden.service.converter.SetupSemLigacaoConverter;
import com.cheapvegarden.service.converter.UmidadesETipoControleConverter;
import com.cheapvegarden.service.validator.SetupValidacao;

@ApplicationScoped
public class SetupService {

    @Inject
    SetupDao dao;

    @Inject
    SetupConverter converter;

    @Inject
    UmidadesETipoControleConverter umidadesETipoControleConverter;

    @Inject
    SetupSemLigacaoConverter setupSemLigacaoConverter;

    @Inject
    ControleService controleService;

    @Inject
    CulturaService culturaService;

    @Inject
    SetupValidacao validacao;

    @Transactional
    public SetupDto salvar(SetupDto setupDto) throws Exception {
        try {
            int umidadeMaxima = setupDto.getUmidadeMaxima();
            int umidadeMinima = setupDto.getUmidadeMinima();

            validacao.validarSeUmidadeMaximaEMaiorQueUmidadeMinima(umidadeMaxima, umidadeMinima);

            Setup setup = converter.toEntity(setupDto);

            if (setup.isStatus()) {
                alterarStatus();
            }

            dao.persistAndFlush(setup);
            return converter.toDto(setup);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public SetupDto alterar(long id, SetupDto setupDto) throws Exception {
        try {
            int umidadeMaxima = setupDto.getUmidadeMaxima();
            int umidadeMinima = setupDto.getUmidadeMinima();

            validacao.validarSeUmidadeMaximaEMaiorQueUmidadeMinima(umidadeMaxima, umidadeMinima);

            SetupDto setupListado = buscarSetupPorId(id);

            if (setupDto.isStatus() && !(setupListado.isStatus())) {
                alterarStatus();
            } else if (!(setupDto.isStatus()) && setupListado.isStatus()) {
                dao.alterarStatusDeSetupSemLigacao(Boolean.TRUE);
            }

            setupListado = atribuirValores(setupDto, setupListado);

            dao.persistAndFlush(converter.toEntity(setupListado));

            return setupListado;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public UmidadesETipoControleDto listarUmidadeMaximaMinimaETipoDeControle() throws Exception {
        try {

            UmidadesETipoControleDto umidadesETipoControleDto = new UmidadesETipoControleDto();

            Setup setup = dao.buscarSetupAtivo();
            boolean desabilitarAgendamento = controleService.buscarDesabilitarAgendamento();

            if (setup.getId() != 1l) {
                long culturaId = culturaService.buscarCulturaPorSetup(setup.getId()).getId();

                LocalTime horaAtual = LocalTime.now();

                if (!setup.isTipoControle() || desabilitarAgendamento) {
                    controleService.controlarIrrigacaoPorAgendamento(horaAtual, culturaId, desabilitarAgendamento);
                }
            } else if (desabilitarAgendamento) {
                controleService.alterarDesabilitarAgendamento(false);
            }

            umidadesETipoControleDto = umidadesETipoControleConverter.toDto(setup);
            return umidadesETipoControleDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public Object buscarSetupAtivo() throws Exception {
        try {
            Setup setup = dao.buscarSetupAtivo();

            if (setup.getId() != 1l) {
                SetupDto setupDto = converter.toDto(setup);
                return setupDto;
            } else {
                SetupSemLigacaoDto setupSemLigacaoDto = setupSemLigacaoConverter.toDto(setup);
                return setupSemLigacaoDto;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public SetupDto buscarSetupPorId(Long id) throws Exception {
        try {
            Setup setup = dao.findByIdOptional(id)
                    .orElseThrow(() -> new RuntimeException("Erro ao buscar Setup, ID: " + id + " inexistente"));
            return converter.toDto(setup);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Boolean buscarTipoControle() throws Exception {
        try {
            Boolean tipoControle = dao.buscarTipoControle();
            return tipoControle;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @Transactional
    public void deletar(Setup setup) throws Exception {
        try {
            if (setup.isStatus()) {
                dao.alterarStatusDeSetupSemLigacao(true);
            }
            dao.delete(setup);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    private SetupDto atribuirValores(SetupDto setupDto, SetupDto setupListado) throws Exception {
        try {
            setupListado.setStatus(setupDto.isStatus());
            setupListado.setTipoControle(setupDto.isTipoControle());
            setupListado.setUmidadeMaxima(setupDto.getUmidadeMaxima());
            setupListado.setUmidadeMinima(setupDto.getUmidadeMinima());
            return setupListado;

        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    private void alterarStatus() throws Exception {
        try {
            Setup setup = dao.buscarSetupAtivo();
            setup.setStatus(Boolean.FALSE);
            dao.persist(setup);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}