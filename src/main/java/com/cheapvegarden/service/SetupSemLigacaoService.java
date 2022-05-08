package com.cheapvegarden.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

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

    @Transactional
    public SetupSemLigacaoDto alterarSetupSemLigacao(SetupSemLigacaoDto setupDto) throws Exception {
        try {
            int umidadeMaxima = setupDto.getUmidadeMaxima();
            int umidadeMinima = setupDto.getUmidadeMinima();

            validacao.validarSeUmidadeMaximaEMaiorQueUmidadeMinima(umidadeMaxima, umidadeMinima);

            Setup setup = dao.buscarSetupSemLigacao();
            setup.setUmidadeMaxima(umidadeMaxima);
            setup.setUmidadeMinima(umidadeMinima);

            dao.persistAndFlush(setup);

            SetupSemLigacaoDto setupSemLigacaoDto = converter.toDto(setup);
            return setupSemLigacaoDto;
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

    public void salvarPrimeiroSetup() throws Exception {
        try {
            SetupSemLigacaoDto setupDto = new SetupSemLigacaoDto();
            setupDto.setUmidadeMaxima(60);
            setupDto.setUmidadeMinima(50);
            Setup setup = converter.toEntity(setupDto);
            dao.persistAndFlush(setup);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}
