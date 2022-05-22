package com.cheapvegarden.service;

import java.util.*;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cheapvegarden.repository.dao.UsuarioDao;
import com.cheapvegarden.repository.dto.UsuarioDto;
import com.cheapvegarden.repository.entity.Usuario;
import com.cheapvegarden.service.converter.UsuarioConverter;

import io.quarkus.elytron.security.common.BcryptUtil;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioDao dao;

    @Inject
    UsuarioConverter converter;

    public UsuarioDto salvar(UsuarioDto usuarioDto) throws Exception {

        try {
            Usuario usuario = new Usuario();
            usuario = converter.toEntity(usuarioDto);
            String senhaCriptografada = BcryptUtil.bcryptHash(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
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
            List<Usuario> usuarioList = usuarios.stream()
                    .filter(item -> BcryptUtil.matches("teste@123", item.getSenha()))
                    .collect(Collectors.toList());

            usuarioDtoList = converter.toDtoList(usuarioList);
            return usuarioDtoList;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public List<UsuarioDto> buscarUsuarioPorNome(String nome) throws Exception {
        try {
            List<UsuarioDto> usuarioDtos = new ArrayList<>();
            List<Usuario> usuarios = dao.buscarUsuarioPorNome(nome);
            usuarioDtos = converter.toDtoList(usuarios);
            return usuarioDtos;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public UsuarioDto buscarUsuarioPorId(long id) throws Exception {
        try {
            Usuario usuario = dao.findByIdOptional(id)
                    .orElseThrow(() -> new RuntimeException("Erro ao buscar usuário, Id: " + id + " inexistente"));
            UsuarioDto usuarioDto = converter.toDto(usuario);
            return usuarioDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }
}