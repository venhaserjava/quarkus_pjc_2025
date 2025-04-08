package endereco.resource;


import endereco.dtos.EnderecoRequest;
import endereco.dtos.EnderecoResponse;
import endereco.services.EnderecoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

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
    public Response list(@QueryParam("page") @DefaultValue("0") int page,
                         @QueryParam("size") @DefaultValue("10") int size) {
        return Response.ok(service.findAll(page, size)).build();
    }
/*
    @GET
    public List<EnderecoResponse> list() {
        return service.findAll();
    }
*/
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
