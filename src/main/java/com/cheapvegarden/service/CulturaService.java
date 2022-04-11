package com.cheapvegarden.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cheapvegarden.repository.dao.CulturaDao;
import com.cheapvegarden.repository.dto.ControleDto;
import com.cheapvegarden.repository.dto.CulturaDto;
import com.cheapvegarden.repository.dto.SetupDto;
import com.cheapvegarden.repository.entity.Cultura;
import com.cheapvegarden.service.converter.CulturaConverter;

@ApplicationScoped
public class CulturaService {
    
    @Inject
    CulturaDao dao;

    @Inject
    CulturaConverter converter;

    @Inject
    ControleService controleService;

    @Inject
    SetupService setupService;

    public CulturaDto salvar(CulturaDto culturaDto) throws Exception {
        try {
            ControleDto controleDto = controleService.salvar(culturaDto.getControleDto());
            SetupDto setupDto = setupService.salvar(culturaDto.getSetupDto());

            culturaDto.setControleDto(controleDto);
            culturaDto.setSetupDto(setupDto);

            Cultura cultura = converter.toEntity(culturaDto);

            dao.persistAndFlush(cultura);
            
            return converter.toDto(cultura);        
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<CulturaDto> listarCulturas() throws Exception {
        try {
            List<CulturaDto> culturaDtoList = new ArrayList<>();
            List<Cultura> culturas = dao.listAll();
            culturaDtoList = converter.toDtoList(culturas);
            return culturaDtoList;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public CulturaDto deletarCultura(Long id) throws Exception {
        try {
            Cultura cultura = dao.findById(id);
            dao.delete(cultura);
            return converter.toDto(cultura);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
