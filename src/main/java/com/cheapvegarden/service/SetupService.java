package com.cheapvegarden.service;

import java.time.LocalTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.TransactionScoped;

import com.cheapvegarden.repository.dao.SetupDao;
import com.cheapvegarden.repository.dto.SetupDto;
import com.cheapvegarden.repository.dto.UmidadesETipoControleDto;
import com.cheapvegarden.repository.entity.Setup;
import com.cheapvegarden.service.converter.SetupConverter;
import com.cheapvegarden.service.converter.UmidadesETipoControleConverter;

@ApplicationScoped
public class SetupService {

    @Inject
    SetupDao dao;

    @Inject
    SetupConverter converter;

    @Inject
    UmidadesETipoControleConverter umidadesETipoControleConverter;

    @Inject
    ControleService controleService;

    @Inject
    CulturaService culturaService;

    @TransactionScoped
    public SetupDto salvar(SetupDto setupDto) throws Exception {
        try {
            Setup setup = converter.toEntity(setupDto);

            if (setup.getStatus().booleanValue()) {
                alterarStatus();
            }

            dao.persistAndFlush(setup);
            return converter.toDto(setup);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public SetupDto alterar(Long id, SetupDto setupDto) throws Exception {
        try {
            Setup setup = dao.findById(id);
            SetupDto setupListado = converter.toDto(setup);

            if (setupDto.getStatus().booleanValue() && !(setupListado.getStatus().booleanValue())) {
                alterarStatus();
            } else if (!(setupDto.getStatus().booleanValue()) && setupListado.getStatus().booleanValue()) {
                dao.alterarStatusDeSetupSemLigacao(Boolean.TRUE);
            }

            setupListado = atribuirValores(setupDto, setupListado);
            setup = converter.toEntity(setupListado);

            dao.persistAndFlush(setup);

            return setupListado;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public UmidadesETipoControleDto listarUmidadeMaximaMinimaETipoDeControle() throws Exception {
        try {
            UmidadesETipoControleDto umidadesETipoControleDto = new UmidadesETipoControleDto();
            umidadesETipoControleDto = umidadesETipoControleConverter.toDto(dao.buscarSetupAtivo());
            if (!umidadesETipoControleDto.getTipoControle().booleanValue()) {
                controleService.controlarIrrigacaoPorAgendamento(LocalTime.now());
            }
            return umidadesETipoControleDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public SetupDto buscarSetupPorId(Long id) throws Exception {
        try {
            Setup setup = dao.findById(id);
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

    @TransactionScoped
    public void deletar(Setup setup) throws Exception {
        try {
            if (setup.getStatus()) {
                dao.alterarStatusDeSetupSemLigacao(Boolean.TRUE);
            }
            dao.delete(setup);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    private SetupDto atribuirValores(SetupDto setupDto, SetupDto setupListado) throws Exception {
        try {
            setupListado.setStatus(setupDto.getStatus());
            setupListado.setTipoControle(setupDto.getTipoControle());
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
