package com.cheapvegarden.repository.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.repository.entity.Agendamento;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class AgendamentoDao implements PanacheRepositoryBase<Agendamento, Long> {

    public List<Agendamento> buscarAgendamentosPorCulturaId(long culturaId) throws Exception {
        try {
            return list("id_cultura = ?1 ORDER BY hora_inicio", culturaId);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void deletarAgendamentosDeUmaCultura(long culturaId) throws Exception {
        try {
            delete("id_cultura", culturaId);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}