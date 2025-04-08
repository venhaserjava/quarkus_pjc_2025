package endereco.resource;

//package com.rossatti.quarkus_pjc_2025.endereco.resources;

import endereco.dtos.EnderecoRequest;
import endereco.dtos.EnderecoResponse;
import endereco.services.EnderecoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

//import com.rossatti.quarkus_pjc_2025.endereco.dtos.*;
//import com.rossatti.quarkus_pjc_2025.endereco.services.EnderecoService;

import java.util.List;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService service;

    @POST
    public Response create(@Valid EnderecoRequest request) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(request))
                .build();
    }

    @GET
    public List<EnderecoResponse> list() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public EnderecoResponse findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}")
    public EnderecoResponse update(@PathParam("id") Long id, @Valid EnderecoRequest request) {
        return service.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
