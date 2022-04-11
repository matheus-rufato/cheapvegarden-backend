package com.cheapvegarden.service.converter;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.common.Converter;
import com.cheapvegarden.repository.dto.UsuarioDto;
import com.cheapvegarden.repository.entity.Usuario;

@ApplicationScoped
public class UsuarioConverter implements Converter<Usuario, UsuarioDto> {

    @Override
    public UsuarioDto toDto(Usuario entity) {
        return UsuarioDto.builder()
        .id(entity.getId())
        .nome(entity.getNome())
        .telefone(entity.getTelefone())
        .email(entity.getEmail())
        .senha(entity.getSenha())
        .build();
    }

    @Override
    public Usuario toEntity(UsuarioDto dto) throws IllegalAccessException {
        return Usuario.builder()
        .id(dto.getId())
        .nome(dto.getNome())
        .telefone(dto.getTelefone())
        .email(dto.getEmail())
        .senha(dto.getSenha())
        .build();
    }

    @Override
    public List<UsuarioDto> toDtoList(List<Usuario> entityList) {
        List<UsuarioDto> usuarioDtoList = new ArrayList<>();
        entityList.forEach(item -> usuarioDtoList.add(toDto(item)));
        return usuarioDtoList;
    }

}
