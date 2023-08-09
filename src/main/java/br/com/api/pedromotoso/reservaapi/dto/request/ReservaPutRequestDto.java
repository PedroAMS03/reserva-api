package br.com.api.pedromotoso.reservaapi.dto.request;

import br.com.api.pedromotoso.reservaapi.dto.ReservaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReservaPutRequestDto extends ReservaDto {
    private String status;
}