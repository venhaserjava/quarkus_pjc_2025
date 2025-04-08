package cidade.resource;

import cidade.dtos.CidadeRequest;
import cidade.dtos.CidadeResponse;
import cidade.services.CidadeService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CidadeResource {

    @Inject
    CidadeService service;

    @POST
    public Response create(@Valid CidadeRequest request) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(request))
                .build();
    }

    @GET
    public List<CidadeResponse> list(@QueryParam("page") @DefaultValue("0") int page,
                                     @QueryParam("size") @DefaultValue("10") int size) {
        return service.findAll(page, size);
    }
//    public List<CidadeResponse> list() {
//        return service.findAll();
//    }

    @GET
    @Path("/{id}")
    public CidadeResponse findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}")
    public CidadeResponse update(@PathParam("id") Long id, @Valid CidadeRequest request) {
        return service.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
