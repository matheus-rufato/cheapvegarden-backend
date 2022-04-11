package com.cheapvegarden.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.TransactionScoped;

import com.cheapvegarden.repository.dao.SetupDao;
import com.cheapvegarden.repository.dto.SetupDto;
import com.cheapvegarden.repository.entity.Setup;
import com.cheapvegarden.service.converter.SetupConverter;

@ApplicationScoped
public class SetupService {
    
    @Inject
    SetupDao dao;

    @Inject
    SetupConverter converter;

    @TransactionScoped
    public SetupDto salvar(SetupDto setupDto) throws Exception {
        try {
            Setup setup = converter.toEntity(setupDto);
            dao.persistAndFlush(setup);
            return converter.toDto(setup);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
}
