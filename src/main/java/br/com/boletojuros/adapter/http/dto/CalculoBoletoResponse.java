package br.com.boletojuros.adapter.http.dto;

import br.com.boletojuros.core.domain.enuns.TipoBoleto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
public class CalculoBoletoResponse {

    @NotNull
    private String codigo;

    @NotNull
    @JsonProperty("data_vencimento")
    @JsonFormat(pattern = "yyyy-MM--dd")
    private LocalDate dataVencimento;

    private BigDecimal valor;

    private TipoBoleto tipo;
}
