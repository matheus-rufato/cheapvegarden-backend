package com.cheapvegarden.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid SetupDto setupDto) throws Exception {
        return Response.ok(service.alterar(id, setupDto)).build();
    }

    @GET
    @Transactional
    @TransactionConfiguration(timeout = 900)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lerUmidadesETipoControle")
    public Response lerUmidadesETipoControle() throws Exception {
        return Response.ok(service.listarUmidadeMaximaMinimaETipoDeControle()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response lerSetupPorId(@PathParam("id") Long id) throws Exception {
        return Response.ok(service.buscarSetupPorId(id)).build();
    }
}
