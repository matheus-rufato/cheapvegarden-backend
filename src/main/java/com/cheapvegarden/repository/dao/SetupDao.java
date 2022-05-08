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
                    .createQuery(
                            "SELECT Setup FROM setup AS Setup WHERE status = :status",
                            Setup.class)
                    .setParameter("status", true)
                    .getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public boolean buscarTipoControle() throws Exception {
        try {
            return entityManager
                    .createQuery(
                            "SELECT tipoControle FROM setup WHERE status = :status",
                            Boolean.class)
                    .setParameter("status", true)
                    .getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public Setup buscarSetupSemLigacao() throws Exception {
        try {
            return entityManager
                    .createQuery("SELECT Setup " +
                            "FROM setup AS Setup " +
                            "WHERE id " +
                            "NOT IN (SELECT Cultura.setup FROM cultura AS Cultura)",
                            Setup.class)
                    .getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void alterarStatusDeSetupSemLigacao(boolean status) throws SQLException {
        try {
            entityManager.createQuery(
                    "UPDATE setup SET status = :status WHERE id = :id")
                    .setParameter("status", status)
                    .setParameter("id", 1l)
                    .executeUpdate();
        } catch (Exception e) {
            throw new SQLException(e.getMessage(), e.getCause());
        }
    }
}
