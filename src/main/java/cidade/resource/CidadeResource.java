package cidade.resource;
//package com.rossatti.quarkus_pjc_2025.resources;
//import com.rossatti.quarkus_pjc_2025.entities.Cidade;
//import com.rossatti.quarkus_pjc_2025.services.CidadeService;
import cidade.entities.Cidade;
import cidade.services.CidadeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/cidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CidadeResource {

    @Inject
    CidadeService cidadeService;

    @GET
    public List<Cidade> getAll() {
        return cidadeService.findAll();
    }

    @GET
    @Path("/{id}")
    public Cidade getById(@PathParam("id") Long id) {
        return cidadeService.findById(id)
                .orElseThrow(() -> new NotFoundException("City not found"));
    }

    @POST
    public Response create(Cidade cidade) {
        Cidade created = cidadeService.create(cidade);
        return Response.created(URI.create("/cidades/" + created.getId())).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Cidade update(@PathParam("id") Long id, Cidade cidade) {
        return cidadeService.update(id, cidade);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        cidadeService.delete(id);
    }
}
