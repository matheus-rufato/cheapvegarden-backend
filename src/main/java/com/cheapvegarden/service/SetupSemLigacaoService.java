package com.cheapvegarden.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cheapvegarden.repository.dao.SetupDao;
import com.cheapvegarden.repository.dto.SetupSemLigacaoDto;
import com.cheapvegarden.repository.entity.Setup;
import com.cheapvegarden.service.converter.SetupSemLigacaoConverter;

@ApplicationScoped
public class SetupSemLigacaoService {

    @Inject
    SetupDao dao;

    @Inject
    SetupSemLigacaoConverter converter;

    public SetupSemLigacaoDto alterarSetupSemLigacao(SetupSemLigacaoDto setupDto) throws Exception {
        try {
            Setup setup = dao.buscarSetupSemLigacao();

            SetupSemLigacaoDto setupListado = converter.toDto(setup);

            setupListado = atribuirValores(setupDto, setupListado);

            setup = converter.toEntity(setupListado);

            dao.persistAndFlush(setup);

            return setupListado;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public SetupSemLigacaoDto lerSetupSemLigacao() throws Exception {
        try {
            Setup setup = dao.buscarSetupSemLigacao();
            SetupSemLigacaoDto setupDto = converter.toDto(setup);
            return setupDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void salvarPrimeiroSetup() throws IllegalAccessException {
        SetupSemLigacaoDto setupDto = new SetupSemLigacaoDto(null, 60, 45);
        Setup setup = converter.toEntity(setupDto);
        dao.persistAndFlush(setup);
    }

    private SetupSemLigacaoDto atribuirValores(SetupSemLigacaoDto setupDto, SetupSemLigacaoDto setupListado)
            throws Exception {
        try {
            setupListado.setUmidadeMaxima(setupDto.getUmidadeMaxima());
            setupListado.setUmidadeMinima(setupDto.getUmidadeMinima());
            return setupListado;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}
