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
                            "SELECT controle.statusSolenoide FROM Controle AS controle",
                            Boolean.class)
                    .getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public Boolean buscarDesabilitarAgendamento() throws Exception {
        try {
            return entityManager
                    .createQuery(
                            "SELECT desabilitarAgendamento FROM Controle",
                            Boolean.class)
                    .getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void alterarDesabilitarAgendamento(Boolean desabilitarAgendamento) throws Exception {
        try {
            entityManager.createQuery(
                    "UPDATE Controle AS controle SET controle.desabilitarAgendamento = :desabilitarAgendamento WHERE controle.id = :id")
                    .setParameter("desabilitarAgendamento", desabilitarAgendamento)
                    .setParameter("id", Long.valueOf(1))
                    .executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}
