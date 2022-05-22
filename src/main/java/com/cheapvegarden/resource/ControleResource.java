package com.cheapvegarden.resource;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.cheapvegarden.repository.dto.ControleDto;
import com.cheapvegarden.service.ControleService;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;

@Path("/controle")
public class ControleResource {

    @Inject
    ControleService service;

    @PUT
    @Transactional
    @TransactionConfiguration(timeout = 900)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response alterar(ControleDto controleDto) throws Exception {
        try {
            return Response.ok(service.alterar(controleDto)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lerStatusSolenoide")
    @PermitAll
    public Response lerStatusSolenoide() throws Exception {
        try {
            return Response.ok(service.lerStatusSolenoide()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response ler() throws Exception {
        try {
            return Response.ok(service.lerControle()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}