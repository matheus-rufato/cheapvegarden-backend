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

    public CulturaLeituraDto alterarNomeCultura(Long id, String nome) throws Exception {
        try {
            Cultura cultura = dao.findById(id);
            CulturaLeituraDto culturaLeituraDto = culturaLeituraConverter.toDto(cultura);

            culturaLeituraDto.setNome(nome);
            cultura = culturaLeituraConverter.toEntity(culturaLeituraDto);

            dao.persistAndFlush(cultura);
            return culturaLeituraDto;

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

    public CulturaLeituraDto buscarCulturaAtiva() throws Exception {
        try {
            Cultura cultura = dao.buscarCulturaAtiva();
            CulturaLeituraDto culturaDto = culturaLeituraConverter.toDto(cultura);
            return culturaDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void deletarCultura(Long id) throws Exception {
        try {
            Cultura cultura = dao.findById(id);

            agendamentoService.deletar(cultura.getId());
            dao.delete(cultura);
            setupService.deletar(cultura.getSetup());

        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}
