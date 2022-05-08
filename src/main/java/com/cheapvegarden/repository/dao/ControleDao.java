package com.cheapvegarden.repository.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.cheapvegarden.repository.entity.Controle;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ControleDao implements PanacheRepositoryBase<Controle, Long> {

    @Inject
    EntityManager entityManager;

    public Boolean buscarStatusSolenoide() throws Exception {
        try {
            return entityManager
                    .createQuery(
                            "SELECT Controle.statusSolenoide FROM controle AS Controle",
                            Boolean.class)
                    .getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public boolean buscarDesabilitarAgendamento() throws Exception {
        try {
            return entityManager
                    .createQuery(
                            "SELECT desabilitarAgendamento FROM controle",
                            Boolean.class)
                    .getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void alterarDesabilitarAgendamento(boolean desabilitarAgendamento) throws Exception {
        try {
            entityManager
                    .createQuery(
                            "UPDATE controle AS Controle " +
                                    "SET Controle.desabilitarAgendamento = :desabilitarAgendamento " +
                                    "WHERE Controle.id = :id")
                    .setParameter("desabilitarAgendamento", desabilitarAgendamento)
                    .setParameter("id", 1l)
                    .executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}
