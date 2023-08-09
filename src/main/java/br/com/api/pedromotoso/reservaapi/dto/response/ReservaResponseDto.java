package br.com.api.pedromotoso.reservaapi.dto.response;

import br.com.api.pedromotoso.reservaapi.dto.ReservaDto;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class ReservaResponseDto extends ReservaDto {
    private Long id;
    private String status;
}
