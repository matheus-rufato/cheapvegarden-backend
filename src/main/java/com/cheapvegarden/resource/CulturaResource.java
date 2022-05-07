package com.cheapvegarden.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.cheapvegarden.repository.dto.CulturaDto;
import com.cheapvegarden.service.CulturaService;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;

@Path("/cultura")
@ApplicationScoped
public class CulturaResource {

    @Inject
    CulturaService service;

    @POST
    @Transactional
    @TransactionConfiguration(timeout = 900)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criar(CulturaDto culturaDto) throws Exception {
        return Response.ok(service.salvar(culturaDto)).build();
    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response alterarNome(@PathParam("id") long id, @Valid String nome) throws Exception {
        return Response.ok(service.alterarNomeCultura(id, nome)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ler() throws Exception {
        return Response.ok(service.listarCulturas()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscarCulturaPorSetup/{setupId}")
    public Response buscarCulturaPorSetup(@PathParam("setupId") long setupId) throws Exception {
        return Response.ok(service.buscarCulturaPorSetup(setupId)).build();
    }

    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deletar(@PathParam("id") long id) throws Exception {
        service.deletarCultura(id);
        return Response.status(Status.OK).build();
    }
}
