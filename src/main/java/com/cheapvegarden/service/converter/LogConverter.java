package com.cheapvegarden.service.converter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.cheapvegarden.common.Converter;
import com.cheapvegarden.repository.dto.LogDto;
import com.cheapvegarden.repository.entity.Log;

@ApplicationScoped
public class LogConverter implements Converter<Log, LogDto> {

    @Override
    public LogDto toDto(Log entity) {
        return LogDto.builder()
                .id(entity.getId())
                .hora(entity.getHora())
                .fluxo(entity.getFluxo())
                .temperaturaClima(entity.getTemperaturaClima())
                .umidadeClima(entity.getUmidadeClima())
                .umidadeSolo(entity.getUmidadeSolo())
                .build();
    }

    @Override
    public Log toEntity(LogDto dto) throws IllegalAccessException {
        return Log.builder()
                .id((dto.getId()))
                .hora(LocalTime.now())
                .fluxo(dto.getFluxo())
                .temperaturaClima(dto.getTemperaturaClima())
                .umidadeClima(dto.getUmidadeClima())
                .umidadeSolo(dto.getUmidadeSolo())
                .build();
    }

    @Override
    public List<LogDto> toDtoList(List<Log> entityList) {
        List<LogDto> logDtoList = new ArrayList<>();
        entityList.forEach(entity -> logDtoList.add(toDto(entity)));
        return logDtoList;
    }
}