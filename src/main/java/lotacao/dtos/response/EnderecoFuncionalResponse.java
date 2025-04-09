package lotacao.dtos.response;

public record EnderecoFuncionalResponse(
        String pessoaNome,
        String unidadeNome,
        String logradouro,
        Integer numero,
        String bairro,
        String cidadeNome,
        String cidadeUf
) {}
