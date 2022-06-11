package com.cheapvegarden.resource;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

import com.cheapvegarden.repository.dto.AgendamentoDto;
import com.cheapvegarden.service.AgendamentoService;

@Path("/agendamento")
public class AgendamentoResource {

    @Inject
    AgendamentoService service;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response criar(AgendamentoDto agendamentoDto) throws Exception {
        try {
            return Response.ok(service.salvar(agendamentoDto)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{culturaId}")
    @PermitAll
    public Response lerAgendamentosPorCultura(@PathParam("culturaId") long culturaId) throws Exception {
        try {
            return Response.ok(service.listarAgendamentosPorCultura(culturaId)).build();
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
            service.deletarAgendamento(id);
            return Response.ok(Status.OK).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deletarAgendamentos/{culturaId}")
    @PermitAll
    public Response deletarAgendamentosPorCultura(@PathParam("culturaId") long culturaId) throws Exception {
        try {
            service.deletarAgendamentosPorCultura(culturaId);
            return Response.ok(Status.OK).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}