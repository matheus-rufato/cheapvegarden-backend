package com.cheapvegarden.repository.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.cheapvegarden.repository.entity.Cultura;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class CulturaDao implements PanacheRepositoryBase<Cultura, Long> {

    @Inject
    EntityManager entityManager;

    public Cultura buscarCulturaAtiva() throws Exception {
        try {
            Cultura cultura = entityManager
                    .createQuery(
                            "SELECT Cultura " +
                                    "FROM cultura AS Cultura " +
                                    "INNER JOIN setup AS s " +
                                    "ON Cultura.setup = s.id " +
                                    "WHERE s.status = :status",
                            Cultura.class)
                    .setParameter("status", true)
                    .getSingleResult();
            return cultura;
        } catch (Exception e) {

            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public Cultura buscarCulturaPorSetup(long setupId) throws Exception {
        try {
            Cultura cultura = entityManager
                    .createQuery(
                            "SELECT Cultura FROM cultura AS Cultura WHERE id_setup = :setupId",
                            Cultura.class)
                    .setParameter("setupId", setupId)
                    .getSingleResult();
            return cultura;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}
