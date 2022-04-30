package com.cheapvegarden.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.cheapvegarden.repository.dto.SetupSemLigacaoDto;
import com.cheapvegarden.service.SetupSemLigacaoService;

@Path("setupSemLigacao")
@ApplicationScoped
public class SetupSemLigacaoResource {

    @Inject
    SetupSemLigacaoService service;

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarSetupSemLigacao(SetupSemLigacaoDto setupDto) throws Exception {
        return Response.ok(service.alterarSetupSemLigacao(setupDto)).build();
    }

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response lerSetupSemLigacao() throws Exception {
        return Response.ok(service.lerSetupSemLigacao()).build();
    }
}
