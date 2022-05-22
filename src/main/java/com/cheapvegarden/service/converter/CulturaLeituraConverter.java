package com.cheapvegarden.service.converter;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.common.Converter;
import com.cheapvegarden.repository.dto.CulturaLeituraDto;
import com.cheapvegarden.repository.entity.Controle;
import com.cheapvegarden.repository.entity.Cultura;
import com.cheapvegarden.repository.entity.Setup;

@ApplicationScoped
public class CulturaLeituraConverter implements Converter<Cultura, CulturaLeituraDto> {

    @Override
    public CulturaLeituraDto toDto(Cultura entity) {
        return CulturaLeituraDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .controleId(entity.getControle().getId())
                .setupId(entity.getSetup().getId())
                .build();
    }

    @Override
    public Cultura toEntity(CulturaLeituraDto dto) throws IllegalAccessException {
        return Cultura.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .controle(Controle.builder().id(dto.getControleId()).build())
                .setup(Setup.builder().id(dto.getSetupId()).build())
                .build();
    }

    @Override
    public List<CulturaLeituraDto> toDtoList(List<Cultura> entityList) throws Exception {
        List<CulturaLeituraDto> culturaLeituraDtoList = new ArrayList<>();
        entityList.forEach(entity -> culturaLeituraDtoList.add(toDto(entity)));
        return culturaLeituraDtoList;
    }
}