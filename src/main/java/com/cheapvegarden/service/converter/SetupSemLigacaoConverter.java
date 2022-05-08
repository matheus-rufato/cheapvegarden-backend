package com.cheapvegarden.service.converter;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.common.Converter;
import com.cheapvegarden.repository.dto.SetupSemLigacaoDto;
import com.cheapvegarden.repository.entity.Setup;

@ApplicationScoped
public class SetupSemLigacaoConverter implements Converter<Setup, SetupSemLigacaoDto> {

    @Override
    public SetupSemLigacaoDto toDto(Setup entity) {
        return SetupSemLigacaoDto.builder()
                .id(entity.getId())
                .umidadeMaxima(entity.getUmidadeMaxima())
                .umidadeMinima(entity.getUmidadeMinima())
                .build();
    }

    @Override
    public Setup toEntity(SetupSemLigacaoDto dto) throws IllegalAccessException {
        return Setup.builder()
                .id(dto.getId())
                .umidadeMaxima(dto.getUmidadeMaxima())
                .umidadeMinima(dto.getUmidadeMinima())
                .tipoControle(true)
                .status(true)
                .build();
    }

    @Override
    public List<SetupSemLigacaoDto> toDtoList(List<Setup> entityList) throws Exception {
        return null;
    }

}
