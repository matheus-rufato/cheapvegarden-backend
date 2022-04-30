package com.cheapvegarden.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response alterar(ControleDto controleDto) throws Exception {
        return Response.ok(service.alterar(controleDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lerStatusSolenoide")
    public Response lerStatusSolenoide() throws Exception {
        return Response.ok(service.lerStatusSolenoide()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ler() throws Exception {
        return Response.ok(service.lerControle()).build();
    }
}
