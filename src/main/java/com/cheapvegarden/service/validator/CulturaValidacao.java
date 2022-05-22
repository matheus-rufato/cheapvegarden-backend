package com.cheapvegarden.service.validator;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cheapvegarden.repository.dao.CulturaDao;
import com.cheapvegarden.repository.entity.Cultura;

@ApplicationScoped
public class CulturaValidacao {

    @Inject
    CulturaDao dao;

    public void validarSeIdDaCulturaExiste(long culturaId) throws Exception {
        List<Cultura> culturas = dao.listAll();

        if (culturas.stream().noneMatch(cultura -> cultura.getId() == culturaId)) {
            throw new Exception("Id de cultura inexistente");
        }
    }
}