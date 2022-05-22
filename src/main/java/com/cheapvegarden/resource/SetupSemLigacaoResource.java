package com.cheapvegarden.resource;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.cheapvegarden.repository.dto.SetupSemLigacaoDto;
import com.cheapvegarden.service.SetupSemLigacaoService;

@Path("setupSemLigacao")
public class SetupSemLigacaoResource {

    @Inject
    SetupSemLigacaoService service;

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed(value = { "user", "admin" })
    public Response alterarSetupSemLigacao(SetupSemLigacaoDto setupDto) throws Exception {
        try {
            return Response.ok(service.alterarSetupSemLigacao(setupDto)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(value = { "user", "admin" })
    public Response lerSetupSemLigacao() throws Exception {
        try {
            return Response.ok(service.lerSetupSemLigacao()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}