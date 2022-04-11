package com.cheapvegarden.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.cheapvegarden.repository.dto.AgendamentoDto;
import com.cheapvegarden.service.AgendamentoService;

@Path("/scheduling")
@ApplicationScoped
public class AgendamentoResource {
    
    @Inject
    AgendamentoService service;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AgendamentoDto agendamentoDto) throws Exception {
        return Response.ok(service.salvar(agendamentoDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() throws Exception {
        return Response.ok(service.listarAgendamentos()).build();
    }

    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) throws Exception {
        return Response.ok(service.deletarAgendamento(id)).build();
    }
}
