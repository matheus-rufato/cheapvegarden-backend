package com.cheapvegarden.resource;

import javax.annotation.security.PermitAll;
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
public class CulturaResource {

    @Inject
    CulturaService service;

    @POST
    @Transactional
    @TransactionConfiguration(timeout = 900)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response criar(CulturaDto culturaDto) throws Exception {
        try {
            return Response.ok(service.salvar(culturaDto)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @PermitAll
    public Response alterarNome(@PathParam("id") long id, @Valid String nome) throws Exception {
        try {
            return Response.ok(service.alterarNomeCultura(id, nome)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response ler() throws Exception {
        try {
            return Response.ok(service.listarCulturas()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscarPorSetup/{setupId}")
    @PermitAll
    public Response buscarCulturaPorSetup(@PathParam("setupId") long setupId) throws Exception {
        try {
            return Response.ok(service.buscarCulturaPorSetup(setupId)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @PermitAll
    public Response deletar(@PathParam("id") long id) throws Exception {
        try {
            service.deletarCultura(id);
            return Response.ok(Status.OK).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}