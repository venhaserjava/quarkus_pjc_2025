package servidor.dtos.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

public record ServidorRequest(
        @NotBlank @Size(min = 3, max = 200) String nome,
        @NotBlank @Size(min = 2, max = 200) String mae,
        @Size(min = 3, max = 200) String pai,
        @NotNull @Pattern(regexp = "MASCULINO|FEMININO|NAOINF") String sexo,
        @NotNull @Past LocalDate dataNascimento,
        Set<ServidorEnderecoRequest> enderecos
) {}
