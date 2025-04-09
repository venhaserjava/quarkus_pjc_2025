package servidor_efetivo.dtos.response;

import java.time.LocalDate;

public record ServidorEfetivoResponseDTO(
        Long id,
        String nome,
        Integer idade,
        String matricula,
        String unidadeNome,
        LocalDate dataLotacao,
        String portaria
) {}
