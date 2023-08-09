package br.com.api.pedromotoso.reservaapi.service;

import br.com.api.pedromotoso.reservaapi.domain.Reserva;
import br.com.api.pedromotoso.reservaapi.dto.request.ReservaPutRequestDto;
import br.com.api.pedromotoso.reservaapi.dto.request.ReservaRequestDto;
import br.com.api.pedromotoso.reservaapi.dto.response.ReservaResponseDto;
import br.com.api.pedromotoso.reservaapi.enums.StatusEnum;
import br.com.api.pedromotoso.reservaapi.exceptions.NotFoundException;
import br.com.api.pedromotoso.reservaapi.repository.ReservaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public ReservaResponseDto criaReserva(ReservaRequestDto reservaRequestDto) {
        log.info("Criando reserva");
        Reserva reserva = reservaRepository.save(Reserva
                .builder()
                .nomeHospede(reservaRequestDto.getNomeHospede())
                .quantidadePessoas(reservaRequestDto.getQuantidadePessoas())
                .dataInicio(reservaRequestDto.getDataInicio())
                .dataFim(reservaRequestDto.getDataFim())
                .status(StatusEnum.CONFIRMADA.name())
                .build());
        log.info("Reserva {} criada com sucesso", reserva.getId());
        return buildReservaReponseDto(reserva);
    }

    public List<Reserva> consultaReservas() {
        return reservaRepository.findAll();
    }

    public Page<Reserva> consultaReservasPaginado(Integer pagina, Integer tamanhoDaPagina) {
        Pageable sortedByName =
                PageRequest.of(pagina, tamanhoDaPagina, Sort.by("id"));

        Page<Reserva> reservaList = reservaRepository.findAll(sortedByName);

        return reservaList;
    }

    public ReservaResponseDto consultaReservaPorId(Long id) {
        return buildReservaReponseDto(consultaReserva(id));
    }

    private Reserva consultaReserva(Long id) {
        log.info("Consultando reserva {}", id);

        Optional<Reserva> reserva = reservaRepository.findById(id);

        reserva.ifPresentOrElse(
                (value)
                        -> {
                    log.info("Reserva {} encontrada", id);
                },
                ()
                        -> {
                    log.info("Reserva {} não encontrada", id);
                    throw new NotFoundException();
                });

        return reserva.get();
    }

    public ReservaResponseDto atualizaReservaPorId(Long id, ReservaPutRequestDto reservaPutRequestDto) {
        consultaReserva(id);

        Reserva reserva = reservaRepository.save(Reserva
                .builder()
                .id(id)
                .nomeHospede(reservaPutRequestDto.getNomeHospede())
                .quantidadePessoas(reservaPutRequestDto.getQuantidadePessoas())
                .dataInicio(reservaPutRequestDto.getDataInicio())
                .dataFim(reservaPutRequestDto.getDataFim())
                .status(reservaPutRequestDto.getStatus())
                .build());

        log.info("Reserva {} atualizada com sucesso", id);
        return buildReservaReponseDto(reserva);
    }


    public ReservaResponseDto cancelaReservaPorId(Long id) {
        Reserva reserva = consultaReserva(id);
        reserva.setStatus(StatusEnum.CANCELADA.name());
        reservaRepository.save(reserva);
        log.info("Reserva {} cancelada com sucesso", id);
        return buildReservaReponseDto(reserva);
    }

    public ReservaResponseDto buildReservaReponseDto(Reserva reserva) {
        return ReservaResponseDto
                .builder()
                .status(reserva.getStatus())
                .id(reserva.getId())
                .quantidadePessoas(reserva.getQuantidadePessoas())
                .nomeHospede(reserva.getNomeHospede())
                .dataInicio(reserva.getDataInicio())
                .dataFim(reserva.getDataFim())
                .build();
    }
}
