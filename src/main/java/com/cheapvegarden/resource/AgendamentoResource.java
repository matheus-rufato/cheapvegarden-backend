package com.cheapvegarden.resource;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.cheapvegarden.repository.dto.AgendamentoDto;
import com.cheapvegarden.service.AgendamentoService;

@Path("/agendamento")
@ApplicationScoped
public class AgendamentoResource {

    @Inject
    AgendamentoService service;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed(value = { "user", "admin" })
    public Response criar(AgendamentoDto agendamentoDto) throws Exception {
        return Response.ok(service.salvar(agendamentoDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{culturaId}")
    @RolesAllowed(value = { "user", "admin" })
    public Response lerAgendamentosPorCultura(@PathParam("culturaId") long culturaId) throws Exception {
        return Response.ok(service.listarAgendamentosPorCultura(culturaId)).build();
    }

    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @RolesAllowed(value = { "user", "admin" })
    public Response deletar(@PathParam("id") long id) throws Exception {
        service.deletarAgendamento(id);
        return Response.ok(Status.OK).build();
    }

    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deletarAgendamentos/{culturaId}")
    @RolesAllowed(value = { "user", "admin" })
    public Response deletarAgendamentosPorCultura(@PathParam("culturaId") long culturaId) throws Exception {
        service.deletarAgendamentosPorCultura(culturaId);
        return Response.ok(Status.OK).build();
    }
}
