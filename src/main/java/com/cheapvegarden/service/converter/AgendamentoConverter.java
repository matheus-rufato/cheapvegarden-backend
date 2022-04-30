package com.cheapvegarden.service.converter;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.common.Converter;
import com.cheapvegarden.repository.dto.AgendamentoDto;
import com.cheapvegarden.repository.entity.Agendamento;
import com.cheapvegarden.repository.entity.Cultura;

@ApplicationScoped
public class AgendamentoConverter implements Converter<Agendamento, AgendamentoDto> {

    @Override
    public AgendamentoDto toDto(Agendamento entity) {
        return AgendamentoDto.builder()
                .id(entity.getId())
                .culturaId(entity.getCultura().getId())
                .horaInicio(entity.getHoraInicio())
                .horaFim(entity.getHoraFim())
                .build();
    }

    @Override
    public Agendamento toEntity(AgendamentoDto dto) throws IllegalAccessException {
        return Agendamento.builder()
                .id(dto.getId())
                .cultura(Cultura.builder().id(dto.getCulturaId()).build())
                .horaInicio(dto.getHoraInicio())
                .horaFim(dto.getHoraFim())
                .build();
    }

    @Override
    public List<AgendamentoDto> toDtoList(List<Agendamento> entityList) {
        List<AgendamentoDto> agendamentoDtoList = new ArrayList<>();
        entityList.forEach(entity -> agendamentoDtoList.add(toDto(entity)));
        return agendamentoDtoList;
    }
}
