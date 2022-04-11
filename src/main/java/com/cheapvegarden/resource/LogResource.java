package com.cheapvegarden.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.cheapvegarden.repository.dto.LogDto;
import com.cheapvegarden.service.LogService;

@Path("/log")
@ApplicationScoped
public class LogResource {
    
    @Inject
    LogService service;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(LogDto logDto) throws Exception {
        return Response.ok(service.salvar(logDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() throws Exception {
        return Response.ok(service.listarLog()).build();
    }
}
