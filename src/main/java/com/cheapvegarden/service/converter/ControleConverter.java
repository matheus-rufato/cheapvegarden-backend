package com.cheapvegarden.service.converter;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.common.Converter;
import com.cheapvegarden.repository.dto.ControleDto;
import com.cheapvegarden.repository.entity.Controle;

@ApplicationScoped
public class ControleConverter implements Converter<Controle, ControleDto> {

    @Override
    public ControleDto toDto(Controle entity) {
        return ControleDto.builder()
        .id(entity.getId())
        .umidadeMaxima(entity.getUmidadeMaxima())
        .umidadeMinima(entity.getUmidadeMinima())
        .build();
    }

    @Override
    public Controle toEntity(ControleDto dto) throws IllegalAccessException {
        return Controle.builder()
        .id(dto.getId())
        .umidadeMaxima(dto.getUmidadeMaxima())
        .umidadeMinima(dto.getUmidadeMinima())
        .build();
    }

    @Override
    public List<ControleDto> toDtoList(List<Controle> entityList) {
        List<ControleDto> controleDtoList = new ArrayList<>();
        entityList.forEach(entity -> controleDtoList.add(toDto(entity)));
        return controleDtoList;
    }
    
}
