package com.cheapvegarden.repository.dao;

import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.cheapvegarden.repository.entity.Setup;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class SetupDao implements PanacheRepositoryBase<Setup, Long> {

    @Inject
    EntityManager entityManager;

    public Setup buscarSetupAtivo() throws Exception {
        try {
            return entityManager
                    .createQuery("SELECT setup FROM Setup AS setup WHERE Status = :status", Setup.class)
                    .setParameter("status", Boolean.TRUE)
                    .getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public Boolean buscarTipoControle() throws Exception {
        try {
            return entityManager
                    .createQuery(
                            "SELECT tipoControle FROM Setup WHERE Status = :status",
                            Boolean.class)
                    .setParameter("status", Boolean.TRUE)
                    .getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public Setup buscarSetupSemLigacao() throws Exception {
        try {
            return entityManager
                    .createQuery("SELECT setup " +
                            "FROM Setup AS setup " +
                            "WHERE id " +
                            "NOT IN (SELECT cultura.setup FROM Cultura AS cultura)",
                            Setup.class)
                    .getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void alterarStatusDeSetupSemLigacao(Boolean status) throws SQLException {
        try {
            entityManager.createQuery(
                    "UPDATE Setup AS setup SET setup.status = :status WHERE setup.id = :id")
                    .setParameter("status", status)
                    .setParameter("id", Long.valueOf(1))
                    .executeUpdate();
        } catch (Exception e) {
            throw new SQLException(e.getMessage(), e.getCause());
        }
    }
}
