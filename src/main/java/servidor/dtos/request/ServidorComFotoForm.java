package servidor.dtos.request;

import org.jboss.resteasy.reactive.RestForm;

import java.io.InputStream;

public class ServidorComFotoForm {

    @RestForm("pessoa")
    public String pessoaJson;

    @RestForm("foto")
    public InputStream foto;
}
