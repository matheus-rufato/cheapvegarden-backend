package com.cheapvegarden.service.converter;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cheapvegarden.common.Converter;
import com.cheapvegarden.repository.dto.CulturaDto;
import com.cheapvegarden.repository.entity.Controle;
import com.cheapvegarden.repository.entity.Cultura;
import com.cheapvegarden.repository.entity.Setup;
import com.cheapvegarden.service.SetupService;

@ApplicationScoped
public class CulturaConverter implements Converter<Cultura, CulturaDto> {

    @Inject
    SetupService setupService;

    @Override
    public CulturaDto toDto(Cultura entity) throws Exception {
        return CulturaDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .controleId(entity.getControle().getId())
                .setupDto(setupService.buscarSetupPorId(entity.getSetup().getId()))
                .build();
    }

    @Override
    public Cultura toEntity(CulturaDto dto) throws IllegalAccessException {
        return Cultura.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .controle(Controle.builder().id(dto.getControleId()).build())
                .setup(Setup.builder().id(dto.getSetupDto().getId()).build())
                .build();
    }

    @Override
    public List<CulturaDto> toDtoList(List<Cultura> entityList) throws Exception {
        List<CulturaDto> culturaDtoList = new ArrayList<>();
        entityList.forEach(entity -> {
            try {
                culturaDtoList.add(toDto(entity));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return culturaDtoList;
    }
}
