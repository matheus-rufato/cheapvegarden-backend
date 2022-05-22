package com.cheapvegarden.repository.dao;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.repository.entity.Cultura;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class CulturaDao implements PanacheRepositoryBase<Cultura, Long> {

    public Cultura buscarCulturaPorSetup(long setupId) throws Exception {
        try {
            Cultura cultura = find(
                    "FROM cultura AS Cultura WHERE id_setup = ?1",
                    setupId)
                    .singleResult();
            return cultura;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}