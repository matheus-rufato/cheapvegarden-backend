package com.cheapvegarden.resource;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.cheapvegarden.repository.dto.UsuarioDto;
import com.cheapvegarden.service.UsuarioService;

@Path("/usuario")
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    // @RolesAllowed("admin")
    @PermitAll
    public Response criar(UsuarioDto usuarioDto) throws Exception {
        try {
            return Response.ok(service.salvar(usuarioDto)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response readAll() throws Exception {
        try {
            return Response.ok(service.listarUsuarios()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchName/{nome}")
    @RolesAllowed("admin")
    public Response readUserByName(@PathParam("nome") String nome) throws Exception {
        try {
            return Response.ok(service.buscarUsuarioPorNome(nome)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchId/{id}")
    @RolesAllowed("admin")
    public Response readUserById(@PathParam("id") long id) throws Exception {
        try {
            return Response.ok(service.buscarUsuarioPorId(id)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}