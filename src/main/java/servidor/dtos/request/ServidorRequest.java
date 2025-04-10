package servidor.dtos.request;

import  com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServidorRequest {

        @NotBlank @Size(min = 3, max = 200)
        private String nome;

        @NotBlank @Size(min = 2, max = 200)
        private String mae;

        @Size(min = 3, max = 200)
        private String pai;

        @NotNull @Pattern(regexp = "MASCULINO|FEMININO|NAOINF")
        private String sexo;

//        @NotNull
//        @Past
//        @JsonFormat(pattern = "yyyy-MM-dd") // 👈 Aqui a mágica
//        private LocalDate dataNascimento;

        private Set<ServidorEnderecoRequest> enderecos;
}
/*
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

public record ServidorRequest(
        @NotBlank @Size(min = 3, max = 200)
        String nome,
        @NotBlank @Size(min = 2, max = 200)
        String mae,
        @Size(min = 3, max = 200)
        String pai,
        @NotNull @Pattern(regexp = "MASCULINO|FEMININO|NAOINF")
        String sexo,
        @NotNull @Past
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataNascimento,

        Set<ServidorEnderecoRequest> enderecos
) {}

 */