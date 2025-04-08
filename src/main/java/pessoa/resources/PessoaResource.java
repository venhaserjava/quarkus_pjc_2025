package pessoa.resources;

//package com.rossatti.quarkus_pjc_2025.pessoa.resources;
//import com.rossatti.quarkus_pjc_2025.pessoa.dtos.PessoaRequest;
//import com.rossatti.quarkus_pjc_2025.pessoa.dtos.PessoaResponse;
//import com.rossatti.quarkus_pjc_2025.pessoa.services.PessoaService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pessoa.dtos.PessoaRequest;
import pessoa.dtos.PessoaResponse;
import pessoa.services.PessoaService;

import java.net.URI;
import java.util.List;

@Path("/api/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

    @Inject
    PessoaService service;

    @GET
    public List<PessoaResponse> listAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return service.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response create(@Valid PessoaRequest request) {
        PessoaResponse response = service.create(request);
        return Response.created(URI.create("/api/pessoas/" + response.id())).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid PessoaRequest request) {
        PessoaResponse response = service.update(id, request);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = service.delete(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
