package br.com.api.pedromotoso.reservaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReservaDto {

    @NotEmpty
    @NotBlank
    @NotNull
    private String nomeHospede;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date dataFim;

    @Min(1)
    @NotNull
    private Integer quantidadePessoas;
}
