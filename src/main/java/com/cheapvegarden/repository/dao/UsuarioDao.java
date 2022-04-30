package com.cheapvegarden.repository.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.cheapvegarden.repository.entity.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class UsuarioDao implements PanacheRepositoryBase<Usuario, Long> {

    @Inject
    EntityManager entityManager;

    public List<Usuario> buscarUsuarioPorNome(String nome) throws Exception {
        try {
            return entityManager
                    .createQuery(
                            "SELECT usuario FROM Usuario AS usuario WHERE Nome = :nome",
                            Usuario.class)
                    .setParameter("nome", nome)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}
