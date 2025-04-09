package lotacao.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lotacao.dtos.request.LotacaoRequest;
import lotacao.services.LotacaoService;

@Path("/lotacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LotacaoResource {

    @Inject LotacaoService service;

    @POST
    public Response create(LotacaoRequest request) {
        return Response.ok(service.create(request)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return service.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @GET
    public Response list(@QueryParam("page") @DefaultValue("0") int page,
                         @QueryParam("size") @DefaultValue("10") int size) {
        return Response.ok(service.findAll(page, size)).build();
    }
    @GET
    @Path("/funcional")
    public Response buscarEnderecoFuncional(@QueryParam("nome") String nome) {
        var resultado = service.buscarEnderecoFuncionalPorNome(nome);
        return Response.ok(resultado).build();
    }

}
