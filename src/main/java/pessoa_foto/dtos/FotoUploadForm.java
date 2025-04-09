package pessoa_foto.dtos;

import org.jboss.resteasy.reactive.RestForm;

import java.io.InputStream;

public class FotoUploadForm {

    @RestForm("file")
    public InputStream file;

    @RestForm("pessoaId")
    public Long pessoaId;
}
