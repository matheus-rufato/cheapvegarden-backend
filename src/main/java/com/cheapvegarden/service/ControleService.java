package com.cheapvegarden.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.TransactionScoped;

import com.cheapvegarden.repository.dao.ControleDao;
import com.cheapvegarden.repository.dto.ControleDto;
import com.cheapvegarden.repository.entity.Controle;
import com.cheapvegarden.service.converter.ControleConverter;

@ApplicationScoped
public class ControleService {
    
    @Inject
    ControleDao dao;

    @Inject
    ControleConverter converter;

    @TransactionScoped
    public ControleDto salvar(ControleDto controleDto) throws Exception {
        try {
            Controle controle = converter.toEntity(controleDto);
            dao.persistAndFlush(controle);
            return converter.toDto(controle);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ControleDto buscarControlePorId(Long id) throws Exception {
        try {
            Controle controle = dao.findById(id);
            return converter.toDto(controle);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
