package servidor.resources;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.*;
import io.minio.http.Method;
import io.vertx.ext.web.FileUpload;
import io.vertx.mutiny.core.buffer.Buffer;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestForm;
import pessoa.entities.Pessoa;
import pessoa_foto.services.PessoaFotoService;
import servidor.dtos.request.ServidorRequest;
import servidor.dtos.response.ServidorResponse;
import servidor.mappers.ServidorMapper;
import servidor.services.ServidorService;

import java.io.ByteArrayInputStream;

@Path("/servidores")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class ServidorResource {

    @Inject ServidorService servidorService;
    @Inject PessoaFotoService pessoaFotoService;
    @Inject MinioClient minioClient;

    private static final String BUCKET_NAME = "fotos";

    @POST
    public Response createServidorComFoto(@RestForm("pessoa") String pessoaJson,
                                          @RestForm("foto") FileUpload foto,
                                          @FormParam("foto") Buffer fileBuffer) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ServidorRequest request = mapper.readValue(pessoaJson, ServidorRequest.class);

            Pessoa pessoa = servidorService.createServidor(request);
            ServidorResponse response = ServidorMapper.toResponse(pessoa);

            byte[] bytes = fileBuffer.getBytes();
            String hash = pessoaFotoService.generateHash(bytes);
            String fileName = hash + ".png";

            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
            }

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(fileName)
                    .stream(new ByteArrayInputStream(bytes), bytes.length, -1)
                    .contentType(foto.contentType())
                    .build());

            pessoaFotoService.saveFoto(pessoa.getId(), hash);

            String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(BUCKET_NAME)
                    .object(fileName)
                    .expiry(300)
                    .build());

            return Response.ok(response.withFoto(url)).build();

        } catch (Exception e) {
            return Response.serverError().entity("Erro ao cadastrar servidor: " + e.getMessage()).build();
        }
    }
}


/*
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.*;
import io.minio.http.Method;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.ext.web.FileUpload;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestForm;
import pessoa.entities.Pessoa;
import pessoa_foto.services.PessoaFotoService;
import servidor.dtos.request.ServidorRequest;
import servidor.dtos.response.ServidorResponse;
import servidor.mappers.ServidorMapper;
import servidor.services.ServidorService;

import java.io.ByteArrayInputStream;


@Path("/servidores")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class ServidorResource {

    @Inject ServidorService servidorService;
    @Inject PessoaFotoService pessoaFotoService;
    @Inject MinioClient minioClient;

    private static final String BUCKET_NAME = "quarkus-fotos";

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createServidorComFoto(@RestForm("pessoa") String pessoaJson,
                                          @RestForm("foto") FileUpload foto,
                                          @FormParam("foto") Buffer fileBuffer) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ServidorRequest request = mapper.readValue(pessoaJson, ServidorRequest.class);
            Pessoa pessoa = servidorService.createServidor(request);
            ServidorResponse response = ServidorMapper.toResponse(pessoa);

            byte[] bytes = fileBuffer.getBytes();
            String hash = pessoaFotoService.generateHash(bytes);
            String fileName = hash + ".png";

            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
            }

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(fileName)
                    .stream(new ByteArrayInputStream(bytes), bytes.length, -1)
                    .contentType(foto.contentType())
                    .build());

            pessoaFotoService.saveFoto(pessoa.getId(), hash);

            String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(BUCKET_NAME)
                    .object(fileName)
                    .expiry(300)
                    .build());

            return Response.ok(response.withFoto(url)).build();

        } catch (Exception e) {
            return Response.serverError().entity("Erro ao cadastrar servidor: " + e.getMessage()).build();
        }
    }
    }
}
*/