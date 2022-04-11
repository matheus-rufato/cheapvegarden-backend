package com.cheapvegarden.service.converter;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.common.Converter;
import com.cheapvegarden.repository.dto.SetupDto;
import com.cheapvegarden.repository.entity.Setup;

@ApplicationScoped
public class SetupConverter implements Converter<Setup, SetupDto> {

    @Override
    public SetupDto toDto(Setup entity) {
        return SetupDto.builder()
        .id(entity.getId())
        .status(entity.getStatus())
        .tipoControle(entity.getTipoControle())
        .build();
    }

    @Override
    public Setup toEntity(SetupDto dto) throws IllegalAccessException {
        return Setup.builder()
        .id(dto.getId())
        .status(dto.getStatus())
        .tipoControle(Boolean.FALSE)
        .build();
    }

    @Override
    public List<SetupDto> toDtoList(List<Setup> entityList) {
        List<SetupDto> setupDtoList = new ArrayList<>();
        entityList.forEach(entity -> setupDtoList.add(toDto(entity)));
        return setupDtoList;
    }
    
}
