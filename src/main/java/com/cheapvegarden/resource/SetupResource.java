package com.cheapvegarden.resource;

import javax.annotation.security.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.cheapvegarden.repository.dto.SetupDto;
import com.cheapvegarden.service.SetupService;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;

@Path("/setup")
@ApplicationScoped
public class SetupResource {

    @Inject
    SetupService service;

    @PUT
    @Transactional
    @TransactionConfiguration(timeout = 900)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @RolesAllowed(value = { "user", "admin" })
    public Response alterar(@PathParam("id") long id, @Valid SetupDto setupDto) throws Exception {
        return Response.ok(service.alterar(id, setupDto)).build();
    }

    @GET
    @Transactional
    @TransactionConfiguration(timeout = 900)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lerUmidadesETipoControle")
    @PermitAll
    public Response lerUmidadesETipoControle() throws Exception {
        return Response.ok(service.listarUmidadeMaximaMinimaETipoDeControle()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @RolesAllowed(value = { "user", "admin" })
    public Response lerSetupPorId(@PathParam("id") long id) throws Exception {
        return Response.ok(service.buscarSetupPorId(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(value = { "user", "admin" })
    public Response buscarSetupAtivo() throws Exception {
        return Response.ok(service.buscarSetupAtivo()).build();
    }
}
