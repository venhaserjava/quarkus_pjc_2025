package servidor_temporario.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestQuery;
import servidor_temporario.dtos.request.ServidorTemporarioRequest;
import servidor_temporario.service.ServidorTemporarioService;

@Path("/servidor-temporario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServidorTemporarioResource {

    @Inject
    ServidorTemporarioService service;

    @GET
    @Path("/{id}")
    public Response findByPessoaId(@PathParam("id") Long id) {
        return Response.ok(service.findByPessoaId(id)).build();
    }

    @GET
    public Response findAll(@RestQuery String nome,
                            @RestQuery int page,
                            @RestQuery int size) {
        return Response.ok(service.findAll(nome, page, size)).build();
    }

    @POST
    public Response create(ServidorTemporarioRequest request) {
        service.create(request);
        return Response.status(Response.Status.CREATED)
                .entity("{\"mensagem\": \"Servidor temporário cadastrado com sucesso.\"}")
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ServidorTemporarioRequest request) {
        service.update(id, request);
        return Response.ok("{\"mensagem\": \"Servidor temporário alterado com sucesso.\"}").build();
    }
}
