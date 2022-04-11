package com.cheapvegarden.repository.dao;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.repository.entity.Cultura;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class CulturaDao implements PanacheRepositoryBase<Cultura, Long> {    
}
