package br.com.api.pedromotoso.reservaapi.repository;

import br.com.api.pedromotoso.reservaapi.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
