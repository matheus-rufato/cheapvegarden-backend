package com.cheapvegarden.service;

import java.util.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cheapvegarden.repository.dao.UsuarioDao;
import com.cheapvegarden.repository.dto.UsuarioDto;
import com.cheapvegarden.repository.entity.Usuario;
import com.cheapvegarden.service.converter.UsuarioConverter;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioDao dao;

    @Inject
    UsuarioConverter converter;

    public UsuarioDto salvar(UsuarioDto usuarioDto) throws Exception {

        try {
            Usuario usuario = converter.toEntity(usuarioDto);
            dao.persistAndFlush(usuario);
            return converter.toDto(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public List<UsuarioDto> listarUsuarios() throws Exception {
        try {
            List<UsuarioDto> usuarioDtoList = new ArrayList<>();
            List<Usuario> usuarios = dao.listAll();
            usuarioDtoList = converter.toDtoList(usuarios);
            return usuarioDtoList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<UsuarioDto> buscarUsuarioPorNome(String nome) throws Exception {
        try {
            List<UsuarioDto> usuarioDtos = new ArrayList<>();
            List<Usuario> usuarios = dao.buscarUsuarioPorNome(nome);
            usuarioDtos = converter.toDtoList(usuarios);
            return usuarioDtos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UsuarioDto buscarUsuarioPorId(long id) throws Exception {
        try {
            Usuario usuario = dao.findById(id);
            UsuarioDto usuarioDto = converter.toDto(usuario);
            return usuarioDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
