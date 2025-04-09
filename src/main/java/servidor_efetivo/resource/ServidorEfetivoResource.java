package servidor_efetivo.resource;

//package com.seuprojeto.resource;
//import com.seuprojeto.dto.ServidorEfetivoRequestDTO;
//import com.seuprojeto.dto.ServidorEfetivoResponseDTO;
//import com.seuprojeto.service.ServidorEfetivoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servidor_efetivo.dtos.request.ServidorEfetivoRequestDTO;
import servidor_efetivo.dtos.response.ServidorEfetivoResponseDTO;
import servidor_efetivo.services.ServidorEfetivoService;

@Path("/servidor-efetivo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServidorEfetivoResource {

    @Inject
    ServidorEfetivoService service;

    @POST
    public Response criar(ServidorEfetivoRequestDTO dto) {
        ServidorEfetivoResponseDTO criado = service.criar(dto);
        return Response.ok(criado).status(Response.Status.CREATED).build();
    }
    
    @GET
    @Path("/unidade/{id}")
    public Response listarPorUnidade(@PathParam("id") Long unidadeId,
                                      @QueryParam("page") @DefaultValue("0") int page,
                                      @QueryParam("size") @DefaultValue("10") int size) {
        var lista = service.listarPorUnidade(unidadeId, page, size);
        return Response.ok(lista).build();
    }    

}
