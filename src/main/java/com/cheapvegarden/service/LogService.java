package com.cheapvegarden.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cheapvegarden.repository.dao.LogDao;
import com.cheapvegarden.repository.dto.LogDto;
import com.cheapvegarden.repository.entity.Log;
import com.cheapvegarden.service.converter.LogConverter;

@ApplicationScoped
public class LogService {

    @Inject
    LogDao dao;

    @Inject
    LogConverter converter;

    public LogDto salvar(LogDto logDto) throws Exception {
        try {
            Log log = converter.toEntity(logDto);
            dao.persistAndFlush(log);
            return converter.toDto(log);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<LogDto> listarLog() throws Exception {
        try {
            List<Log> logList = dao.listAll();
            List<LogDto> logDtoList = converter.toDtoList(logList);
            return logDtoList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<LogDto> listarUltimosRegistros(int quantidade) throws Exception {
        try {
            List<Log> logList = dao.buscarUltimosDozeRegistro(quantidade);
            List<LogDto> logDtoList = converter.toDtoList(logList);
            return logDtoList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}