package com.cheapvegarden.resource;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.cheapvegarden.repository.dto.UsuarioDto;
import com.cheapvegarden.service.UsuarioService;

@Path("/usuario")
@ApplicationScoped
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response criar(UsuarioDto usuarioDto) throws Exception {
        return Response.ok(service.salvar(usuarioDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response readAll() throws Exception {
        return Response.ok(service.listarUsuarios()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchName/{nome}")
    @RolesAllowed("admin")
    public Response readUserByName(@PathParam("nome") String nome) throws Exception {
        return Response.ok(service.buscarUsuarioPorNome(nome)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchId/{id}")
    @RolesAllowed("admin")
    public Response readUserById(@PathParam("id") long id) throws Exception {
        return Response.ok(service.buscarUsuarioPorId(id)).build();
    }
}
