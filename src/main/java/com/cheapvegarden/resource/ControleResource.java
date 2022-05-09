package com.cheapvegarden.resource;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.cheapvegarden.repository.dto.ControleDto;
import com.cheapvegarden.service.ControleService;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;

@Path("/controle")
@ApplicationScoped
public class ControleResource {

    @Inject
    ControleService service;

    @PUT
    @Transactional
    @TransactionConfiguration(timeout = 900)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response alterar(ControleDto controleDto) throws Exception {
        return Response.ok(service.alterar(controleDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lerStatusSolenoide")
    @PermitAll
    public Response lerStatusSolenoide() throws Exception {
        return Response.ok(service.lerStatusSolenoide()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response ler() throws Exception {
        return Response.ok(service.lerControle()).build();
    }
}
