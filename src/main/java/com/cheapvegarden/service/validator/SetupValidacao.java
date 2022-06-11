package com.cheapvegarden.service.validator;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.cheapvegarden.repository.dao.SetupDao;
import com.cheapvegarden.repository.entity.Setup;

@ApplicationScoped
public class SetupValidacao {

    @Inject
    SetupDao dao;

    public void validarSeSetupIdExiste(long setupId) throws Exception {
        List<Setup> setupList = dao.listAll();

        if (setupList.stream().map(Setup::getId).noneMatch((item) -> item.equals(setupId))) {
            throw new Exception("Id de Setup inexistente");
        }
    }

    public void validarSeIdEDiferenteDeUm(long setupId) throws Exception {
        if (setupId == 1l) {
            throw new Exception("Não existe cultura para este Id de Setup");
        }
    }

    public void validarSeUmidadeMaximaEMaiorQueUmidadeMinima(int umidadeMaxima, int umidadeMinima) throws Exception {
        if (umidadeMaxima <= umidadeMinima) {
            throw new Exception("Umidade máxima menor ou igual a umidade mínima");
        }
    }

    public void validarSeNaoEstaAlterandoStatusDoSetupSemLigacao(long id, boolean status) throws Exception {
        if (id == 1l && !status) {
            throw new Exception("Alterando o status do Setup sem cultura");
        }
    }

    public void validarSeTipoControleEstaFalso() throws Exception {
        Setup setup = dao.buscarSetupAtivo();
        if (setup.isTipoControle()) {
            throw new Exception("Alterando o status da Solénoide no modo automático");
        }
    }
}