package com.cheapvegarden.resource;

import javax.annotation.security.*;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.cheapvegarden.repository.dto.SetupDto;
import com.cheapvegarden.service.SetupService;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;

@Path("/setup")
public class SetupResource {

    @Inject
    SetupService service;

    @PUT
    @Transactional
    @TransactionConfiguration(timeout = 900)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @PermitAll
    public Response alterar(@PathParam("id") long id, @Valid SetupDto setupDto) throws Exception {
        try {
            return Response.ok(service.alterar(id, setupDto)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Transactional
    @TransactionConfiguration(timeout = 900)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lerUmidadesETipoControle")
    @PermitAll
    public Response lerUmidadesETipoControle() throws Exception {
        try {
            return Response.ok(service.listarUmidadeMaximaMinimaETipoDeControle()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @PermitAll
    public Response lerSetupPorId(@PathParam("id") long id) throws Exception {
        try {
            return Response.ok(service.buscarSetupPorId(id)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response buscarSetupAtivo() throws Exception {
        try {
            return Response.ok(service.buscarSetupAtivo()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}