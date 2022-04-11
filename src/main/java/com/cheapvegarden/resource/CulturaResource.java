package com.cheapvegarden.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.cheapvegarden.repository.dto.CulturaDto;
import com.cheapvegarden.service.CulturaService;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;

@Path("/culture")
@ApplicationScoped
public class CulturaResource {
    
    @Inject
    CulturaService service;

    @POST
    @Transactional
    @TransactionConfiguration(timeout = 120)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CulturaDto culturaDto) throws Exception {
        return Response.ok(service.salvar(culturaDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() throws Exception {
        return Response.ok(service.listarCulturas()).build();
    }

    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) throws Exception {
        return Response.ok(service.deletarCultura(id)).build();
    }
}
