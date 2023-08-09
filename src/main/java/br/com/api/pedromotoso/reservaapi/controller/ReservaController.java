package br.com.api.pedromotoso.reservaapi.controller;

import br.com.api.pedromotoso.reservaapi.domain.Reserva;
import br.com.api.pedromotoso.reservaapi.dto.request.ReservaPutRequestDto;
import br.com.api.pedromotoso.reservaapi.dto.request.ReservaRequestDto;
import br.com.api.pedromotoso.reservaapi.dto.response.ReservaResponseDto;
import br.com.api.pedromotoso.reservaapi.service.ReservaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaResponseDto> criaReserva(@RequestBody @Valid ReservaRequestDto reservaRequest) {

        ReservaResponseDto reservaResponseDto = reservaService.criaReserva(reservaRequest);
        return ResponseEntity.created(URI.create(String.format("/reservas/%s", reservaResponseDto.getId()))).body(reservaResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> consultaReservas() {
        return ResponseEntity.ok(reservaService.consultaReservas());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Reserva>> consultaReservasPaginado(@Valid @NotNull @Param("pagina") Integer pagina,
                                                                  @Valid @NotNull @Param("tamanhoDaPagina") Integer tamanhoDaPagina) {
        return ResponseEntity.ok(reservaService.consultaReservasPaginado(pagina, tamanhoDaPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDto> consultaReservaPorId(
            @Valid @PathVariable("id") @Min(1) @NotNull Long id
    ) {
        return ResponseEntity.ok(reservaService.consultaReservaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDto> atualizaReservaPorId(
            @Valid @PathVariable("id") @Min(1) @NotNull Long id,
            @RequestBody @Valid ReservaPutRequestDto reservaPutRequestDto
    ) {

        return ResponseEntity.ok(reservaService.atualizaReservaPorId(id, reservaPutRequestDto));
    }

    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<ReservaResponseDto> deletaReservaPorId(
            @PathVariable("id") @Valid @Min(1) @NotNull Long id
    ) {

        return ResponseEntity.ok(reservaService.cancelaReservaPorId(id));
    }
}