package pessoa_foto.resources;

import io.minio.*;
import io.minio.http.Method;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pessoa_foto.dtos.FotoUploadForm;
import pessoa_foto.services.PessoaFotoService;

import java.io.ByteArrayInputStream;
import java.util.List;

@Path("/api/foto_pessoa")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class PessoaFotoResource {

    @Inject
    PessoaFotoService service;

    @Inject
    MinioClient minioClient;

    private static final String BUCKET_NAME = "quarkus-fotos";

    @POST
    @Path("/upload")
    public Response upload(FotoUploadForm form) {
        try {
            byte[] fileBytes = form.file.readAllBytes();
            String hash = service.generateHash(fileBytes);
            String fileName = hash + ".png";

            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
            }

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(fileName)
                    .stream(new ByteArrayInputStream(fileBytes), fileBytes.length, -1)
                    .contentType("image/png")
                    .build());

            service.saveFoto(form.pessoaId, hash);
            String url = gerarLinkTemporarioInterno(hash);
            return Response.ok("Foto salva com sucesso. Link: " + url).build();

        } catch (Exception e) {
            return Response.serverError().entity("Erro ao fazer upload: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/listar/{pessoaId}")
    public Response listar(@PathParam("pessoaId") Long pessoaId) {
        List<String> fotos = service.listarHashesPorPessoa(pessoaId);
        return Response.ok(fotos).build();
    }

    @GET
    @Path("/link/{hash}")
    public Response gerarLinkTemporario(@PathParam("hash") String hash) {
        try {
            String url = gerarLinkTemporarioInterno(hash);
            return Response.ok(url).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao gerar link: " + e.getMessage()).build();
        }
    }

    private String gerarLinkTemporarioInterno(String hash) throws Exception {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(BUCKET_NAME)
                .object(hash + ".png")
                .expiry(300)
                .build());
    }
}
