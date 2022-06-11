package com.cheapvegarden.repository.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.repository.entity.Log;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class LogDao implements PanacheRepositoryBase<Log, Long> {

    public List<Log> buscarUltimosDozeRegistro(int quantidade) throws Exception {
        try {
            return find("ORDER BY id DESC").range(0, (quantidade - 1)).list();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}