package com.cheapvegarden.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.TransactionScoped;

import com.cheapvegarden.repository.dao.CulturaDao;
import com.cheapvegarden.repository.dto.CulturaDto;
import com.cheapvegarden.repository.dto.CulturaLeituraDto;
import com.cheapvegarden.repository.dto.SetupDto;
import com.cheapvegarden.repository.entity.Cultura;
import com.cheapvegarden.service.converter.CulturaConverter;
import com.cheapvegarden.service.converter.CulturaLeituraConverter;
import com.cheapvegarden.service.validator.SetupValidacao;

@ApplicationScoped
public class CulturaService {

    @Inject
    CulturaDao dao;

    @Inject
    CulturaConverter converter;

    @Inject
    CulturaLeituraConverter culturaLeituraConverter;

    @Inject
    ControleService controleService;

    @Inject
    SetupService setupService;

    @Inject
    AgendamentoService agendamentoService;

    @Inject
    SetupValidacao setupValidacao;

    @TransactionScoped
    public CulturaDto salvar(CulturaDto culturaDto) throws Exception {
        try {
            SetupDto setupDto = setupService.salvar(culturaDto.getSetupDto());

            culturaDto.setSetupDto(setupDto);

            Cultura cultura = converter.toEntity(culturaDto);

            dao.persistAndFlush(cultura);

            return converter.toDto(cultura);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public CulturaLeituraDto alterarNomeCultura(long id, String nome) throws Exception {
        try {
            Cultura cultura = dao.findById(id);
            cultura.setNome(nome);
            dao.persistAndFlush(cultura);
            return culturaLeituraConverter.toDto(cultura);

        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public List<CulturaLeituraDto> listarCulturas() throws Exception {
        try {
            List<CulturaLeituraDto> culturaDtoList = new ArrayList<>();
            List<Cultura> culturas = dao.listAll();
            culturaDtoList = culturaLeituraConverter.toDtoList(culturas);
            return culturaDtoList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public CulturaLeituraDto buscarCulturaPorSetup(long setupId) throws Exception {
        try {
            setupValidacao.validarSeSetupIdExiste(setupId);
            setupValidacao.validarSeIdEDiferenteDeUm(setupId);

            Cultura cultura = dao.buscarCulturaPorSetup(setupId);
            CulturaLeituraDto culturaDto = culturaLeituraConverter.toDto(cultura);
            return culturaDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public CulturaLeituraDto buscarCulturaAtiva() throws Exception {
        try {
            Cultura cultura = dao.buscarCulturaAtiva();
            CulturaLeituraDto culturaDto = culturaLeituraConverter.toDto(cultura);
            return culturaDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void deletarCultura(long id) throws Exception {
        try {
            Cultura cultura = dao.findById(id);

            agendamentoService.deletarAgendamentosPorCultura(cultura.getId());
            dao.delete(cultura);
            setupService.deletar(cultura.getSetup());

        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}
