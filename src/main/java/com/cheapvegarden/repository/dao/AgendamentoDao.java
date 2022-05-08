package com.cheapvegarden.repository.dao;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.cheapvegarden.repository.entity.Agendamento;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class AgendamentoDao implements PanacheRepositoryBase<Agendamento, Long> {

    @Inject
    EntityManager entityManager;

    public List<Agendamento> buscarAgendamentosPorCulturaId(long culturaId) throws Exception {
        try {
            return entityManager
                    .createQuery(
                            "SELECT Agendamento FROM agendamento AS Agendamento WHERE id_cultura = :culturaId ORDER BY hora_inicio",
                            Agendamento.class)
                    .setParameter("culturaId", culturaId)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void deletarAgendamentosDeUmaCultura(long culturaId) throws SQLException {
        try {
            entityManager.createQuery(
                    "DELETE FROM agendamento WHERE agendamento.cultura.id = :culturaId")
                    .setParameter("culturaId", culturaId)
                    .executeUpdate();
        } catch (Exception e) {
            throw new SQLException(e.getMessage(), e.getCause());
        }
    }
}
