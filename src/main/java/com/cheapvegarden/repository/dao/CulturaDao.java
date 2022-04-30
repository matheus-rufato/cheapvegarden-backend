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
                            "SELECT cultura " +
                                    "FROM Cultura AS cultura " +
                                    "INNER JOIN Setup s " +
                                    "ON cultura.setup = s.id " +
                                    "WHERE s.status = :status",
                            Cultura.class)
                    .setParameter("status", Boolean.TRUE)
                    .getSingleResult();
            return cultura;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}
