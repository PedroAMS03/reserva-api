package br.com.api.pedromotoso.reservaapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tb_ibm_reserva")
public class Reserva {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_hospede")
    private String nomeHospede;
    @Column(name = "data_inicio")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;
    @Column(name = "data_fim")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataFim;
    @Column(name = "quantidade_pessoas")
    private Integer quantidadePessoas;

    @Column(name = "status")
    private String status;
}
