package com.cheapvegarden.repository.dao;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.repository.entity.Setup;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class SetupDao implements PanacheRepositoryBase<Setup, Long> {

    public Setup buscarSetupAtivo() throws Exception {
        try {
            return find("status", true).singleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public boolean buscarTipoControle() throws Exception {
        try {
            return find("status", true).singleResult().isTipoControle();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public Setup buscarSetupSemLigacao() throws Exception {
        try {
            return find(
                    "FROM setup AS Setup WHERE Setup.id NOT IN (SELECT Cultura.setup FROM cultura AS Cultura)")
                    .singleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void alterarStatusDeSetupSemLigacao(boolean status) throws Exception {
        try {
            update("status = ?1 WHERE id = ?2", status, 1l);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}