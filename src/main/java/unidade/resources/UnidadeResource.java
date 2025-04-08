package unidade.resources;


//package com.rossatti.quarkus_pjc_2025.unidade.resources;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import unidade.dtos.UnidadeRequest;
import unidade.dtos.UnidadeResponse;
import unidade.services.UnidadeService;

//import com.rossatti.quarkus_pjc_2025.unidade.dtos.*;
//import com.rossatti.quarkus_pjc_2025.unidade.services.UnidadeService;

import java.util.List;

@Path("/unidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnidadeResource {

    @Inject
    UnidadeService service;

    @POST
    public Response create(@Valid UnidadeRequest request) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(request))
                .build();
    }

    @GET
    public List<UnidadeResponse> list() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public UnidadeResponse findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}")
    public UnidadeResponse update(@PathParam("id") Long id, @Valid UnidadeRequest request) {
        return service.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
