package com.cheapvegarden.repository.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.repository.entity.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class UsuarioDao implements PanacheRepositoryBase<Usuario, Long> {

    public List<Usuario> buscarUsuarioPorNome(String nome) throws Exception {
        try {
            return find("nome", nome).list();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}