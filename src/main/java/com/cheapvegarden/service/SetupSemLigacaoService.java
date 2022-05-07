package com.cheapvegarden.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cheapvegarden.repository.dao.SetupDao;
import com.cheapvegarden.repository.dto.SetupSemLigacaoDto;
import com.cheapvegarden.repository.entity.Setup;
import com.cheapvegarden.service.converter.SetupSemLigacaoConverter;
import com.cheapvegarden.service.validator.SetupValidacao;

@ApplicationScoped
public class SetupSemLigacaoService {

    @Inject
    SetupDao dao;

    @Inject
    SetupSemLigacaoConverter converter;

    @Inject
    SetupValidacao validacao;

    public SetupSemLigacaoDto alterarSetupSemLigacao(SetupSemLigacaoDto setupDto) throws Exception {
        try {
            int umidadeMaxima = setupDto.getUmidadeMaxima();
            int umidadeMinima = setupDto.getUmidadeMinima();

            validacao.validarSeUmidadeMaximaEMaiorQueUmidadeMinima(umidadeMaxima, umidadeMinima);

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
        SetupSemLigacaoDto setupDto = new SetupSemLigacaoDto(1, 60, 45);
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
