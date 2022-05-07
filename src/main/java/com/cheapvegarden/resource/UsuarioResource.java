package com.cheapvegarden.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.cheapvegarden.repository.dto.UsuarioDto;
import com.cheapvegarden.service.UsuarioService;

@Path("/user")
@ApplicationScoped
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(UsuarioDto usuarioDto) throws Exception {
        return Response.ok(service.salvar(usuarioDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() throws Exception {
        return Response.ok(service.listarUsuarios()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchName/{nome}")
    public Response readUserByName(@PathParam("nome") String nome) throws Exception {
        return Response.ok(service.buscarUsuarioPorNome(nome)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchId/{id}")
    public Response readUserById(@PathParam("id") long id) throws Exception {
        return Response.ok(service.buscarUsuarioPorId(id)).build();
    }
}
