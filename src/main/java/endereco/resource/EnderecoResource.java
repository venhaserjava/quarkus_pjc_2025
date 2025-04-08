package endereco.resource;

//package com.rossatti.quarkus_pjc_2025.resources;
//import com.rossatti.quarkus_pjc_2025.entities.Endereco;
//import com.rossatti.quarkus_pjc_2025.services.EnderecoService;
import endereco.entities.Endereco;
import endereco.services.EnderecoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService enderecoService;

    @GET
    public List<Endereco> getAll() {
        return enderecoService.findAll();
    }

    @GET
    @Path("/{id}")
    public Endereco getById(@PathParam("id") Long id) {
        return enderecoService.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found"));
    }

    @POST
    public Response create(Endereco endereco) {
        Endereco created = enderecoService.create(endereco);
        return Response.created(URI.create("/enderecos/" + created.getId())).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Endereco update(@PathParam("id") Long id, Endereco endereco) {
        return enderecoService.update(id, endereco);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        enderecoService.delete(id);
    }
}
