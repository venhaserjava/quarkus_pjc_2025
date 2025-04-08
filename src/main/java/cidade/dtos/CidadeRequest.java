package cidade.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CidadeRequest(
        @NotBlank(message = "O nome da cidade não pode estar em branco.")
        @Size(max = 200, message = "O nome da cidade não pode ter mais de 200 caracteres.")
        String nome,
        @NotBlank(message = "A UF da cidade não pode estar em branco.")
        @Size(min = 2, max = 2, message = "A UF deve ter exatamente 2 caracteres.")
        String uf
) {
}


//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//public record CidadeRequest(
//        @NotBlank
//        @Size(max = 200)
//        String nome,
//
//        @NotBlank
//        @Size(min = 2, max = 2)
//        String uf
//) {}
