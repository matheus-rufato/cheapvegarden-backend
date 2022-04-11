package com.cheapvegarden.repository.dao;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.repository.entity.Controle;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ControleDao implements PanacheRepositoryBase<Controle, Long> {    
}
