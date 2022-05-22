package com.cheapvegarden.resource;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.cheapvegarden.repository.dto.LogDto;
import com.cheapvegarden.service.LogService;

@Path("/log")
public class LogResource {

    @Inject
    LogService service;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response criar(LogDto logDto) throws Exception {
        try {
            return Response.ok(service.salvar(logDto)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response ler() throws Exception {
        try {
            return Response.ok(service.listarLog()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}