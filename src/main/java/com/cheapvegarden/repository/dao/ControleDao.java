package com.cheapvegarden.repository.dao;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.repository.entity.Controle;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ControleDao implements PanacheRepositoryBase<Controle, Long> {

    public void alterarDesabilitarAgendamento(boolean desabilitarAgendamento) throws Exception {
        try {
            update(
                    "desabilitarAgendamento = ?1 WHERE id = ?2",
                    desabilitarAgendamento, 1l);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void alterarStatusSolenoide(boolean statusSolenoide) throws Exception {
        try {
            update("statusSolenoide = ?1", statusSolenoide);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}