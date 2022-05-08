package com.cheapvegarden.service.converter;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.common.Converter;
import com.cheapvegarden.repository.dto.UmidadesETipoControleDto;
import com.cheapvegarden.repository.entity.Setup;

@ApplicationScoped
public class UmidadesETipoControleConverter implements Converter<Setup, UmidadesETipoControleDto> {

    @Override
    public UmidadesETipoControleDto toDto(Setup entity) throws Exception {
        return UmidadesETipoControleDto.builder()
                .tipoControle(entity.isTipoControle())
                .umidadeMaxima(entity.getUmidadeMaxima())
                .umidadeMinima(entity.getUmidadeMinima())
                .build();
    }

    @Override
    public Setup toEntity(UmidadesETipoControleDto dto) throws IllegalAccessException {
        return null;
    }

    @Override
    public List<UmidadesETipoControleDto> toDtoList(List<Setup> entityList) throws Exception {
        return null;
    }
}
